package com.example.demo.controller.dto;

import lombok.Data;

/**
 * Created by MI on 2019/7/5.
 */
@Data
public class HttpResult {
    // 响应的状态码
    private int code;

    // 响应的响应体
    private String body;

    public HttpResult(int code,String body){
        this.code=code;
        this.body=body;
    }
}
