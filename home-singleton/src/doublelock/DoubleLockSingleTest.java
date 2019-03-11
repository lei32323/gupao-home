package doublelock;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 19:35
 * @Description:
 */
public class DoubleLockSingleTest {

    public static void main(String[] args) {
//        DoubleLockSingle instance = DoubleLockSingle.getInstance();
//        System.out.println(instance);
//        DoubleLockSingle instance1 = DoubleLockSingle.getInstance();
//        System.out.println(instance1);

        //测试多线程并发
        for (int i=0;i<199;i++){
            new Thread(()->{
                DoubleLockSingle instance = DoubleLockSingle.getInstance();
                System.out.println(instance);
            }).start();
        }


    }

}
