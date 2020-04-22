package com.example.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MI on 2019/6/22.
 */
@Slf4j
public class HttpSecurity implements HandlerInterceptor{
    private List<Request> list=new ArrayList<>();
    @Data
    private class Request{
        private HttpMethod method;
        private String uri;
    }

    public HttpSecurity antMatchers(String uri){
        Request request=new Request();
        request.setUri(uri);
        list.add(request);
        return this;
    }

    public HttpSecurity antMatchers(HttpMethod method,String uri){
        Request request=new Request();
        request.setMethod(method);
        request.setUri(uri);
        list.add(request);
        return this;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("日志是："+list.get(0)+list.get(1));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
