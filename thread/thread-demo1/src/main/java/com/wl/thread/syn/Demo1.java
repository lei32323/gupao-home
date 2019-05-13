package com.wl.thread.syn;

public class Demo1{
    public static void main(String[] args) {
        Integer count = 0;
        System.out.println(count);

        Integer count1 = count++;

        System.out.println(count==count1);

    }
}