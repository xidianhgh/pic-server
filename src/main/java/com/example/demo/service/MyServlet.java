package com.example.demo.service;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by MI on 2019/6/12.
 */
public class MyServlet  extends HttpServlet{
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
//        super.service(req, res);
        resp.getWriter().write("this is servlet^_^");
    }
}
