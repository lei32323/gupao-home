import java.io.Serializable;

public class Role implements Cloneable, Serializable {

    /**
     * ��ɫ����
     */
    private String name ;


    /**
     * ��ɫ����
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
