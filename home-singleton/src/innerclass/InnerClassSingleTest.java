package innerclass;

public class InnerClassSingleTest {

    public static void main(String[] args) {

        for (int i =0;i<100;i++){

          InnerClassSingle instance = InnerClassSingle.getInstance();
          System.out.println(instance);
        }

    }

}
