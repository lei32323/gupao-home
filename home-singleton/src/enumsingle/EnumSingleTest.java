package enumsingle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * @Auther: wanglei
 * @Date: 2019/3/10 19:45
 * @Description:
 */
public class EnumSingleTest {

    public static void main(String[] args) {
//
        EnumSingle instance = EnumSingle.getInstance();
        System.out.println(instance);
//
//        EnumSingle instance1 = EnumSingle.getInstance();
//        System.out.println(instance1);

        //反射
//        Constructor<?>[] declaredConstructors = EnumSingle.class.getDeclaredConstructors();
//
//        Stream.of(declaredConstructors).forEach(declaredConstructor->{
//            declaredConstructor.setAccessible(true);
//            try {
//                declaredConstructor.newInstance();
//            }  catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
        try{


            try(FileOutputStream fileOutputStream = new FileOutputStream("EnumSingle.io")){
                try(ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream)){
                    objectInputStream.writeObject(instance);
                }
            }

            try(FileInputStream fileInputStream = new FileInputStream("EnumSingle.io")){
                try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                    Object o = objectInputStream.readObject();
                    System.out.println(o);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
