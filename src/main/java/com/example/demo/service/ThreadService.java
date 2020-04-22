package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by MI on 2019/3/22.
 */
@Service
public class ThreadService extends Thread{
//    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
//    @Resource
//    private TestDao testDao;

    public Map testThread() throws ExecutionException, InterruptedException {
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<3;i++){
            int finalI = i;
            Future<String> future= threadPoolTaskExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "gooddd::: "+ finalI;
                }
            });
            map.put(String.valueOf(i),future.get());
        }


      return map;

    }

    public String test(){
        return this.getClass().getName();
    }

    @Override
    public void run() {
        System.out.println("执行了线程=="+this.getName());
    }

    @Async
    public void testAsync(){
        Thread thread=Thread.currentThread();
        System.out.println("开启新线程=="+thread.getName());
    }




}
