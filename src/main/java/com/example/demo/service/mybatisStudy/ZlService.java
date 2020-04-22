package com.example.demo.service.mybatisStudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2019/10/11.
 */
@Service
//@Scope("prototype")
public class ZlService {

//    @Autowired
//    private Zl zlb;
    @Autowired
    ApplicationContext applicationContext;

    public ZlService(){
        System.out.println("构造方法constructor ZlService");
    }

    public void xx(){
        System.out.println(applicationContext.getBean(Zlb.class).hashCode());
    }
}
