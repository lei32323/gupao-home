package com.gupaoedu.home.pay1;

import com.gupaoedu.home.pay1.payment.ALIPay;
import com.gupaoedu.home.pay1.payment.AbstractPayment;
import com.gupaoedu.home.pay1.payment.JDPay;
import com.gupaoedu.home.pay1.payment.WXPay;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付渠道
 */
public class PayStrategy {

    private PayStrategy(){};


    //支付方式注册中心
    private static final Map<String, AbstractPayment> PAY_REGISTER = new ConcurrentHashMap<>();

    public static final String ALI_PAY = "ALIPAY";
    public static final String WX_PAY = "WXPAY";
    public static final String JD_PAY = "JDPAY";
    public static final String DEFAULT_PAY = ALI_PAY;



    //初始化
    static{
        PAY_REGISTER.put(ALI_PAY,new ALIPay());
        PAY_REGISTER.put(WX_PAY,new WXPay());
        PAY_REGISTER.put(JD_PAY,new JDPay());
    }


    //获取支付方式
    public static final AbstractPayment getPayment(String type){
        return PAY_REGISTER.get(type);
    }


}
