package staticproxy;

public class Mother {


    private IProsen iPeople ;

    //����
    public Mother(IProsen iPeople) {
        this.iPeople = iPeople;
    }

    public void FindObject() {
        System.out.println("�Ҷ���Ҫ����");
        iPeople.FindObject();
        System.out.println("�ҵ�����");

    }
}
