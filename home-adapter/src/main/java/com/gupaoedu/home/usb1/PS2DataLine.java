package com.gupaoedu.home.usb1;

/**
 * 安卓数据线
 */
public class PS2DataLine implements IDataLine {

    @Override
    public String type() {
        return "ps2";
    }
}
