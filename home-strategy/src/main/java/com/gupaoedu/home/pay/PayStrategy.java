package com.gupaoedu.home.pay;

import com.gupaoedu.home.pay.method.ALIPay;
import com.gupaoedu.home.pay.method.JDPay;
import com.gupaoedu.home.pay.method.Payment;
import com.gupaoedu.home.pay.method.WeiXinPay;

import java.util.HashMap;
import java.util.Map;

public class PayStrategy {

    private PayStrategy(){}

    public static final String ALI_PAY = "ALIPAY";

    public static final  String JD_PAY = "JDPAY";

    public static final  String WX_PAY = "WXPAY";

    public static final String DEFAULT_PAY = ALI_PAY;


    //支付方式存储池
    private static final Map<String, Payment> PAYMENT_REGISTER = new HashMap<>();


    static {
        //初始化
        PAYMENT_REGISTER.put(ALI_PAY,new ALIPay());
        PAYMENT_REGISTER.put(JD_PAY,new JDPay());
        PAYMENT_REGISTER.put(WX_PAY,new WeiXinPay());
    }

    //获取付款方式
    public static Payment getPayMent(String payType){
        return PAYMENT_REGISTER.get(payType);
    }


}
