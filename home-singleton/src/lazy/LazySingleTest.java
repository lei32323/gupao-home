package lazy;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 19:14
 * @Description:
 */
public class LazySingleTest {

    public static void main(String[] args) throws InterruptedException {
//        LazySingle instance = LazySingle.getInstance();
//        System.out.println(instance);
//        LazySingle instance1 = LazySingle.getInstance();
//        System.out.println(instance1);



        //多线程测试时间
//        long currentTimeMillis = System.currentTimeMillis();
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        for (int i=0;i<100;i++){
//            new Thread(()->{
//                LazySingle lazySingle = LazySingle.getInstance();
//                System.out.println(lazySingle);
//                countDownLatch.countDown();
//            }).start();
//        }
//        countDownLatch.await();
//        System.out.println("用时:"+(System.currentTimeMillis()-currentTimeMillis));

        //恶汉式已经测试过防止反序列化 和 防止反射了，这里不测试了


    }
}
