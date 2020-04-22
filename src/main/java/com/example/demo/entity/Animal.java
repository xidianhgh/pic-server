package com.example.demo.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by MI on 2019/7/17.
 */
@Slf4j
public abstract class Animal {
    public String eat(){
        log.info("吃");
        return "eat";
    }

    public abstract String run();
}
