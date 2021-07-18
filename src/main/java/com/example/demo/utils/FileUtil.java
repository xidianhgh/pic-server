package com.example.demo.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Created by MI on 2019/4/20.
 */
public class FileUtil {
    public static boolean createDir(String destDir) {
        File dir = new File(destDir);
        if (dir.exists()) {
            return false;
        }
        if (dir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    public static String copyFile(File origin, String dir, String realName) throws IOException {
        File file = new File(dir, realName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileUtils.copyFile(origin, file);
        return realName;
    }

    public static File asFile(InputStream inputStream) throws IOException{
        File tmp = File.createTempFile("lzq", ".tmp", new File("C:\\"));
        OutputStream os = new FileOutputStream(tmp);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        return tmp;
    }

    public static void inputstream2File(InputStream ins,File file) throws Exception {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

//    public static void main(String[] args) throws IOException {
//        String f="QQ截图20190316224326.png";
//        String fPath="C:\\Users\\MI\\Desktop\\"+f;
//        File src=new File(fPath);
//        String destination="C:\\Users\\MI\\Desktop\\0909";
////        createDir(destination);
//        copyFile(src,destination,f);
//    }
public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    List<Integer> list=new ArrayList<>();
    System.out.println("请输入10个整数：");
    for(int i = 0; i < 5; i++)
    {
        list.add(scan.nextInt());
    }
    scan.close();

    //逆序排序
//    Collections.sort(list,Collections.reverseOrder());
    Collections.sort(list, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1.intValue()==3){
                return 1;
            }
            if(o2.intValue()==3){
                return -1;
            }
            return o1-o2;
        }
    });
    //输出排序结果
    System.out.println(list);
    Collections.sort(list);

}
}
