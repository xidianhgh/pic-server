package com.example.demo.service.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by MI on 2019/8/21.
 */
public class PeopleInvocationHandle implements InvocationHandler {

    private Object obj;
    public Object getInstance(People people){
        this.obj = people;
        Class clazz = obj.getClass();
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       System.out.println("------我是动态代理----------");
       Object invoke=method.invoke(obj,args);
       System.out.println("---------------END---------");

        return null;
    }
}
