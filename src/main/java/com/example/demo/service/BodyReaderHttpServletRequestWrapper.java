//package com.example.demo.service;
//
//import com.example.demo.utils.HttpHelper;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//
//public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
//
//    private final byte[] body;
//
//    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
//throws Exception {
//        super(request);
//        body =  HttpHelper.getBodyString(request).getBytes("UTF-8");
////        body = StreamUtil.readBytes(request.getReader(), JoddDefault.encoding);
//    }
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(getInputStream()));
//    }
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
//        return new ServletInputStream() {
//
//            @Override
//            public int read() throws IOException {
//                int value = 0;
//                if(bais != null){
//                    value = bais.read();
//                    bais.close();
//                }
//                return value;
//            }
//
//            @Override
//            public boolean isFinished() {
//                return false;
//            }
//
//            @Override
//            public boolean isReady() {
//                return false;
//            }
//
//            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//        };
//    }
//
//}
