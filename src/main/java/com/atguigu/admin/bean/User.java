package com.atguigu.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/23 23:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
/* 当我们的bean是User,mybatisPlus默认去数据库中查找user表
* 如果数据库中的表名是user_tbl,可以在这里定义 */
@TableName("user_tbl")
public class User {
    // 用于验证登录字段
    /* 所有的属性都应该在数据库中存在，否则查询时会报错:
    * @TableField(exist = false)表示表中不存在这个字段 */
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;
    private String token;

    // 以下是数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
