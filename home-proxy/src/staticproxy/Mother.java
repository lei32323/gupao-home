package staticproxy;

public class Mother {


    private IPeople iPeople ;

    //����
    public Mother(IPeople iPeople) {
        this.iPeople = iPeople;
    }

    public void FindObject() {
        System.out.println("�Ҷ���Ҫ����");
        iPeople.FindObject();
        System.out.println("�ҵ�����");

    }
}
