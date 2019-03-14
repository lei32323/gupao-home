package com.gupaoedu.home.pay;

import com.gupaoedu.home.pay.method.Payment;
import sun.security.krb5.internal.PAData;

/**
 * 订单
 */
public class Order {

    //Id
    private Long uid;

    //订单id
    private String orderId;

    //订单金额
    private double amount;

    public Order(Long uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public PayMsg pay(String payType){
        //通过策略模式找到对应的付款方式
        Payment payMent = PayStrategy.getPayMent(payType);
        System.out.println("欢迎使用"+payMent.getName()+"支付");
        PayMsg pay = payMent.pay(this.uid, this.amount);
        return pay;

    }
}
