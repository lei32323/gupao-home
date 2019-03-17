package com.gupaoedu.home.usb2;

public class UsbTest {

    public static void main(String[] args) {
        TypecDataLine typecDataLine = new TypecDataLine();
        DataLineAdapter dataLineAdapter = new DataLineAdapter(typecDataLine);
        System.out.println(dataLineAdapter.type());
    }

}
