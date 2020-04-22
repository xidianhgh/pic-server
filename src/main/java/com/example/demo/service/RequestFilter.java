package com.example.demo.service;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by MI on 2019/5/24.
 */
@Component
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置请求响应字符编码
//        String charset="UTF-8";
//        servletRequest.setCharacterEncoding(charset);
//        servletResponse.setCharacterEncoding(charset);
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        filterChain.doFilter(httpServletRequest, servletResponse);




    }

    @Override
    public void destroy() {

    }
}
