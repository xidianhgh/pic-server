package com.example.demo.service.deepcopy;

import org.springframework.util.SerializationUtils;

public class Test {

    public static void main(String[] args) {
        Club club=new Club();
        club.setClub("x");

        DeepCloneUtil deepCloneUtil=new DeepCloneUtil();

//        Club b=(Club) deepCloneUtil.deepClone(club);
//        b.setClub("q");

        byte[] serialize = SerializationUtils.serialize(club);
        Club b=(Club) SerializationUtils.deserialize(serialize);
        b.setClub("q");

        System.out.println(club.getClub());
        System.out.println(b.getClub());

    }

}
