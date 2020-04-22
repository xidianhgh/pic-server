//package com.example.demo.config;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.WriteListener;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.*;
//
///**
// * Created by MI on 2019/6/8.
// */
//public class MyResponseWrapper extends HttpServletResponseWrapper {
////    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
////    private PrintWriter pwrite;
////    public MyResponseWrapper(HttpServletResponse response) {
////        super(response);
////    }
////    @Override
////    public ServletOutputStream getOutputStream() throws IOException {
////        return new MyServletOutputStream();
////    }
////    @Override
////    public PrintWriter getWriter() throws IOException {
////        try{
////            pwrite = new PrintWriter(new OutputStreamWriter(bytes, "UTF-8"));
////        } catch(UnsupportedEncodingException e) {
////            e.printStackTrace();
////        }
////        return pwrite;
////}
////
////    /**
////     *
////     * 获取响应数据
////     * @param
////     * @return byte[]
////     * @throws
////     * @author pf
////     * @datetime 2016年4月28日 下午4:23:33
////     */
////    public byte[] getBytes() {
////        if(null != pwrite) {
////            pwrite.close();
////            return bytes.toByteArray();
////        }
////        if(null != bytes) {
////            try {
////                bytes.flush();
////            } catch(IOException e) {
////                e.printStackTrace();
////            }
////        }
////        return bytes.toByteArray();
////
////}
////    private class MyServletOutputStream extends ServletOutputStream {
////        @Override
////        public void write(int b) throws IOException {
////            bytes.write(b); // 将数据写到 stream　中
////        }
////        @Override
////        public boolean isReady() {
////            return false;
////        }
////        @Override
////        public void setWriteListener(WriteListener writeListener) {
////        }
////    }
//    /////////////////////////////////////////////////////////
//    private ByteArrayOutputStream buffer = null;
//    private ServletOutputStream out = null;
//    private PrintWriter writer = null;
//
//    public MyResponseWrapper(HttpServletResponse resp) throws IOException {
//        super(resp);
//        buffer = new ByteArrayOutputStream();// 真正存储数据的流
//        out = new WapperedOutputStream(buffer);
//        writer = new PrintWriter(new OutputStreamWriter(buffer));
//    }
//
//    @Override
//    public ServletOutputStream getOutputStream() throws IOException {
//        return out;
//    }
//
//    @Override
//    public PrintWriter getWriter() throws UnsupportedEncodingException {
//        return writer;
//    }
//
//    @Override
//    public void flushBuffer() throws IOException {
//        if (out != null) {
//            out.flush();
//        }
//        if (writer != null) {
//            writer.flush();
//        }
//    }
//
//    @Override
//    public void reset() {
//        buffer.reset();
//    }
//
//    public byte[] getResponseData() throws IOException {
//        flushBuffer();
//        return buffer.toByteArray();
//    }
//
//    public String getContent() throws IOException{
//        flushBuffer();
//        return buffer.toString();
//    }
//
//    private class WapperedOutputStream extends ServletOutputStream {
//        private ByteArrayOutputStream bos = null;
//
//        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
//            bos = stream;
//        }
//
//        @Override
//        public void write(int b) throws IOException {
//            bos.write(b);
//        }
//
//        @Override
//        public void write(byte[] b) throws IOException {
//            bos.write(b, 0, b.length);
//        }
//
//        @Override
//        public void write(byte[] b, int off, int len) throws IOException {
//            bos.write(b, off, len);
//        }
//
//        @Override
//        public boolean isReady() {
//            return false;
//        }
//
//        @Override
//        public void setWriteListener(WriteListener writeListener) {
//
//        }
//    }
//
//
//}
