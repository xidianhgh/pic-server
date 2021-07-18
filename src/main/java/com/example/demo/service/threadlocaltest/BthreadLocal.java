package com.example.demo.service.threadlocaltest;

public class BthreadLocal extends ThreadLocal<String>  {
    private static BthreadLocal instance=new BthreadLocal();
    public static BthreadLocal getInstance(){
        return instance;
    }
}
