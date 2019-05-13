package com.wl.thread.vola;

public class VolatileDemo1 {
    static volatile Object[] objects = new Object[1];

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                objects = new Object[2];
            }, "thread1").start();

            new Thread(() -> {
                objects = new Object[4];
            }, "thread2").start();

            System.out.println(objects.length);
        }

    }
}