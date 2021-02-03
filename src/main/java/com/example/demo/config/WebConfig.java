//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-16"));
//        stringHttpMessageConverter.setWriteAcceptCharset(true);
//        List<MediaType> mediaTypes=new ArrayList<>();
//        mediaTypes.add(MediaType.ALL);
////        MediaType mediaType = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
//
////        mediaTypes.add(mediaType);
////        mediaTypes.add(MediaType.TEXT_HTML);
//        stringHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
//        converters.add(stringHttpMessageConverter);
//    }
//}
