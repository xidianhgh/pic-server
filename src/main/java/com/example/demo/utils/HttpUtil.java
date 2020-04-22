package com.example.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by MI on 2019/7/17.
 */
public class HttpUtil {
    // https://www.cnblogs.com/zhao1949/p/9148813.html
    public static String sendHttpPost(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(body));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseContent);
        response.close();
        httpClient.close();
        return responseContent;
    }


    public static String sendHttpDelete(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpDelete httpDelete = new HttpDelete(uriBuilder.build());

        CloseableHttpResponse response = httpClient.execute(httpDelete);

        String httpResult = null;
        // 解析数据封装HttpResult
        if (response.getEntity() != null) {
            httpResult = EntityUtils.toString(response.getEntity(), "UTF-8");
        } else {
            httpResult = "";
        }
        response.close();
        httpClient.close();
        // 返回
        return httpResult;
    }

    public static String sendHttpGet(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // 3 使用HttpClient执行httpGet，相当于按回车，发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 4 解析结果，封装返回对象httpResult，相当于显示相应的结果
        // 状态码
        // response.getStatusLine().getStatusCode();
        // 响应体，字符串，如果response.getEntity()为空，下面这个代码会报错,所以解析之前要做非空的判断
        // EntityUtils.toString(response.getEntity(), "UTF-8");
        String httpResult = null;
        // 解析数据封装HttpResult
        if (response.getEntity() != null) {
            httpResult = EntityUtils.toString(response.getEntity(), "UTF-8");
        } else {
            httpResult = "";
        }

        // 返回
        return httpResult;
    }
}
