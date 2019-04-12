package com.dy.spring.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default "";
}
