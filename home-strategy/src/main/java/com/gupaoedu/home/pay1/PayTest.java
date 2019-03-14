package com.gupaoedu.home.pay1;

public class PayTest {

    public static void main(String[] args) {

        Order order = new Order("1","2019031400010009",200.0);
        System.out.println(order.pay(PayStrategy.JD_PAY));

    }

}
