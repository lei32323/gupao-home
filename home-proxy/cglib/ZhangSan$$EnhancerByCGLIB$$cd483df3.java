// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   <generated>

package cglib;

import java.lang.reflect.Method;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.*;

// Referenced classes of package cglib:
//            ZhangSan

public class ZhangSan$$EnhancerByCGLIB$$cd483df3 extends ZhangSan
    implements Factory
{

    static void CGLIB$STATICHOOK1()
    {
        Method amethod[];
        Method amethod1[];
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class class1 = Class.forName("cglib.ZhangSan$$EnhancerByCGLIB$$cd483df3");
        Class class2;
        amethod = ReflectUtils.findMethods(new String[] {
            "FindObject", "()V"
        }, (class2 = Class.forName("cglib.ZhangSan")).getDeclaredMethods());
        Method[] _tmp = amethod;
        CGLIB$FindObject$0$Method = amethod[0];
        CGLIB$FindObject$0$Proxy = MethodProxy.create(class2, class1, "()V", "FindObject", "CGLIB$FindObject$0");
        amethod1 = ReflectUtils.findMethods(new String[] {
            "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"
        }, (class2 = Class.forName("java.lang.Object")).getDeclaredMethods());
        Method[] _tmp1 = amethod1;
        CGLIB$equals$1$Method = amethod1[0];
        CGLIB$equals$1$Proxy = MethodProxy.create(class2, class1, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
        CGLIB$toString$2$Method = amethod1[1];
        CGLIB$toString$2$Proxy = MethodProxy.create(class2, class1, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
        CGLIB$hashCode$3$Method = amethod1[2];
        CGLIB$hashCode$3$Proxy = MethodProxy.create(class2, class1, "()I", "hashCode", "CGLIB$hashCode$3");
        CGLIB$clone$4$Method = amethod1[3];
        CGLIB$clone$4$Proxy = MethodProxy.create(class2, class1, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
    }

    final void CGLIB$FindObject$0()
    {
        super.FindObject();
    }

    public final void FindObject()
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 37;
           goto _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_21;
_L4:
        break MISSING_BLOCK_LABEL_37;
        this;
        CGLIB$FindObject$0$Method;
        CGLIB$emptyArgs;
        CGLIB$FindObject$0$Proxy;
        intercept();
        return;
        super.FindObject();
        return;
    }

    final boolean CGLIB$equals$1(Object obj)
    {
        return super.equals(obj);
    }

    public final boolean equals(Object obj)
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 57;
           goto _L3 _L4
_L3:
        this;
        CGLIB$equals$1$Method;
        new Object[] {
            obj
        };
        CGLIB$equals$1$Proxy;
        intercept();
        JVM INSTR dup ;
        JVM INSTR ifnonnull 50;
           goto _L5 _L6
_L5:
        JVM INSTR pop ;
        false;
          goto _L7
_L6:
        (Boolean);
        booleanValue();
_L7:
        return;
_L4:
        return super.equals(obj);
    }

    final String CGLIB$toString$2()
    {
        return super.toString();
    }

    public final String toString()
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 40;
           goto _L3 _L4
_L3:
        this;
        CGLIB$toString$2$Method;
        CGLIB$emptyArgs;
        CGLIB$toString$2$Proxy;
        intercept();
        (String);
        return;
_L4:
        return super.toString();
    }

    final int CGLIB$hashCode$3()
    {
        return super.hashCode();
    }

    public final int hashCode()
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 52;
           goto _L3 _L4
_L3:
        this;
        CGLIB$hashCode$3$Method;
        CGLIB$emptyArgs;
        CGLIB$hashCode$3$Proxy;
        intercept();
        JVM INSTR dup ;
        JVM INSTR ifnonnull 45;
           goto _L5 _L6
_L5:
        JVM INSTR pop ;
        0;
          goto _L7
_L6:
        (Number);
        intValue();
_L7:
        return;
_L4:
        return super.hashCode();
    }

    final Object CGLIB$clone$4()
        throws CloneNotSupportedException
    {
        return super.clone();
    }

    protected final Object clone()
        throws CloneNotSupportedException
    {
        CGLIB$CALLBACK_0;
        if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
        JVM INSTR pop ;
        CGLIB$BIND_CALLBACKS(this);
        CGLIB$CALLBACK_0;
_L2:
        JVM INSTR dup ;
        JVM INSTR ifnull 37;
           goto _L3 _L4
_L3:
        this;
        CGLIB$clone$4$Method;
        CGLIB$emptyArgs;
        CGLIB$clone$4$Proxy;
        intercept();
        return;
_L4:
        return super.clone();
    }

    public static MethodProxy CGLIB$findMethodProxy(Signature signature)
    {
        String s = signature.toString();
        s;
        s.hashCode();
        JVM INSTR lookupswitch 5: default 120
    //                   -508378822: 60
    //                   -447908355: 72
    //                   1826985398: 84
    //                   1913648695: 96
    //                   1984935277: 108;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        "clone()Ljava/lang/Object;";
        equals();
        JVM INSTR ifeq 121;
           goto _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_121;
_L7:
        return CGLIB$clone$4$Proxy;
_L3:
        "FindObject()V";
        equals();
        JVM INSTR ifeq 121;
           goto _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_121;
_L9:
        return CGLIB$FindObject$0$Proxy;
_L4:
        "equals(Ljava/lang/Object;)Z";
        equals();
        JVM INSTR ifeq 121;
           goto _L11 _L12
_L12:
        break MISSING_BLOCK_LABEL_121;
_L11:
        return CGLIB$equals$1$Proxy;
_L5:
        "toString()Ljava/lang/String;";
        equals();
        JVM INSTR ifeq 121;
           goto _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_121;
_L13:
        return CGLIB$toString$2$Proxy;
_L6:
        "hashCode()I";
        equals();
        JVM INSTR ifeq 121;
           goto _L15 _L16
_L16:
        break MISSING_BLOCK_LABEL_121;
_L15:
        return CGLIB$hashCode$3$Proxy;
_L1:
        JVM INSTR pop ;
        return null;
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback acallback[])
    {
        CGLIB$THREAD_CALLBACKS.set(acallback);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback acallback[])
    {
        CGLIB$STATIC_CALLBACKS = acallback;
    }

    private static final void CGLIB$BIND_CALLBACKS(Object obj)
    {
        ZhangSan$$EnhancerByCGLIB$$cd483df3 zhangsan$$enhancerbycglib$$cd483df3 = (ZhangSan$$EnhancerByCGLIB$$cd483df3)obj;
        if(zhangsan$$enhancerbycglib$$cd483df3.CGLIB$BOUND) goto _L2; else goto _L1
_L1:
        Object obj1;
        zhangsan$$enhancerbycglib$$cd483df3.CGLIB$BOUND = true;
        obj1 = CGLIB$THREAD_CALLBACKS.get();
        obj1;
        if(obj1 != null) goto _L4; else goto _L3
_L3:
        JVM INSTR pop ;
        CGLIB$STATIC_CALLBACKS;
        if(CGLIB$STATIC_CALLBACKS != null) goto _L4; else goto _L5
_L5:
        JVM INSTR pop ;
          goto _L2
_L4:
        (Callback[]);
        zhangsan$$enhancerbycglib$$cd483df3;
        JVM INSTR swap ;
        0;
        JVM INSTR aaload ;
        (MethodInterceptor);
        CGLIB$CALLBACK_0;
_L2:
    }

    public Object newInstance(Callback acallback[])
    {
        CGLIB$SET_THREAD_CALLBACKS(acallback);
        CGLIB$SET_THREAD_CALLBACKS(null);
        return new ZhangSan$$EnhancerByCGLIB$$cd483df3();
    }

    public Object newInstance(Callback callback)
    {
        CGLIB$SET_THREAD_CALLBACKS(new Callback[] {
            callback
        });
        CGLIB$SET_THREAD_CALLBACKS(null);
        return new ZhangSan$$EnhancerByCGLIB$$cd483df3();
    }

    public Object newInstance(Class aclass[], Object aobj[], Callback acallback[])
    {
        CGLIB$SET_THREAD_CALLBACKS(acallback);
        JVM INSTR new #2   <Class ZhangSan$$EnhancerByCGLIB$$cd483df3>;
        JVM INSTR dup ;
        aclass;
        aclass.length;
        JVM INSTR tableswitch 0 0: default 35
    //                   0 28;
           goto _L1 _L2
_L2:
        JVM INSTR pop ;
        ZhangSan$$EnhancerByCGLIB$$cd483df3();
          goto _L3
_L1:
        JVM INSTR pop ;
        throw new IllegalArgumentException("Constructor not found");
_L3:
        CGLIB$SET_THREAD_CALLBACKS(null);
        return;
    }

    public Callback getCallback(int i)
    {
        CGLIB$BIND_CALLBACKS(this);
        this;
        i;
        JVM INSTR tableswitch 0 0: default 30
    //                   0 24;
           goto _L1 _L2
_L2:
        CGLIB$CALLBACK_0;
          goto _L3
_L1:
        JVM INSTR pop ;
        null;
_L3:
        return;
    }

    public void setCallback(int i, Callback callback)
    {
        switch(i)
        {
        case 0: // '\0'
            CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
            break;
        }
    }

    public Callback[] getCallbacks()
    {
        CGLIB$BIND_CALLBACKS(this);
        this;
        return (new Callback[] {
            CGLIB$CALLBACK_0
        });
    }

    public void setCallbacks(Callback acallback[])
    {
        this;
        acallback;
        JVM INSTR dup2 ;
        0;
        JVM INSTR aaload ;
        (MethodInterceptor);
        CGLIB$CALLBACK_0;
    }

    private boolean CGLIB$BOUND;
    public static Object CGLIB$FACTORY_DATA;
    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static final Callback CGLIB$STATIC_CALLBACKS[];
    private MethodInterceptor CGLIB$CALLBACK_0;
    private static Object CGLIB$CALLBACK_FILTER;
    private static final Method CGLIB$FindObject$0$Method;
    private static final MethodProxy CGLIB$FindObject$0$Proxy;
    private static final Object CGLIB$emptyArgs[];
    private static final Method CGLIB$equals$1$Method;
    private static final MethodProxy CGLIB$equals$1$Proxy;
    private static final Method CGLIB$toString$2$Method;
    private static final MethodProxy CGLIB$toString$2$Proxy;
    private static final Method CGLIB$hashCode$3$Method;
    private static final MethodProxy CGLIB$hashCode$3$Proxy;
    private static final Method CGLIB$clone$4$Method;
    private static final MethodProxy CGLIB$clone$4$Proxy;

    static 
    {
        CGLIB$STATICHOOK1();
    }

    public ZhangSan$$EnhancerByCGLIB$$cd483df3()
    {
        CGLIB$BIND_CALLBACKS(this);
    }
}
