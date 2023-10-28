package com.atguigu.admin.bean;

import lombok.Data;

import java.util.Date;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/27 23:56
 */
@Data
public class Account {
    private Long id; //因为定义了11个长度，不要使用Integer接收
    private String userName;
    private String password;
    private String name;
    private String telephone;
    private Date time;
}
