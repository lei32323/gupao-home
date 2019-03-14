package com.gupaoedu.home.pay1.payment;

public class ALIPay extends AbstractPayment {
    @Override
    public String getName() {
        return "阿里支付";
    }

    @Override
    public double checkBalance(String uid) {
        return 200;
    }
}
