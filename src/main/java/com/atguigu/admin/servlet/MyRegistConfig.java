package com.atguigu.admin.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
/**
 * @auther 陈彤琳
 * @Description $ 法二：创建一个config类，编写相关方法
 * 2023/10/26 16:37
 */
/* 细节
*  proxyBeanMethods = true保证依赖的组件始终时单实例的
* */
@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {
    //放到容器中
    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();
        /*当访问/my和/my02时，执行myServlet中的doGet()方法，不会被默认拦截要求登录*/
        return new ServletRegistrationBean(myServlet, "/my", "/my02");
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        MyFilter myFilter = new MyFilter();
        /* myServlet()表示过滤的url就是myServlet()中定义的urlPattern(/my和/my02)
        return new FilterRegistrationBean(myFilter, myServlet());*/
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(myFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/my", "/css/*"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean(myServletContextListener);
    }
}
