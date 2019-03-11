import org.springframework.cglib.beans.BeanCopier;

public class BeanCopierClone {

    public static void main(String[] args) {

        UserVO userVO = new UserVO();
        userVO.setName("zhangsan");
        userVO.setPwd("1111");
        userVO.setRole(new Role());
        System.out.println(userVO+":"+userVO.getRole());

        long start = System.currentTimeMillis();

        for (int i=0;i<100000;i++){
            BeanCopier b = BeanCopier.create(UserVO.class, UserVO.class, false);
            UserVO clone = new UserVO();
            b.copy(userVO,clone,null);
            System.out.println(clone+":"+clone.getRole());
        }
        System.out.println("ºÄÊ±:"+(System.currentTimeMillis()-start));


    }

}
