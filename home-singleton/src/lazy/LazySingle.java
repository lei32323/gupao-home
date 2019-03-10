package lazy;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 19:11
 * @Description:
 */
public class LazySingle {

    private LazySingle(){
        //防止反射
        if(lazySingle!=null){
            throw new RuntimeException("非法调用");
        }
    }

    private static LazySingle lazySingle = null;


    //因为线程不安全，所以需要加上synchronized,但是多线程调用的时候，耗时久
    public synchronized static LazySingle getInstance(){
        if(lazySingle==null){
            lazySingle = new LazySingle();
        }

        return lazySingle;
    }

    //防止序列化
    private Object readResolve(){
        return lazySingle;
    }

}
