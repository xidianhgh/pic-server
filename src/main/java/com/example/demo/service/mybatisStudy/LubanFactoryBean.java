package com.example.demo.service.mybatisStudy;

import com.example.demo.entity.Tiger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by MI on 2019/9/25.
 */
@Component("zl")
public class LubanFactoryBean implements FactoryBean {
//    public class LubanFactoryBean {

        @Override
    public Object getObject() throws Exception {
        return new Tiger();
    }

    @Override
    public Class<?> getObjectType() {
        return Tiger.class;
    }
}
