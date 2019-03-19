# 观察者模式
要点： 回调函数

## jdk 实现
+ 观察者 需要 实现`Observer`接口，重写update方法，用来监听对象的更改

+ 被观察者 需要 继承`Observable`类，每次更改信息，需要设置更改状态`super.setChanged();`，并且通知观察者`super.notifyObservers();`

+ `java.util.Observable`

 + `private Vector<Observer> obs;` 存放 观察者的集合，使用Vector，防止线程安全问题

 + `addObserver(Observer o)` 添加观察者 ，把观察者添加到obs集合中

 + `deleteObserver(Observer o)` 删除观察者

 + `void notifyObservers()` 通知所有观察者
   
    + `notifyObservers(Object arg)` 通知 
            
      + `changed` 判断状态是否是更改过的，不是就return掉
      + 获取到通知对象，转换成数组 
      + 通过反射调用Update方法 

属于广播形式。只要订阅了。。都会收到。


## guava 实现
实现GPer社区提问通知的业务场景  ->`com.gupaoedu.home.gper` 包下

原理分析

1.  `register（）`方法 保存监听者，是怎么保存到哪里的？

   `com.google.common.eventbus.SubscriberRegistry#register`

   ~~~java
   void register(Object listener) {
       //获取全部的被@Subscriber注解的方法集合
       Multimap<Class<?>, Subscriber> listenerMethods = findAllSubscribers(listener);
   	//循环集合中的key:参数类型
       for (Entry<Class<?>, Collection<Subscriber>> entry : listenerMethods.asMap().entrySet()) {
           Class<?> eventType = entry.getKey();
           //获取到subscriber对象
           Collection<Subscriber> eventMethodsInListener = entry.getValue();
   
           //从subscribers中根据参数类型找到对应的对象
           CopyOnWriteArraySet<Subscriber> eventSubscribers = subscribers.get(eventType);
   		//为空的话，
           if (eventSubscribers == null) {
               //创建一个，并且保存到subscribers中
               CopyOnWriteArraySet<Subscriber> newSet = new CopyOnWriteArraySet<>();
               eventSubscribers =
                   MoreObjects.firstNonNull(subscribers.putIfAbsent(eventType, newSet), newSet);
           }
   		//value值赋值为eventMethodsInListener
           eventSubscribers.addAll(eventMethodsInListener);
       }
   }
   ~~~

   `com.google.common.eventbus.SubscriberRegistry#findAllSubscribers`

   ~~~java
   //传入监听者进行存储
   private Multimap<Class<?>, Subscriber> findAllSubscribers(Object listener) {
       //new HashMultimap<>()
       Multimap<Class<?>, Subscriber> methodsInListener = HashMultimap.create();
       //反射而获取类信息
       Class<?> clazz = listener.getClass();
       //获取到类中的方法信息，属性信息
       for (Method method : getAnnotatedMethods(clazz)) {
           //获取参数类型
         Class<?>[] parameterTypes = method.getParameterTypes();
           //参数列表（第一个参数)
         Class<?> eventType = parameterTypes[0];
           //Subscriber.create(bus, listener, method) 根据对象和方法和所属bus创建subscribel对象
           //保存到methodsInListener 中 ，Key为参数类型
         methodsInListener.put(eventType, Subscriber.create(bus, listener, method));
       }
       return methodsInListener;
     }
   ~~~

   保存到subscribers对象

   ~~~java
   private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers =Maps.newConcurrentMap();
   ~~~

   

