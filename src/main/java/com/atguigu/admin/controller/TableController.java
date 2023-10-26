package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/24 9:37
 */
@Controller
public class TableController {
    @GetMapping("/basic_table")
    public String basic_table(){
        /* 用于测试错误响应
        int i = 10/0; */
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){
        //表格内容的遍历
        List<User> users = Arrays.asList(new User("zhangsan","12"),
                new User("lisi","123"),
                new User("wangwu","1234"),
                new User("haha","1245"),
                new User("hehe","12346"));
        model.addAttribute("users", users);
        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }
}
