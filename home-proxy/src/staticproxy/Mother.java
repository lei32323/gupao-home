package staticproxy;

public class Mother {


    private IProsen iPeople ;

    //儿子
    public Mother(IProsen iPeople) {
        this.iPeople = iPeople;
    }

    public void FindObject() {
        System.out.println("找儿子要需求");
        iPeople.FindObject();
        System.out.println("找到对象");

    }
}
