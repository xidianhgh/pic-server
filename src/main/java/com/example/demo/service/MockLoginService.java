package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MI on 2019/3/30.
 */
@Slf4j
@Service
public class MockLoginService implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (null == request.getHeader("referer")) {
//            return true;
//        }
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.sendError(HttpServletResponse.SC_FORBIDDEN);

//        JSONObject resp = new JSONObject();
//        resp.put("msg", "尚未登录");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.write(resp.toString());
//        printWriter.flush();
//        printWriter.close();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String uri = request.getRequestURI();
        if (uri.matches("/hello")) {
            log.info("f");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uri = request.getRequestURI();

        if (uri.matches("/hello")) {
            log.info(" hello completed");
        }
    }

//    public static void main(String[] args) {
//        String str="?{abcxx}取出大括号!{io}中的内容";
//        String regex="\\{[^}]*\\}";
//       String s= str.replaceAll(regex,"");
//       System.out.println(s);
//
//        Pattern pattern=Pattern.compile(regex);
//        Matcher m= pattern.matcher(str);
//        while (m.find()){
//            System.out.println(m.group());
//        }
//    }
}
