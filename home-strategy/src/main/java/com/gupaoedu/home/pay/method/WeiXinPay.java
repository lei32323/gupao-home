package com.gupaoedu.home.pay.method;

public class WeiXinPay extends Payment {


    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public double checkBalances(Long uid) {
        return 300.0;
    }
}
