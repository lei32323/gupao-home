package hungry;

import java.io.Serializable;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 14:33
 * @Description:
 */
public class HungrySingle implements Serializable {

    //构造函数私有化
    private HungrySingle(){
        //防止反射
        if(hungrySingle!=null){
            throw new RuntimeException("非法调用");
        }
    }

    //静态属性，优先创建HungrySingle实体
    private static final HungrySingle hungrySingle = new HungrySingle();

    //提供一个外部调用的方法
    public static HungrySingle getInstance(){
        return hungrySingle;
    }


    //防止序列化
    private Object readResolve(){
        return  hungrySingle;
    }

}
