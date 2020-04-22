package com.example.demo.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MI on 2019/6/22.
 */
public class AntPathRequestMatcher implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest request) {
        return false;
    }
}
