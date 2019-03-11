package dnroute.proxy;

import dnroute.IOrderService;
import dnroute.Order;
import dnroute.OrderService;
import dnroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;

public class OrderServiceProxy implements IOrderService {


    //ע��һ��OrderService����
    private OrderService orderService;

    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void createOrder(Order order) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        //�ж�����һ���
        Integer year = Integer.parseInt(simpleDateFormat.format(order.getYear()));
        //�л�����ͬ�����ݿ�
        System.out.println("�л������ݿ�[db_"+year+"]");
        //�����ݿ���ĵ�
        DynamicDataSourceEntity.set(year);
        //ִ�з���
        this.orderService.createOrder(order);
        //�ָ�����Դ
        DynamicDataSourceEntity.store();


    }
}
