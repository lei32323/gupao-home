package cglib;

import sun.awt.image.PixelConverter;

import static sun.awt.image.PixelConverter.Argb.instance;

public class CgLibProxyTest {
    public static void main(String[] args) {
        MeiPoProxy meiPoProxy = new MeiPoProxy();
        ZhangSan instance = (ZhangSan)meiPoProxy.getInstance(ZhangSan.class);
        instance.FindObject();
    }
}
