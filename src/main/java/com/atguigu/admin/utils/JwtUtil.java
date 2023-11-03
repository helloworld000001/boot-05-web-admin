package com.atguigu.admin.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @auther 陈彤琳
 * @Description $ Jwt工具类
 * 2023/11/3 12:32
 */
public class JwtUtil {
    private static long time = 1000 * 25;
    private static String sign = "admin";

    /**
     * 创建token
     * @return
     */
    public static String createToken(){
        // 创建一个JwtBuilder对象
        JwtBuilder builder = Jwts.builder();
        String jwtToken = builder
                // Header:头部
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // Payload:载荷
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("admin-test")
                // Token过期时间: 当前时间往后存活一小时
                .setExpiration(new Date(System.currentTimeMillis()+ time))
                .setId(UUID.randomUUID().toString())
                // Signature: 签名
                // 设置加密算法和签名
                .signWith(SignatureAlgorithm.HS256, sign)
                // 使用"." 连接成一个完整的字符串
                .compact();
        System.out.println(jwtToken);
        return jwtToken;
    }

    /**
     * 封装验证token是否过期的方法
     */
    public static boolean checkToken(String token){
        if(token == null || token == ""){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false; // false表示过期
        }

        return true;
    }
}
