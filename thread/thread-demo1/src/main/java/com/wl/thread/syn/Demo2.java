package com.wl.thread.syn;

public class Demo2 {

    public synchronized void test1() {
        System.out.println(Thread.currentThread().getName() + " 获取到锁");
    }

    public static synchronized void test2() {
        System.out.println(Thread.currentThread().getName() + " 获取到锁");
    }

    private Object lock = new Object();

    public void test3() {

        synchronized (lock) {
            System.out.println("加锁");
        }

    }

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {

            new Thread(() -> {

                new Demo2().test1();

            }, "thread_" + i).start();

        }

    }

}