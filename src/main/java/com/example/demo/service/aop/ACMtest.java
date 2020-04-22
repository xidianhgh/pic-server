package com.example.demo.service.aop;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by MI on 2019/11/1.
 */
public class ACMtest {
    static volatile Map<String,Object> map=new HashMap<>();
    static ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();
    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<5;i++){
            int k=i;
            new Thread(()->put(k+"x",k),i+"").start();

        }

        for(int i=0;i<5;i++){
            int k=i;
            new Thread(()->get(k+"x"),i+"").start();
        }
    }

    private static void put(String key,Object value){
        rwlock.writeLock().lock();

        System.out.println(key+"开始写入");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(key+"写入完成");
        map.put(key,value);
        rwlock.writeLock().unlock();

    }

    private static Object get(String key){
        rwlock.readLock().lock();

        System.out.println(key+"开始读入");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object value= map.get(key);
        System.out.println(key+"读入完成："+value);
        rwlock.readLock().unlock();

        return value;
    }


    public static void test(String str) {
        str = str + "world";
        System.out.println(str);
        int[] a = new int[1024];
    }
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNext()) {
//            String text = scanner.next();
//            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
//
//            for (int i = 0; i < text.length(); i++) {
//                char temp = text.charAt(i);
//                String c = String.valueOf(temp);
//                if (map.containsKey(c)) {
//                    Integer count = map.get(c);
//                    map.put(c, count + 1);
//
//                } else {
//                    map.put(c, 1);
//                }
//            }
//
//            StringBuilder stringBuilder = new StringBuilder();
//            int size = map.size();
//            while (size != 0) {
//                String maxS = maxCount(map);
//                Integer max = map.get(maxS);
//
//                map.remove(maxS);
//                size--;
//                stringBuilder.append(maxS+":"+max);
//
//                stringBuilder.append(";");
//
//            }
//            System.out.println(stringBuilder.toString());
//        }
//
//    }

    private static String maxCount(LinkedHashMap<String, Integer> map) {
        Integer max = 0;
        String maxS = "";
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Integer temp = map.get(key);
            if (temp > max) {
                max = temp;
                maxS = key;
            }
        }
        return maxS;
    }

//    public static Node buildTree(){
//
//        Scanner scanner=new Scanner(System.in);
//
//        String in=scanner.next();
//        Node tree=new Node();
//        if(!" ".equals(in)){
//            tree.setData(in);
//        }else {
//            tree.setLeft(null);
//            tree.setRight(null);
//        }
//
//    }

    private static class Node {

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


    public static void quickSort(int[] arr, int low, int high) {
        // ok
        int i = low;
        int j = high;
        if (i >= j) {
            return;
        }
        int base = arr[i];
        int position = i;
        while (i < j) {
            while (arr[j] > base && i < j) {
                j--;
            }

            //swap
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;

            position = j;


            while (arr[i] < base && i < j) {
                i++;
            }
            //swap
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;

            position = i;
        }
        quickSort(arr, low, position - 1);
        quickSort(arr, position + 1, high);

    }

    public static void quickSortNet(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSortNet(arr, low, j - 1);
        //递归调用右半数组
        quickSortNet(arr, j + 1, high);
    }


}
