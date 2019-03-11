package threadsingle;

public class ThreadLocalSingleTest {

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            ThreadLocalSingle instance = ThreadLocalSingle.getInstance();
            System.out.println(Thread.currentThread().getName()+":"+instance);
        }

        new Thread(()->{
            for(int i=0;i<10;i++){
                ThreadLocalSingle instance = ThreadLocalSingle.getInstance();
                System.out.println(Thread.currentThread().getName()+":"+instance);
            }
        }).start();
    }

}
