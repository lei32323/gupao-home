package com.gupaoedu.home.usb1;

/**
 * 既支持了之前的usb接口。，又新增了type-c的接口
 */
public class DataLineAdapter extends PS2DataLine implements ITypecDataLine {

    @Override
    public String typec(){
       return super.type();
    }

}
