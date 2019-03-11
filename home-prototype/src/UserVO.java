import java.io.Serializable;

public class UserVO implements Cloneable, Serializable {


    /**
     * ����
     */
    private String name ;


    /**
     * ����
     */
    private String pwd;

    /**
     * ��ɫ
     */
    private Role role;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        UserVO clone = (UserVO)super.clone();
        clone.setRole((Role) role.clone());
        return clone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
