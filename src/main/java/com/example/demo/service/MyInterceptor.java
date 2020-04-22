package com.example.demo.service;

import com.example.demo.entity.HttpSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by MI on 2019/5/24.
 */
@Component
public class MyInterceptor implements WebMvcConfigurer {
    @Autowired
    MockLoginService mockLoginService;
    @Autowired
    HttpSecurity httpSecurity;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mockLoginService);
        registry.addInterceptor(httpSecurity);

    }
}
