package org.aopalliance.intercept;

import org.springframework.aop.aspectj.JoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

import java.lang.reflect.Method;

public interface MethodInvocation extends JoinPoint {

    Method getMethod();
    Object proceed() throws Throwable;

//    default MethodInvocation invocableClone() throws CloneNotSupportedException {
//        Object[] cloneArguments = this.getArgs();
//        if (this.getArgs().length > 0) {
//            // Build an independent copy of the arguments array.
//            cloneArguments = new Object[this.getArgs().length];
//            System.arraycopy(this.getArgs(), 0, cloneArguments, 0, this.getArgs().length);
//        }
//        ReflectiveMethodInvocation clone = (ReflectiveMethodInvocation) this.clone();
//        clone.setArguments(cloneArguments);
//        return clone;
//    }
}
