package com.example.demo.config;

import com.example.demo.service.MockLoginService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created by MI on 2019/5/19.
 */
@Configuration
public class WebmvcConfig implements WebMvcConfigurer {
    @Resource
    private MockLoginService mockLoginService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mockLoginService).addPathPatterns("/**");
    }
}
