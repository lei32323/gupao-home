package com.gupaoedu.home.pay1;

import com.gupaoedu.home.pay1.payment.AbstractPayment;

/**
 * 订单
 */
public class Order {

    //id
    private String uid;

    //订单id
    private String orderId;

    //金额
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    //付款
    public ResultMsg pay(String type){
        //获取支付方式
        AbstractPayment payment = PayStrategy.getPayment(type);
        System.out.println("欢迎使用"+payment.getName()+"付款");
        ResultMsg pay = payment.pay(this.uid, this.amount);
        return pay;
    }
}
