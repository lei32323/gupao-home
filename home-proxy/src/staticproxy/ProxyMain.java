package staticproxy;

public class ProxyMain {

    public static void main(String[] args) {
        Mother mother = new Mother(new Son());
        mother.FindObject();
    }

}
