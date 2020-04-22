package com.example.demo.service.dynamicProxy;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by MI on 2019/8/21.
 */
@Data
public class Germany implements People ,Cloneable,Serializable{
    private String name="Germany name";

    public Germany(){

    }
    public Germany(String x){
        name=x;
    }

    @Override
    public String sayHello() {
        System.out.println("Germany say hello");
        return null;
    }

    public String sayName(){
        System.out.println(name);
        return null;
    }
}
