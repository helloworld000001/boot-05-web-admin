package com.atguigu.admin.servlet;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/26 15:48
 */
/*
* servlet3.0后必须加上@WebServlet
* 必须在MainApplication启动类中加上@ServletComponentScan才会自动扫描servlet
* */
/*法一：使用servletAPI的方式
@WebServlet(urlPatterns = "/my")*/
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //当访问路径为/my时会在浏览器页面写入 1212
        resp.getWriter().write("1212");
    }
}
