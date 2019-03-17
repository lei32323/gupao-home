package com.gupaoedu.home.usb1;

public class UsbTest {

    public static void main(String[] args) {
        DataLineAdapter dataLineAdapter = new DataLineAdapter();
        System.out.println( dataLineAdapter.type());
        System.out.println( dataLineAdapter.typec());

    }

}
