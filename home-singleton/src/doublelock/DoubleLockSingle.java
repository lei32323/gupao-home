package doublelock;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 19:29
 * @Description:
 */
public class DoubleLockSingle {

    private DoubleLockSingle(){
        //防止反射
        if(doubleLockSingle!=null){
            throw  new RuntimeException("非法调用");
        }
    }

    //因为创建对象的时候，jvm执行3步，(1.分配空间，2.初始化变量，3.把变量的地址指向jvm分配的空间。)不确定2 和3步骤是按顺序的。
    //所以要加上volatile，防止重排序
    private volatile static DoubleLockSingle doubleLockSingle = null;


    public static DoubleLockSingle getInstance(){
        if(doubleLockSingle==null){
            synchronized (DoubleLockSingle.class){
                if(doubleLockSingle==null){
                    doubleLockSingle = new DoubleLockSingle();
                }
            }
        }
        return doubleLockSingle;
    }

    //防止反序列化
    private Object readResolve(){
        return  doubleLockSingle;
    }

}
