package cglib.dbroute;

import java.util.Date;

/**
 * 订单实体
 */
public class Order {

    //订单号
    private String orderId;


    //订单时间
    private Date createTime;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
