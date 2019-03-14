package com.gupaoedu.home.pay.method;

public class ALIPay extends Payment {


    @Override
    public String getName() {
        return "阿里支付";
    }

    @Override
    public double checkBalances(Long uid) {
        return 200.0;
    }
}
