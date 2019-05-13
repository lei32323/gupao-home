package com.wl.thread.syn;

public class SynchronizedDemo implements Runnable {
    int x = 100;

    public synchronized void m1() {
        x = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("x=" + x);
    }

    public synchronized void m2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            SynchronizedDemo sd = new SynchronizedDemo(); //1 实例化
            new Thread(() -> sd.m1()).start();//2 调用实例化的m1方法，并且加上了一个synchronized，所以有对该方法加上了锁，具有线程安全性 这里就是1000
            new Thread(() -> sd.m2()).start();//3 调用实例化的m2方法，并且加上了一个synchronized，所以有对该方法加上了锁，具有线程安全性 这里就是2000
            sd.m2();//4 最后主线程调用m2方法，结果 2000
            System.out.println("Main x=" + sd.x); //main x=2000
            //有可能会先执行 234 243 324 342 423 432
        }
    }

    @Override
    public void run() {
        m1();
    }
}