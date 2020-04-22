//package com.example.demo.config;
//
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * Created by MI on 2019/6/8.
// */
//@Component
//public class HttpServletResponseFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////        //将参数response的类型强制转换为使用Http协议的HttpServletResponse类型
////        HttpServletResponse httpResp = (HttpServletResponse) response;
////        //创建包装器对象，包装原始的response对象
////        MyResponseWrapper respWrapper = new MyResponseWrapper(httpResp);
////        //继续执行过滤器链的doFilter方法，使用上面的包装器对象做参数
////        chain.doFilter(request, respWrapper);
////        //过滤器方法执行完毕，通过包装器对象获取缓存在包装器中的响应的数据
////        byte[] bytes = respWrapper.getBytes();
////        //处理获取到的响应的数据
////        //输出处理后的数据
////
////        //写到输出流
////        OutputStream out = response.getOutputStream();
////        out.write(bytes);
////        out.flush();
////
////    }
//
//    ////////////////////////////////////////////
//@Override
//public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    MyResponseWrapper responseWrapper = new MyResponseWrapper((HttpServletResponse) response);
//    chain.doFilter(request, responseWrapper);
//    String contentType = responseWrapper.getContentType();
//    byte[] content = responseWrapper.getResponseData();
//    if (StringUtils.hasText(contentType) && (contentType.contains(MediaType.APPLICATION_JSON_VALUE) || contentType.contains(MediaType.TEXT_HTML_VALUE))) {
//        String str = new String(content);
//        str = StringUtils.replace(str, "success", "successful");
//        content = str.getBytes();
//    }
//    OutputStream out = response.getOutputStream();
//    out.write(content);
//    out.flush();
//}
//    @Override
//    public void destroy() {
//
//    }
//}
