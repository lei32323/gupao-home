package dnroute;

import dnroute.proxy.OrderServiceProxy;

import java.util.Date;

public class DBSourceProxyTest {

    public static void main(String[] args) {

        IOrderService iOrderService = new OrderServiceProxy(new OrderService());
        Order order = new Order();
        order.setYear(new Date());
        iOrderService.createOrder(order);

    }

}
