package com.gupaoedu.home.mvcframework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface DyRequestParam {

    String value() default "";

}
