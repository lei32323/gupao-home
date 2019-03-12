package cglib;

import staticproxy.IProsen;

public class ZhangSan implements IProsen {
    @Override
    public void FindObject() {
        System.out.println("是个女的就好");
    }
}
