package com.atguigu.admin.config;

import com.atguigu.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @auther 陈彤琳
 * @Description $ 用于定制springmvc的一些功能
 * 2023/10/25 18:46
 */
/* 拦截器使用步骤
* 1. 编写一个拦截器实现WebMvcConfigurer接口相关方法
* 2. 将拦截器注册到容器中
* 3. 指定拦截规则： 需要放行登录页面和静态资源
*
* */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 把刚刚写好的登录拦截器添加进来 */
        /* addPathPatterns()表示要拦截哪些路径
        * addPathPatterns("/**")表示拦截所有路径
        * 实际上我们对于登录页是不能拦截的，对于/和/login路径都是不会真正访问到资源路径的
        * excludePathPatterns("/", "/login")表示放行登录页面
        * ("/css/**", "/fonts/**", "/images/**", "/js/**")这些静态资源也要放行，否则被拦截页面没有样式 */
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**");

    }
}
