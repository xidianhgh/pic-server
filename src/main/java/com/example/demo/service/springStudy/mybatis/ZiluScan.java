package com.example.demo.service.springStudy.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by MI on 2019/10/4.
 */
@Import(SpringRegistar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZiluScan {
    String value();
}
