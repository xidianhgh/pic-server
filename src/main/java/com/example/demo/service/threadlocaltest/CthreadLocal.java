package com.example.demo.service.threadlocaltest;

public class CthreadLocal {
    private static final ThreadLocal<String> tl=new ThreadLocal<>();

    public static String get(){
        return tl.get();
    }

    public static void set(String s){
        tl.set(s);
    }
}
