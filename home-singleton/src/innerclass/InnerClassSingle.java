package innerclass;

public class InnerClassSingle {

    private InnerClassSingle(){}


    public static InnerClassSingle getInstance(){
        return InnerClass.innerClassSingle;
    }


    private static class InnerClass{
        private static final InnerClassSingle innerClassSingle = new InnerClassSingle();
    }

    //��ֹ�����л�
    private Object readResolve(){
        return InnerClass.innerClassSingle;
    }

}
