package cglib.dbroute;

import java.util.Date;

/**
 * ����ʵ��
 */
public class Order {

    //������
    private String orderId;


    //����ʱ��
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
