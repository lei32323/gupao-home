package threadsingle;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 20:05
 * @Description:
 */
public class ThreadLocalSingle {

    private static ThreadLocal<ThreadLocalSingle> threadLocalSingleThreadLocal = new ThreadLocal<>();


    private ThreadLocalSingle(){
        if(threadLocalSingleThreadLocal.get()!=null){
            throw new RuntimeException("非法创建");
        }
    }


    public static ThreadLocalSingle getInstance(){
        if(threadLocalSingleThreadLocal.get()==null){
            threadLocalSingleThreadLocal.set(new ThreadLocalSingle());
        }
        return threadLocalSingleThreadLocal.get();
    }

    //防止反序列化
    private Object readResolve(){
        return threadLocalSingleThreadLocal.get();
    }



}
