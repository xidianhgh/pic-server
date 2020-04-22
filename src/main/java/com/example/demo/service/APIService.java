package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.dto.HttpResult;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MI on 2019/7/5.
 */
@Service
public class APIService {
    private CloseableHttpClient httpClient;

    public APIService() {
        // 1 创建HttpClinet，相当于打开浏览器
        this.httpClient = HttpClients.createDefault();
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {

        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 判断参数map是否为非空
        if (map != null) {
            // 遍历参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 设置参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // 3 使用HttpClient执行httpGet，相当于按回车，发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 4 解析结果，封装返回对象httpResult，相当于显示相应的结果
        // 状态码
        // response.getStatusLine().getStatusCode();
        // 响应体，字符串，如果response.getEntity()为空，下面这个代码会报错,所以解析之前要做非空的判断
        // EntityUtils.toString(response.getEntity(), "UTF-8");
        HttpResult httpResult = null;
        // 解析数据封装HttpResult
        if (response.getEntity() != null) {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
        }

        // 返回
        return httpResult;
    }

    /**
     * 不带参数的get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url) throws Exception {
        HttpResult httpResult = this.doGet(url, null);
        return httpResult;
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null) {
            // 声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // 遍历map，设置参数到list中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建form表单对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

//            httpPost.setHeader("Content-type", "application/json");
            // 把表单对象设置到httpPost中
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(map), "UTF-8"));
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        // 解析response封装返回对象httpResult
        HttpResult httpResult = null;
        if (response.getEntity() != null) {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
        }

        // 返回结果
        return httpResult;
    }

    /**
     * 不带参数的post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        HttpResult httpResult = this.doPost(url, null);
        return httpResult;
    }

    /**
     * 带参数的Put请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPut(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPut httpPut = new HttpPut(url);

        // 判断map不为空
        if (map != null) {
            // 声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // 遍历map，设置参数到list中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建form表单对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            // 把表单对象设置到httpPost中
            httpPut.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = this.httpClient.execute(httpPut);

        // 解析response封装返回对象httpResult
        HttpResult httpResult = null;
        if (response.getEntity() != null) {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
        }

        // 返回结果
        return httpResult;
    }

    /**
     * 带参数的Delete请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doDelete(String url, Map<String, Object> map) throws Exception {

        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 判断参数map是否为非空
        if (map != null) {
            // 遍历参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 设置参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpDelete httpDelete = new HttpDelete(uriBuilder.build());

        // 3 使用HttpClient执行httpGet，相当于按回车，发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpDelete);

        // 4 解析结果，封装返回对象httpResult，相当于显示相应的结果
        // 状态码
        // response.getStatusLine().getStatusCode();
        // 响应体，字符串，如果response.getEntity()为空，下面这个代码会报错,所以解析之前要做非空的判断
        // EntityUtils.toString(response.getEntity(), "UTF-8");
        HttpResult httpResult = null;
        // 解析数据封装HttpResult
        if (response.getEntity() != null) {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
        }

        // 返回
        return httpResult;
    }

    //////////
    // https://www.cnblogs.com/zhao1949/p/9148813.html
    public String sendHttpPost(String url, String body) throws Exception {
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

    public String sendHttpDelete(String url) throws Exception {

        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpDelete httpDelete = new HttpDelete(uriBuilder.build());

        CloseableHttpResponse response = this.httpClient.execute(httpDelete);

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
}
