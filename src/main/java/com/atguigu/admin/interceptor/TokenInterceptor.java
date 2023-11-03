package com.atguigu.admin.interceptor;

import com.atguigu.admin.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/3 15:40
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 也可以使用拦截器拦截查看token是否过期:必须把token放在Header中
        String token = request.getHeader("token");
        if(!JwtUtil.checkToken(token)){
            // 验证失败
            return false;
        }
        return true;
    }
}
