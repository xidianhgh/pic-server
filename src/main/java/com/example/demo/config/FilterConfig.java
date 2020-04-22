//package com.example.demo.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * Created by MI on 2019/5/19.
// */
////@Configuration
////@ServletComponentScan
////@WebFilter(urlPatterns = "/hello", filterName = "f")
//@Slf4j
//public class FilterConfig implements Filter {
//    @Bean
//    public ThreadPoolTaskExecutor getThreadPool() {
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setMaxPoolSize(10);
//        threadPoolTaskExecutor.setCorePoolSize(5);
//        return threadPoolTaskExecutor;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        int timeout = servletRequest.getServletContext().getSessionTimeout();
//        log.info("session timeout:" + timeout);
//    }
//
//    @Override
//    public void destroy() {
//        log.info("session timeout:");
//    }
//
////    @Resource
////    private MockLoginService mockLoginService;
////    @Bean
////    public WebMvcConfigurer webMvcConfigurer() {
////        return new WebMvcConfigurer() {
////            /**
////             * 添加拦截器
////             * @param registry
////             */
////            @Override
////            public void addInterceptors(InterceptorRegistry registry) {
////                registry.addInterceptor(mockLoginService).addPathPatterns("/**");
////            }
////        };
////    }
//}
