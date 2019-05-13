package com.wl.thread;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("123");
        }
    }
}
