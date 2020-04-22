package com.example.demo.entity;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by MI on 2019/6/22.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    @AliasFor("operation")
    String value() default "";
    @AliasFor("value")
    String operation() default "";
    String module() default "";



}
