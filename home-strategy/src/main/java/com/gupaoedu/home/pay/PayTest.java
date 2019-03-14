package com.gupaoedu.home.pay;

public class PayTest {

    public static void main(String[] args) {

        Order order = new Order(1l,"20190314000010009",100.0);

        PayMsg pay = order.pay(PayStrategy.ALI_PAY);
        System.out.println(pay);

    }

}
