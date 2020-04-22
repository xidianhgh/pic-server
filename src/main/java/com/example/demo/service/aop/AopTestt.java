package com.example.demo.service.aop;

import org.springframework.stereotype.Component;

/**
 * Created by MI on 2019/9/8.
 */
@Component
public class AopTestt {

    public String aopTest(){
        System.out.println("query db");
        return "aop test";
    }




}
