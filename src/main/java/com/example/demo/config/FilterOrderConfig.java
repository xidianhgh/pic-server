//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by MI on 2019/6/7.
// */
//@Configuration
//public class FilterOrderConfig {
//    @Autowired
//    HttpServletResponseFilter responseFilter;
//
//    @Bean
//    public FilterRegistrationBean buildAFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setOrder(22);
//        filterRegistrationBean.setFilter(responseFilter);
//        filterRegistrationBean.setName("filter1");
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }
//
//}
