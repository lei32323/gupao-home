package staticproxy;

public class Mother {


    private IPeople iPeople ;

    //儿子
    public Mother(IPeople iPeople) {
        this.iPeople = iPeople;
    }

    public void FindObject() {
        System.out.println("找儿子要需求");
        iPeople.FindObject();
        System.out.println("找到对象");

    }
}
