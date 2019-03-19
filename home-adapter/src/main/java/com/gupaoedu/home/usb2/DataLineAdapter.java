package com.gupaoedu.home.usb2;

/**
 * 类似于静态代理的模式
 */
public class DataLineAdapter implements IDataLine {

    //包含了typc-c的实体
    private TypecDataLine typecDataLine;

    public DataLineAdapter(TypecDataLine typecDataLine) {
        this.typecDataLine = typecDataLine;
    }

    @Override
    public String type() {
        return typecDataLine.typec();
    }



}
