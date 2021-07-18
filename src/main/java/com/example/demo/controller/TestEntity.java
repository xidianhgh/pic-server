package com.example.demo.controller;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TestEntity{
    public void setAbcDe(String abcDe) {
        this.abcDe = abcDe;
    }

    @JSONField(deserializeUsing = Italy.class)
    private Country country;
    private String abcDe;
    private int age;
//    @Override
//    public int compareTo(TestEntity o) {
////        return this.age-o.getAge();
//        return -this.age+o.getAge();
//    }
}
