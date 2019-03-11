package staticproxy;

import staticproxy.IPeople;

public class Son implements IPeople {


    @Override
    public void FindObject() {
        System.out.println("要求：是个女的就可以了");
    }
}
