package com.example.demo.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;

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

    public static void main(String[] args) throws IOException {
        String f="QQ截图20190316224326.png";
        String fPath="C:\\Users\\MI\\Desktop\\"+f;
        File src=new File(fPath);
        String destination="C:\\Users\\MI\\Desktop\\0909";
//        createDir(destination);
        copyFile(src,destination,f);
    }
}
