package com.example.demo.service.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.MalformedURLException;
import java.net.URL;


@Configuration
class PicUrlConfig implements WebMvcConfigurer {
    @Value("${server.port}")
    private String port;

    @Autowired
    PicUploadProperties picUploadProperties;

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        String urlPrefixStr = "http://localhost:8089/pic/";
//        String urlPrefixStr = "http://localhost:"+port+"/pic/";
//        URL urlPrefix = null;
//        String urlPath = null;
//        String realPath = "C:/pic/yaoyunxiaoli/";
//
//        //获取图片URL前缀
//        try {
//            if (!picUploadProperties.getUrlPrefix().isEmpty()) {
//                urlPrefix = new URL(picUploadProperties.getUrlPrefix());
//            } else {
//                picUploadProperties.setUrlPrefix(urlPrefixStr);
//                urlPrefix = new URL(urlPrefixStr);
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        //获取URL.path
//        if (urlPrefix != null) {
//            urlPath = urlPrefix.getPath();
//        }
//        //获取文件存储路径
//        if (!picUploadProperties.getRealpath().isEmpty()) {
//            realPath = picUploadProperties.getRealpath();
//        }else {
//            picUploadProperties.setRealpath(realPath);
//        }
//        //获取模块名，并且遍历添加URL与存储路径的映射
//        if (!picUploadProperties.getModule().isEmpty()){
//            for (String module: picUploadProperties.getModule().intern().split(",")
//                    ) {
//                registry.addResourceHandler(urlPath+module+"/**").addResourceLocations("file:"+realPath+module+"/");
//            }
//        }
//
//        registry.addResourceHandler(urlPath+"/**").addResourceLocations("file:"+realPath);
//
//    }
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String urlPrefixStr = "http://localhost:8089/pic/";
    String urlPath= null;
    try {
        urlPath = new URL(picUploadProperties.getUrlPrefix()).getPath();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }

    String realPath=picUploadProperties.getRealpath();

    registry.addResourceHandler(urlPath+"/**").addResourceLocations("file:"+realPath);

}
}
