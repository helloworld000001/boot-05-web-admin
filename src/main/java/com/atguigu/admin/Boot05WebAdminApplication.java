package com.atguigu.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*法一：使用servletAPI的方式
// 自动扫描servlet:不配置basePackages默认扫描Boot05WebAdminApplication所在目录下所有文件
@ServletComponentScan(basePackages = "com.atguigu.admin")*/
@SpringBootApplication
/* 也可以使用这个注解定义项目中mapper位置，在mapper中就不用再每一个去添加@Mapper注解（不推荐）
@MapperScan("com.atguigu.admin.mapper")*/
public class Boot05WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05WebAdminApplication.class, args);
    }

}
