package com.example.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2019/11/2.
 */
@Service
@Lazy
//@Scope("prototype")
public class StudyService {
    private int n=0;

    public String test(){
        String result="I am "+this.getClass().getName();
        System.out.println(result);
        return String.valueOf(++n);

    }
}
