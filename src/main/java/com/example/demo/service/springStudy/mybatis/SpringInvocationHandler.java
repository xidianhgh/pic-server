package com.example.demo.service.springStudy.mybatis;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by MI on 2019/10/4.
 */
public class SpringInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入invoke");
        method.getAnnotation(Autowired.class);
        if("toString".equalsIgnoreCase(method.getName())){
//            return proxy.getClass().getSimpleName();
            return proxy.getClass().getInterfaces()[0].getName();
        }
        return "selectList()";
    }
}
