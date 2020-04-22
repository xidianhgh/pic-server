package com.example.demo.service.aop;

import com.example.demo.config.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MI on 2019/9/8.
 */
public class AopMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AopConfig.class);

        AopTestt bean = ac.getBean(AopTestt.class);
        bean.aopTest();

        System.out.println("hello xidian");

//        final String  a=new String("xx");
//        String b="xx";
//        String c="x"+"x";
//        String d=new String(a);
//
//        System.gc();
//        System.out.println(a==d);
    }


    private static void test(){
        List<String> list= Arrays.asList("x","xx","a","b","ac");
        // Iterator
        for(String temp:list){
            if(temp.contains("a")){
                list.remove(temp);

            }
        }

    }

    //字节跳动面试题
    private static void scan(Node tree, int level) {

        System.out.print(tree.getData());

        if (tree.getLeft() != null && tree.getRight() != null) {
            return;
        }
        if (level % 2 == 0) {

            scan(tree.getLeft(), level + 1);
            scan(tree.getRight(), level + 1);
        } else {
            scan(tree.getRight(), level + 1);
            scan(tree.getLeft(), level + 1);
        }


    }

    private class Node {
        private Node left;
        private Node right;
        private String data;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }
}
