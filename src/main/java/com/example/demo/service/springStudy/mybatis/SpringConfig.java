package com.example.demo.service.springStudy.mybatis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MI on 2019/10/3.
 */
@Configuration
@ComponentScans({@ComponentScan("com.example.demo.service.springStudy")})
@ZiluScan("com.example.demo.service.springStudy.mapper")
public class SpringConfig {



}
