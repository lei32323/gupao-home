package com.gupaoedu.home.pay.method;

public class JDPay extends Payment {


    @Override
    public String getName() {
        return "京东支付";
    }

    @Override
    public double checkBalances(Long uid) {
        return 100.0;
    }
}
