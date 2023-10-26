package com.atguigu.admin.servlet;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/26 15:59
 */
@Slf4j
/* 这里表示的是对{"/css/*", "/images/*"}进行过滤，如果在地址栏输入/css/xxx(文件名)
* 等就可以获取项目的该资源.如果不配置访问，会回到登录页要求登录 */
/*法一：使用servletAPI的方式
@WebFilter(urlPatterns = {"/css/*", "/images/*"})*/
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter初始化完成");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter工作");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyFilter销毁");
    }
}
