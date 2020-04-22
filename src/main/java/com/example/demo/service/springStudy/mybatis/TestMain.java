package com.example.demo.service.springStudy.mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MI on 2019/10/3.
 */
public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(SpringConfig.class);
//      System.out.println(ac.getBean(TestA.class));
        //关闭循环引用
//        ac.setAllowCircularReferences(false);
//        System.out.println(ac.getBean("cityDao"));
        System.out.println(ac.getBean("test"));


    }



}
