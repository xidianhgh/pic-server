package com.example.demo.service.threadlocaltest;

public class Test {
    public static void main(String[] args) {
//        AthreadLocal athreadLocal=new AthreadLocal();
//        athreadLocal.set("Xx");
//        BthreadLocal bthreadLocal=new BthreadLocal();
//        bthreadLocal.set("aa");
//        String a=athreadLocal.get();
//        String b=bthreadLocal.get();
        CthreadLocal.set("ccc");
        DthreadLocal.set("ddd");
        String a=CthreadLocal.get();
        String b=DthreadLocal.get();
        System.out.println(a);
        System.out.println(b);
    }
}
