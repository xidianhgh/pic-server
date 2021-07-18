package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.TestEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by MI on 2019/11/2.
 */
@Service
@Lazy
//@Scope("prototype")
public class StudyService {
    private int n = 0;

    public String test() {
        String result = "I am " + this.getClass().getName();
        System.out.println(result);
        return String.valueOf(++n);

    }

    public static void main(String[] args) {
        TestEntity testEntity = new TestEntity();
        HashMap<String, Object> italymap = new HashMap<>();
        italymap.put("milan","ac");
        HashMap<String, Object> germap = new HashMap<>();
        germap.put("name","ooo");
        HashMap<String, Object> cmap = new HashMap<>();
        cmap.put("italy",italymap);
        cmap.put("germany",germap);

        HashMap<String, Object> map = new HashMap<>();
        map.put("country",cmap);
        map.put("abcDe", "peter");
        map.put("age", 98);
        System.out.println(JSON.toJSONString(map));
        testEntity = JSONObject.parseObject(JSON.toJSONString(map), TestEntity.class);
        System.out.println(testEntity);
    }

}
