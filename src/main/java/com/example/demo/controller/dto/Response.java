package com.example.demo.controller.dto;

import lombok.Data;

/**
 * Created by MI on 2019/7/5.
 */
@Data
public class  Response<T> {
    private String code;
    private String msg;
    private T data;

    public static <T>Response ok(T data){
        Response response=new Response();
        response.setCode("200");
        response.setMsg("操作成功");
        response.setData(data);
        return response;

    }
}
