import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteClone {

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
        System.out.println(userVO + ":" + userVO.getRole());

        try {
            //写出去
            try(ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();){
                try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);){
                    objectOutputStream.writeObject(userVO);
                }

                //写回来
                try(ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());) {
                    try(ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);){
                        UserVO clone = (UserVO) objectInputStream.readObject();
                        System.out.println(clone + ":" + clone.getRole());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
