package com.gupaoedu.home.pay.method;

import com.gupaoedu.home.pay.PayMsg;

/**
 * 支付渠道
 */
public abstract class Payment {


    //支付名称
    public abstract String getName();

    //查询余额
    public abstract double checkBalances(Long uid);

    //付款
    public PayMsg pay(Long uid, double amount){
        if(checkBalances(uid)<amount){
          return  new PayMsg("500","支付失败","余额不足");
        }
        //执行扣款
        return  new PayMsg("200","支付成功","扣款:"+amount);
    }

}
