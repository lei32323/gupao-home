import java.lang.reflect.Field;
import java.util.stream.Stream;

public class ReflexClone {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++) {
            method();
        }
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
    }


    public static void method(){
        UserVO userVO = new UserVO();
        userVO.setName("zhangsan");
        userVO.setPwd("1111");
        userVO.setRole(new Role());
        System.out.println(userVO+":"+userVO.getRole());


        //反射
        Field[] declaredFields = userVO.getClass().getDeclaredFields();
        UserVO clone = new UserVO();
        Stream.of(declaredFields).forEach(declaredField -> {
            try {
                //设置权限
                declaredField.setAccessible(true);
                //进行赋值
                declaredField.set(clone,declaredField.get(userVO));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(clone+":"+clone.getRole());
    }
}
