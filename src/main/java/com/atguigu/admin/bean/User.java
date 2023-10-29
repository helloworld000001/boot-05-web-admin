package com.atguigu.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class User {
    // 用于验证登录字段
    /* 所有的属性都应该在数据库中存在，否则查询时会报错:
    * @TableField(exist = false)表示表中不存在这个字段 */
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;

    // 以下是数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
