package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/24 9:37
 */
@Controller
public class TableController {

    @Autowired
    UserService userService;

    @GetMapping("/basic_table")
    public String basic_table() {
        /* 用于测试错误响应
        int i = 10/0; */
        return "table/basic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                             RedirectAttributes ra){
        userService.removeById(id);
        ra.addAttribute("pn", pn);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/dynamic_table")
    /* pn表示当前页码，如果有选择就传入；没有就默认是1 */
    public String dynamic_table(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                Model model) {
        //表格内容的遍历
        /* List<User> users = Arrays.asList(new User("zhangsan","12"),
                new User("lisi","123"),
                new User("wangwu","1234"),
                new User("haha","1245"),
                new User("hehe","12346"));
        model.addAttribute("users", users);*/
        /* 从数据库中查出user表中的用户
        List<User> list = userService.list();
        model.addAttribute("users", list);*/

        // 分页查询数据:pn表示当前页码，size表示每页显示几条数据
        Page<User> userPage = new Page<>(pn, 2);
        // 分页查询结果：queryWrapper表示查询条件
        Page<User> page = userService.page(userPage, null);

        /*long current = page.getCurrent();
        long pages = page.getPages();
        long total = page.getTotal();
        List<User> records = page.getRecords();*/

        model.addAttribute("page", page);

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
