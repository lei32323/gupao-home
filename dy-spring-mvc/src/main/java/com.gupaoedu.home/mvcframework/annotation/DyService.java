package com.gupaoedu.home.mvcframework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DyService {

    String value() default "";

}
