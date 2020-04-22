package com.example.demo.service.dynamicProxy;

/**
 * Created by MI on 2019/10/28.
 */
public class MyLogHandler implements People {
    People people;

    public MyLogHandler(People people){
        this.people=people;
    }

    @Override
    public String sayHello() {
        System.out.println("log begin...");
        people.sayHello();
        System.out.println("log finish...");
        return "log";

    }
}
