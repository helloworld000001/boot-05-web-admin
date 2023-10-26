package com.atguigu.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*法一：使用servletAPI的方式
// 自动扫描servlet:不配置basePackages默认扫描Boot05WebAdminApplication所在目录下所有文件
@ServletComponentScan(basePackages = "com.atguigu.admin")*/
@SpringBootApplication
public class Boot05WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05WebAdminApplication.class, args);
    }

}
