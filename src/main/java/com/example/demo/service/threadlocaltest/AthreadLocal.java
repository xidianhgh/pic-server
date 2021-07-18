package com.example.demo.service.threadlocaltest;

public class AthreadLocal extends ThreadLocal<String> {
    private static AthreadLocal instance=new AthreadLocal();
    public static AthreadLocal getInstance(){
        return instance;
    }


}
