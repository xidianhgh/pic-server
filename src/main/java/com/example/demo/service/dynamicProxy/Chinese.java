package com.example.demo.service.dynamicProxy;

import lombok.Data;

/**
 * Created by MI on 2019/8/21.
 */
@Data
public class Chinese implements People ,Cloneable{
    private String name="Chinese name";

    @Override
    public String sayHello() {
        System.out.println("Chinese say hello");
        return "Chinese";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String sayName(){
        System.out.println(name);
        return null;
    }
}
