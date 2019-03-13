package cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import sun.awt.image.PixelConverter;

import static sun.awt.image.PixelConverter.Argb.instance;

public class CgLibProxyTest {
    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"J:\\gupaoedu\\gupao-home\\home-proxy");

        MeiPoProxy meiPoProxy = new MeiPoProxy();
        ZhangSan instance = (ZhangSan)meiPoProxy.getInstance(ZhangSan.class);
        System.out.println(instance);
        instance.FindObject();
    }
}
