package com.gupaoedu.home.pay1;

import java.util.Date;

/**
 * 执行结果
 */
public class ResultMsg {

    //状态码
    private String code;

    //状态说明
    private String msg;

    //消息
    private String data;


    public ResultMsg(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String toString(){
        return "支付信息:[" + code + "," + msg + ","+data+"]";
    }
}
