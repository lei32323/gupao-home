package hungry;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 14:36
 * @Description:
 */
public class HungrySingleTest {

    public static void main(String[] args) {
        //正常验证
//        HungrySingle hungrySingle = HungrySingle.getInstance();
//        HungrySingle hungrySingle1 = HungrySingle.getInstance();
//        System.out.println(hungrySingle);
//        System.out.println(hungrySingle1);


        //验证反射
//        Constructor<?>[] declaredConstructors = HungrySingle.class.getDeclaredConstructors();
//        Stream.of(declaredConstructors).forEach(declaredConstructor->{
//            //获取构造函数，进行实例化
//            try {
//                Object o = declaredConstructor.newInstance();
//                System.out.println(o);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        });

        //验证序列化
//        try {
//            try(FileOutputStream fileOutputStream = new FileOutputStream("HungrySingle.io")){
//                try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
//                    HungrySingle instance = HungrySingle.getInstance();
//                    System.out.println("序列化前:"+instance);
//                    objectOutputStream.writeObject(instance);
//                }
//            }
//
//            try(FileInputStream fileInputStream = new FileInputStream("HungrySingle.io")){
//                try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
//                    HungrySingle hungrySingle = (HungrySingle)objectInputStream.readObject();
//                    System.out.println("序列化后:"+hungrySingle);
//                }
//            }
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //验证线程
//        for (int i=0;i<10;i++){
//            new Thread(()->{
//                HungrySingle hungrySingle = HungrySingle.getInstance();
//                System.out.println(hungrySingle);
//            }).start();
//        }
    }

}
