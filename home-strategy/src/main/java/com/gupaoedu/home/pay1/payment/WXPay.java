package com.gupaoedu.home.pay1.payment;

public class WXPay extends AbstractPayment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public double checkBalance(String uid) {
        return 100;
    }
}
