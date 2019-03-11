## 原型模式

### jdk的形式
需要实现jdk的 `CloneAble` 接口,重写`clone`方法
~~~
public class UserVO implements Cloneable{
...
}
//调用
UserVO clone = (UserVO)userVO.clone();
~~~
> 需要克隆的属性都需要实现`CloneAble`接口,重写`clone`方法

### 反射的形式
~~~
 Field[] declaredFields = userVO.getClass().getDeclaredFields();
        UserVO clone = new UserVO();
        Stream.of(declaredFields).forEach(declaredField -> {
            try {
                //设置权限
                declaredField.setAccessible(true);
                //进行赋值
                declaredField.set(clone,declaredField.get(userVO));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
~~~
> 实现的为浅克隆
### json的形式
~~~  
    //--------------fastjson
    //转换为Json
    String json = JSON.toJSONString(userVO);
    //json转换为实体
    UserVO parse = JSON.parseObject(json,UserVO.class);
    
    //---------------GSON
    //转换为json
    Gson gson = new Gson();
    String s = gson.toJson(userVO);
    //转换为实体
    UserVO userVO1 = gson.fromJson(s, UserVO.class);
    System.out.println(userVO1+":"+userVO1.getRole());
~~~
> json实现为深度克隆

### spring的BeanUtils形式
~~~
    BeanUtils.copyProperties(userVO,clone);
~~~
> sping的BeanUtils为浅复制

### byte形式
~~~
 //写出去
try(ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();){
    try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);){
        objectOutputStream.writeObject(userVO);
    }
    
    //写回来
    try(ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());) {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);){
            UserVO clone = (UserVO) objectInputStream.readObject();
            System.out.println(clone + ":" + clone.getRole());
        }
    }
}
~~~
> byte形式为深度克隆

###  BeanCopier形式
~~~
    BeanCopier b = BeanCopier.create(UserVO.class, UserVO.class, false);
    UserVO clone = new UserVO();
    b.copy(userVO,clone,null);
~~~
> BeanCopier形式为浅复制

### 性能比较
> 进行10000次的执行

byte形式（1023ms）
JDK形式（202ms）
json形式(fastjson:584ms GSON:976ms)
反射形式(315ms)
springBeanUtils形式(458ms)
BeanCopier(235ms)

`JDK形式 < BeanCopier < 反射 < springBeanUtils < fastjson < GSON < byte`

> 进行100000次的执行

byte形式（3963ms）
JDK形式（1683ms）
json形式(fastjson:2266ms GSON:4153ms)
反射形式(2358ms)
spring的Bean形式(2073ms)
BeanCopier(1096ms)

`BeanCopier < spring的BeanUtils  < JDK形式 < fastjson < 反射形式 < GSON < byte`



总结：BeanCopier一直的比较稳定，其次是spring的BeanUtils.