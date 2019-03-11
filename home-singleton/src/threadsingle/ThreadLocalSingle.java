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
            throw new RuntimeException("�Ƿ�����");
        }
    }


    public static ThreadLocalSingle getInstance(){
        if(threadLocalSingleThreadLocal.get()==null){
            threadLocalSingleThreadLocal.set(new ThreadLocalSingle());
        }
        return threadLocalSingleThreadLocal.get();
    }

    //��ֹ�����л�
    private Object readResolve(){
        return threadLocalSingleThreadLocal.get();
    }



}