1.  `guava` 是怎么识别注解`@Subscribe` 的？

   通过`findAllSubscribers`方法看到`getAnnotatedMethods()`方法获取

   根据注释得知：这是一个线程安全的缓存，包括每个类，类中呗@Subscribe注释的超类。

   ~~~java
    private static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
       //调用的是静态的LoadingCache集合。
        return subscriberMethodsCache.getUnchecked(clazz);
     }
   ~~~

   `com.google.common.eventbus.SubscriberRegistry#getAnnotatedMethodsNotCached`

   ~~~java
   private static ImmutableList<Method> getAnnotatedMethodsNotCached(Class<?> clazz) {
       //以相同的顺序返回此集合中类型的原始类型。
       Set<? extends Class<?>> supertypes = TypeToken.of(clazz).getTypes().rawTypes();
       Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
       for (Class<?> supertype : supertypes) {
           for (Method method : supertype.getDeclaredMethods()) {
               //有Subscribe注释，并且不是复合方法(通过编译器加入的)
               if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                   // TODO(cgdecker): Should check for a generic parameter type and error out
                   Class<?>[] parameterTypes = method.getParameterTypes();
                   //校验参数列表是不是等于1
                   checkArgument(
                       parameterTypes.length == 1,
                       "Method %s has @Subscribe annotation but has %s parameters."
                       + "Subscriber methods must have exactly 1 parameter.",
                       method,
                       parameterTypes.length);
   				//包装method为MethodIdentifier类型
                   MethodIdentifier ident = new MethodIdentifier(method);
                   //不存在就添加
                   if (!identifiers.containsKey(ident)) {
                       identifiers.put(ident, method);
                   }
               }
           }
       }
       //复制一份list副本返回
       return ImmutableList.copyOf(identifiers.values());
   }
   ~~~

   

2. `post（）`发送事件，guava后面都做了什么？

   ~~~java
   public void post(Object event) {
       //从集合中根据入参找到订阅对象
       Iterator<Subscriber> eventSubscribers = subscribers.getSubscribers(event);
       //虚幻遍历
       if (eventSubscribers.hasNext()) {
           //调用dispatcher方法
         dispatcher.dispatch(event, eventSubscribers);
       } else if (!(event instanceof DeadEvent)) {
         // the event had no subscribers and was not itself a DeadEvent
         post(new DeadEvent(this, event));
       }
     }
   ~~~

   `dispatcher.dispatch(event, eventSubscribers);` 用来保持线程安全性

   ~~~java
   void dispatch(Object event, Iterator<Subscriber> subscribers) {
         checkNotNull(event);
         checkNotNull(subscribers);
       //获取一个事件队列
         Queue<Event> queueForThread = queue.get();
       //保存传入的事件
         queueForThread.offer(new Event(event, subscribers));
   	//判断是否已经在执行
         if (!dispatching.get()) {
             //设置已经在执行
           dispatching.set(true);
           try {
             Event nextEvent;
               //从queue中获取一个事件
             while ((nextEvent = queueForThread.poll()) != null) {
                 //循环事件的事件的订阅对象
               while (nextEvent.subscribers.hasNext()) {
                   //执行订阅对象的方法
                 nextEvent.subscribers.next().dispatchEvent(nextEvent.event);
               }
             }
           } finally {
               //dispatching 清空
             dispatching 清空.remove();
               //事件队列清除
             queue.remove();
           }
         }
       }
   ~~~

   ~~~java
   final void dispatchEvent(final Object event) {
       //并发执行
       executor.execute(
           new Runnable() {
             @Override
             public void run() {
               try {
                   //执行方法
                 invokeSubscriberMethod(event);
               } catch (InvocationTargetException e) {
                 bus.handleSubscriberException(e.getCause(), context(event));
               }
             }
           });
     }
   ~~~

   ~~~java
   void invokeSubscriberMethod(Object event) throws InvocationTargetException {
       try {
           //调用订阅者的method方法传入对象的参数执行
           method.invoke(target, checkNotNull(event));
       } catch (IllegalArgumentException e) {
           throw new Error("Method rejected target/argument: " + event, e);
       } catch (IllegalAccessException e) {
           throw new Error("Method became inaccessible: " + event, e);
       } catch (InvocationTargetException e) {
           if (e.getCause() instanceof Error) {
               throw (Error) e.getCause();
           }
           throw e;
       }
   }
   ~~~

   