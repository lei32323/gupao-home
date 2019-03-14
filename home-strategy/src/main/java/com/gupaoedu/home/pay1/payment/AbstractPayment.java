package com.gupaoedu.home.pay1.payment;

import com.gupaoedu.home.pay1.ResultMsg;

/**
 * 支付方式
 */
public abstract class AbstractPayment {

    //支付名称
    public abstract String getName();

    //查询余额
    public abstract double checkBalance(String uid);

    //通用方法 付款
    public ResultMsg pay(String uid,double amount){
        if(checkBalance(uid) < amount){
            return new ResultMsg("500","支付失败","余额不足");
        }
        return new ResultMsg("200","支付成功","支付金额:"+amount);

    }

}
