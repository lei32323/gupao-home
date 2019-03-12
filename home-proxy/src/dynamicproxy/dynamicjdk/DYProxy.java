package dynamicproxy.dynamicjdk;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class DYProxy {


    public static Object newProxyInstance(DYClassLoader loader,
                                          Class<?>[] interfaces,
                                          DYInvocationHandler h) {
        //1.动态生成.java文件
        StringBuffer sb = new StringBuffer();
        sb.append("package dynamicproxy.dynamicjdk;\r\n");
        sb.append("import dynamicproxy.dynamicjdk.IProsen;\r\n");
        sb.append("import dynamicproxy.dynamicjdk.DYInvocationHandler;\r\n");
        sb.append("import java.lang.reflect.Method;\r\n");
        sb.append("import dynamicproxy.dynamicjdk.DYProxy;\r\n");
        sb.append("import java.lang.reflect.UndeclaredThrowableException;\r\n");
        sb.append("public final class $Proxy0  implements " + interfaces[0].getName() + " {\r\n");

        Method[] methods = interfaces[0].getDeclaredMethods();
        for (int i = 1; i <= methods.length; i++) {
            sb.append("private static Method m" + i + ";\t\n");
        }
        sb.append("private DYInvocationHandler h; \r\t" );
        sb.append("public $Proxy0(DYInvocationHandler var1) {\r\n");
        sb.append("this.h = var1; \r\n");
        sb.append("}\r\n");

        for (int i = 1; i <= methods.length; i++) {
            Class<?>[] parameterTypes = methods[i-1].getParameterTypes();
            StringBuffer param = new StringBuffer();
            for (int ii = 1; i <= parameterTypes.length; i++) {
                param.append(parameterTypes[i-1].getName() + " var" + ii);
            }

            sb.append("public final " + methods[i-1].getReturnType().getName() + " " + methods[i-1].getName() + "(" + param.toString() + "){\t\n");
            sb.append("try{\t\n");
            if (!methods[i-1].getReturnType().getName().equals("void")) {
                sb.append("return (" + methods[i-1].getReturnType().getName() + ")super.h.invoke(this, m" + i + ", new Object[]{var" + i + "});\t\n");
            } else {
                sb.append("this.h.invoke(this, m" + i + ", (Object[])null);\t\n");
            }
            sb.append("} catch (Throwable e) {\t\n");
            sb.append(";\t\n");
            sb.append("}\t\n");
            sb.append("}\t\n");
        }

        sb.append(" static {\t\n");
        sb.append("try {\t\n");
        for (int i = 1; i <= methods.length; i++) {
            sb.append("m"+i+" = Class.forName(\""+methods[i-1].getDeclaringClass().getName()+"\").getMethod(\""+methods[i-1].getName()+"\");\t\n");
        }
        sb.append("} catch (Exception var2) {\t\n");
        sb.append("throw new RuntimeException();\t\n");
        sb.append("}\t\n");
        sb.append("}\t\n");



        sb.append("}\t\n");
        String java = sb.toString();

        //写出文件
        try {
            String filePath = DYProxy.class.getResource("").getPath();
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(java);
            fw.flush();
            fw.close();


            //进行动态编译java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manage,null,null,null,iterable);
            task.call();
            manage.close();

            //编译生成的.class文件加载到JVM中来
            Class proxyClass =  loader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(DYInvocationHandler.class);
//            f.delete();
           return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
