package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by MI on 2019/9/8.
 */
//@ComponentScans(value = {@ComponentScan("com.example.demo.service.aop")})
//如果启动了服务，以下的ComponentScan可以不用
@ComponentScan("com.example.demo.service.aop")
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {


}
