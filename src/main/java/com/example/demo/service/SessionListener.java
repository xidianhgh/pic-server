package com.example.demo.service;

import com.example.demo.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by MI on 2019/6/16.
 */
@Component
@Slf4j
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
     HttpSession session= se.getSession();
//     session.setMaxInactiveInterval(3*60); //minute
        session.setMaxInactiveInterval(1); //second

        log.info("sessionId:"+session.getId()+"创建"+ TimeUtil.getCurrentTime());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session= se.getSession();
        //会延迟大概一分钟左右
        log.info("sessionId:"+session.getId()+"销毁"+TimeUtil.getCurrentTime());
//        log.info("session 销毁");

    }
}
