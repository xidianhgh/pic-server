package com.example.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2019/11/2.
 */
@Service
@Lazy
@Scope("prototype")
public class StudyService {

    public String test(){
        String result="I am "+this.getClass().getName();
        System.out.println(result);
        return result;
    }
}
