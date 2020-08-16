package com.example.demo.controller;

import com.example.demo.service.StudyService;
import com.example.demo.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/test")
// singleton 单例模式，prototype原型模式
@Scope("prototype")
public class TestController {
    private ReentrantLock lock = new ReentrantLock(true);
    //    private static int n = 0;
    //controller默认单例，prototype改为原型，并发问题也可以使用ThreadLocal
    private int n = 0;
    @Autowired
    StudyService studyService;

    @GetMapping("/first")
    public Map<String, Object> test1() throws InterruptedException {

        String currentTime = TimeUtil.getCurrentTime();
        lock.lock();
        n++;
        if (n % 3 == 0) {
            Thread.sleep(1);

        }
        System.out.println("n= " + n + " " + currentTime);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("number", n);
        map.put("studyServiceNumber",studyService.test());
        lock.unlock();
        return map;
    }
}
