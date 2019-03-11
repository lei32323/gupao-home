import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonClone {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++) {
            methodFastjson();
        }
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
    }

    public static void methodFastjson(){
        UserVO userVO = new UserVO();
        userVO.setName("zhangsan");
        userVO.setPwd("1111");
        userVO.setRole(new Role());
        System.out.println(userVO+":"+userVO.getRole());

        //-------------fastjson
        //转换为Json
        String json = JSON.toJSONString(userVO);
        //json转换为实体
        UserVO parse = JSON.parseObject(json,UserVO.class);
        System.out.println(parse+":"+parse.getRole());
    }

    public static void methodGosn(){
        UserVO userVO = new UserVO();
        userVO.setName("zhangsan");
        userVO.setPwd("1111");
        userVO.setRole(new Role());
        System.out.println(userVO+":"+userVO.getRole());

        //转换为json
        Gson gson = new Gson();
        String s = gson.toJson(userVO);
        //转换为实体
        UserVO userVO1 = gson.fromJson(s, UserVO.class);
        System.out.println(userVO1+":"+userVO1.getRole());
    }
}
