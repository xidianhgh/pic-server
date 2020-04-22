package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MI on 2019/3/24.
 */
public class TimeUtil {
    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
