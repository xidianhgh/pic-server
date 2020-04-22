package com.example.demo.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MI on 2019/6/22.
 */
public interface RequestMatcher {
    boolean matches(HttpServletRequest request);

}
