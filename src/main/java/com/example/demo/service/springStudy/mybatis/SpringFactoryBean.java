package com.example.demo.service.springStudy.mybatis;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * Created by MI on 2019/10/4.
 */

public class SpringFactoryBean implements FactoryBean {

    public SpringFactoryBean(){
        System.out.println("default构造方法");
    }
    public SpringFactoryBean (Class interfaceMapper){
        System.out.println("初始化FactoryBean");
        this.interfaceMapper=interfaceMapper;
    }
    Class interfaceMapper;
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(interfaceMapper.getClassLoader(),new Class[]{interfaceMapper},
                new SpringInvocationHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceMapper;
    }
}
