package com.example.demo.controller;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.Data;

import java.lang.reflect.Type;

@Data
public class Italy implements ObjectDeserializer {
    private String milan;

    @Override
    public Country deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Country country=new Country();
        Germany germany=new Germany();
        germany.setName("qqq");
        country.setGermany(germany);
        country.setItaly(new Italy());
        TestEntity testEntity=new TestEntity();
        testEntity.setCountry(country);
        return country;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
