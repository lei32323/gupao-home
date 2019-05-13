package com.wl.thread.lock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 无锁的演示
 */
public class NoLock {

    /**
     *
     */

    private static final String SLEEP1 = "sleep1";

    /**
     * 锁对象
     */
    private final MarkWord markWord = new MarkWord();

    private String str = new String("123");


    public static void main(String[] args) throws Exception {
        NoLock noLock = new NoLock();
        Method method1 = NoLock.class.getDeclaredMethod("sleep1", null);
        Method method10 = NoLock.class.getDeclaredMethod("sleep10", null);

        new Thread(() -> {
            // 1秒
            try {
                System.out.println(noLock.getResultOfTheExecution(method1, noLock, null));;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "thread1").start();

        new Thread(() -> {
            // 10秒
            try {
                System.out.println(noLock.getResultOfTheExecution(method10, noLock, null));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "thread2").start();


    }

    public void sleep1() {
        // 睡眠1秒
        try {
            System.out.println("休眠1秒");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sleep10() {
        // 睡眠10秒
        try {
            System.out.println("休眠10秒");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取是否执行的结果
     * 
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public boolean getResultOfTheExecution(Method method, Object obj, String[] args) throws Exception {
        if (markWord.assign(Thread.currentThread().getId())) {
            // 说明为空， 可以获得偏向锁
            // 继续执行
            System.out.println("当前markWord中的线程ID为空,当前对象复制后，直接执行代码块");

            // 反射执行代码
            method.invoke(obj, args);

            // 释放markWord
            markWord.clear();
            return true;
        } else
        // 说明threadId存在，判断是否相同
        // 判断说明当前markword中的threadId和当前的一致
        if (markWord.compar(Thread.currentThread().getId())) {
            // 一致 说明存在，直接执行代码块
            System.out.println("当前markWord中的线程ID存在并且相同,直接执行代码块");

            // 反射执行代码
            method.invoke(obj, args);

            // 释放markWord
            markWord.clear();
            return true;
        } else {
            // 其他情况，就是markWord中的线程ID存在，并且和当前线程的ID不相同，就进入到自旋锁的装填
            for (int i = 1; i <= 100; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("自旋锁进行等待" + i);
                // 继续获取
                if (markWord.assign(Thread.currentThread().getId())) {
                    // 说明为空， 可以获得偏向锁
                    // 继续执行
                    System.out.println("当前markWord中的线程ID为空,当前对象复制后，直接执行代码块");

                    // 反射执行代码
                    method.invoke(obj, args);

                    // 释放markWord
                    markWord.clear();
                    return true;
                } else
                // 说明threadId存在，判断是否相同
                // 判断说明当前markword中的threadId和当前的一致
                if (markWord.compar(Thread.currentThread().getId())) {
                    // 一致 说明存在，直接执行代码块
                    System.out.println("当前markWord中的线程ID存在并且相同,直接执行代码块");

                    // 反射执行代码
                    method.invoke(obj, args);

                    // 释放markWord
                    markWord.clear();
                    return true;
                }
            }
            // 循环100次后，还是没有执行，那么就进入到重量级锁 就是堵塞状态
            System.out.println("进入到堵塞状态。。。。。等待其他线程释放");
        }

        return false;
    }
}