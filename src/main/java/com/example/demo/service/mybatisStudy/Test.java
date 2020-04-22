package com.example.demo.service.mybatisStudy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MI on 2019/9/25.
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac
                =new AnnotationConfigApplicationContext(Config.class);

//        Object bean = ac.getBean("&zl");

//        Object bean=ac.getBean("tiger");
//        System.out.println(bean.getClass());
        ac.getBean(ZlService.class);
        ac.getBean(ZlService.class).xx();
        ac.getBean(ZlService.class).xx();
        ac.getBean(ZlService.class).xx();



    }

}
