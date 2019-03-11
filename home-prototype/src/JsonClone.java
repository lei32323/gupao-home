import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonClone {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++) {
            methodFastjson();
        }
        System.out.println("��ʱ:"+(System.currentTimeMillis()-start));
    }

    public static void methodFastjson(){
        UserVO userVO = new UserVO();
        userVO.setName("zhangsan");
        userVO.setPwd("1111");
        userVO.setRole(new Role());
        System.out.println(userVO+":"+userVO.getRole());

        //-------------fastjson
        //ת��ΪJson
        String json = JSON.toJSONString(userVO);
        //jsonת��Ϊʵ��
        UserVO parse = JSON.parseObject(json,UserVO.class);
        System.out.println(parse+":"+parse.getRole());
    }

    public static void methodGosn(){
        UserVO userVO = new UserVO();
        userVO.setName("zhangsan");
        userVO.setPwd("1111");
        userVO.setRole(new Role());
        System.out.println(userVO+":"+userVO.getRole());

        //ת��Ϊjson
        Gson gson = new Gson();
        String s = gson.toJson(userVO);
        //ת��Ϊʵ��
        UserVO userVO1 = gson.fromJson(s, UserVO.class);
        System.out.println(userVO1+":"+userVO1.getRole());
    }
}
