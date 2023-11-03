package com.atguigu.admin;

import io.jsonwebtoken.*;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/3 11:18
 */
public class JwtTest {
    // 一小时
    private long time = 1000 * 60 * 60 * 1;

    private String sign = "admin";

    /**
     * 创建JWT
     */
    @Test
    public void createJwt(){
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
        /* JWT由三部分组成：Header + Payload + Signature,三部分之间由.隔开
        Header标明使用jwt等相关信息；Payload一般存有用户相关信息，但是是明文存储，不要把敏感信息存到Payload中
        Signature是Header+Payload进行加密形成的。JWT不可破解，安全性极高
        *  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
        * .eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2OTg5ODU5MDMsImp0aSI6ImZmYTIyNmFiLTc1ZDEtNGZhZS1iNzNkLTE1OTdmOGMyMzZjNCJ9
        * .YuFCevQSQbpnPVEO01TlXBeFTKUObivVoV571qFDYU0
        *
        * */
    }

    /**
     * 验证Jwt
     */
    @Test
    public void checkJwt(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2OTg5ODU5MDMsImp0aSI6ImZmYTIyNmFiLTc1ZDEtNGZhZS1iNzNkLTE1OTdmOGMyMzZjNCJ9.YuFCevQSQbpnPVEO01TlXBeFTKUObivVoV571qFDYU0";

        // 验证token的语法是否符合Jwt的规则
        boolean result = Jwts.parser().isSigned(token);
        System.out.println(result);
    }

    /**
     * 解析Jwt
     */
    @Test
    public void parseJwt(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2OTg5ODU5MDMsImp0aSI6ImZmYTIyNmFiLTc1ZDEtNGZhZS1iNzNkLTE1OTdmOGMyMzZjNCJ9.YuFCevQSQbpnPVEO01TlXBeFTKUObivVoV571qFDYU0";

        // 获取Jwt的解析对象
        JwtParser jwtParser = Jwts.parser();

        // 必须先setSigningKey(sign)才能获取，否则认为操作是不合法的
        // 将Jwt转化成k-v结构，通过key获取对应的value
        Jws<Claims> claimsJws = jwtParser.setSigningKey(sign).parseClaimsJws(token);

        Claims payload = claimsJws.getBody();
        System.out.println(payload.get("username"));
        System.out.println(payload.get("role"));
        System.out.println(payload.getId());
        System.out.println(payload.getSubject());
        System.out.println(payload.getExpiration());

    }
}
