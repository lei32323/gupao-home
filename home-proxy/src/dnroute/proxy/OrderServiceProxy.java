package dnroute.proxy;

import dnroute.IOrderService;
import dnroute.Order;
import dnroute.OrderService;
import dnroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;

public class OrderServiceProxy implements IOrderService {


    //注入一个OrderService对象
    private OrderService orderService;

    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void createOrder(Order order) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        //判断是哪一年的
        Integer year = Integer.parseInt(simpleDateFormat.format(order.getYear()));
        //切换到不同的数据库
        System.out.println("切换到数据库[db_"+year+"]");
        //把数据库更改掉
        DynamicDataSourceEntity.set(year);
        //执行服务
        this.orderService.createOrder(order);
        //恢复数据源
        DynamicDataSourceEntity.store();


    }
}
