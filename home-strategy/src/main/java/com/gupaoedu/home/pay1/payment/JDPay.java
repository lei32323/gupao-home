package com.gupaoedu.home.pay1.payment;

public class JDPay extends AbstractPayment {
    @Override
    public String getName() {
        return "京东支付";
    }

    @Override
    public double checkBalance(String uid) {
        return 500;
    }
}
