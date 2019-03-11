public class JDKClone {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++) {
            method();
        }
        System.out.println("ºÄÊ±:"+(System.currentTimeMillis()-start));
    }

    public static void method(){
        try {
            UserVO userVO = new UserVO();
            userVO.setName("zhangsan");
            userVO.setPwd("1111");
            userVO.setRole(new Role());
            System.out.println(userVO.toString());

            UserVO clone = (UserVO) userVO.clone();

            System.out.println(clone.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
