package com.example.demo.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class MobilePhoneUtil {

    public static void sendMsg() throws IOException {
        // 18710848088 的用户名是xidianhgh 邮箱是1031409343@qq.com
        // 18392527325 的用户名是xidianhgh@163.com 邮箱是xidianhgh@163.com
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        // 本站用户名 xidianhgh@163.com
        NameValuePair[] data = {new NameValuePair("Uid", "本站用户名"),

                // 接口安全秘钥 d41d8cd98f00b204e980
                new NameValuePair("Key", "接口安全秘钥"),

                new NameValuePair("smsMob", "18392527325"),

                new NameValuePair("smsText", "验证码：123 【皇家马德里足球俱乐部】")};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println("发送完成"); //打印返回消息状态
        System.out.println(result); //打印返回消息状态

        post.releaseConnection();
    }

//    public static void main(String[] args) throws IOException {
//        sendMsg();
//    }
}
