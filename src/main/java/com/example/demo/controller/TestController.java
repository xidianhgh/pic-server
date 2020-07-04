package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/test")
public class TestController {
    private ReentrantLock lock =new ReentrantLock(true);
    private static int n = 0;

    @GetMapping("/first")
    public Map<String, Object> test1() throws InterruptedException {

//        lock.lock();
        n++;
        if (n % 3 == 0) {
            Thread.sleep(10000);

        }
        System.out.println("n= " + n);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("number", n);
//        lock.unlock();
        return map;
    }
}
