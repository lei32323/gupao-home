package com.gupaoedu.home.mvcframework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface DyRequestMapping {

    String value() default "";

}
