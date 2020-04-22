package com.example.demo.service.springStudy.mybatis;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by MI on 2019/10/4.
 */
public class SpringRegistar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder beanDefinitionBuilder=BeanDefinitionBuilder.genericBeanDefinition(SpringFactoryBean.class);

        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionBuilder.addConstructorArgValue("com.example.demo.service.springStudy.mapper.MybatisMapper");

//        String packagee="com.example.demo.service.springStudy.mapper.MybatisMapper";
//        beanDefinition.getPropertyValues().add("interfaceMapper",packagee);
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.example.demo.service.springStudy.mapper.MybatisMapper");

        beanDefinitionRegistry.registerBeanDefinition("cityDao",beanDefinition);
    }
}
