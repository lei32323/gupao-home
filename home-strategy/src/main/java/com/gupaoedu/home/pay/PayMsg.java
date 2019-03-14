package com.gupaoedu.home.pay;

/**
 * 支付结果
 */
public class PayMsg {

    //状态码
    private String code;

    //状态说明
    private String msg;

    //消息
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PayMsg(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "支付结果 ：" + code + '\'' +msg + '\'' + "支付信息 " + data ;
    }
}
