package com.example.demo.service.dynamicProxy;

/**
 * Created by MI on 2019/10/28.
 */
public class MyTransactionHandler implements People {
    People people;

    public MyTransactionHandler(People people){
        this.people=people;
    }

    @Override
    public String sayHello() {

        System.out.println("transaction begin...");
        people.sayHello();
        System.out.println("transaction finish...");
        return "transaction";
    }
}
