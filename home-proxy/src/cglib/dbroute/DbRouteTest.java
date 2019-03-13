package cglib.dbroute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static sun.awt.image.PixelConverter.Argb.instance;

public class DbRouteTest {

    public static void main(String[] args) throws ParseException {
        Order order = new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date parse = simpleDateFormat.parse("2111-01-02");
        order.setCreateTime(parse);

        DbAutoSwitchProxy dbAutoSwitchProxy = new DbAutoSwitchProxy();
        OrderService orderService = (OrderService) dbAutoSwitchProxy.getInstance(OrderService.class);
        orderService.createOrder(order);
    }

}
