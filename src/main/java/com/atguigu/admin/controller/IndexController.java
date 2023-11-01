package com.atguigu.admin.controller;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.bean.User;
import com.atguigu.admin.service.impl.AccountServiceImpl;
import com.atguigu.admin.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/23 14:12
 */
@Controller
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    CityServiceImpl cityService;

    @Autowired
    StringRedisTemplate redisTemplate;

    /* 测试的时候使用postman提交表单，更加简单*/
    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){
        cityService.insertCity(city);
        return city;
    }

    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
        return cityService.queryCityById(id);
    }


    @ResponseBody
    @GetMapping("/account")
    public Account getAccountById(@RequestParam("id") Long id){
        return accountService.getAccountById(id);
    }

    /**
     * 一个查询语句，用于测试druid的相关功能，执行返回查询结果在页面
     * @return
     */
    @ResponseBody
    @GetMapping("/sql")
    public String queryFormDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from system_admin", Long.class);
        return aLong.toString();
    }

    /**
     * 到登录页
     * @return
     */
    /* 表示匹配url是/或/login都可以 */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        // 表示跳转到templates/login.html
        return "login";
    }

    /* 如果请求的路径是/login,就跳转到templates/index.html页面 */
    @PostMapping("/login")
    /* 使用User获取，会自动根据表单提交的参数封装到User对象中 */
    public String main(User user, HttpSession session, Model model){

        if((!StringUtils.isEmpty(user.getUserName())) && StringUtils.hasLength(user.getPassword())){
            //把登录成功的用户保存起来
            session.setAttribute("loginUser", user);
            //使用重定向防止表单重复提交redirect
            //此时不断刷新页面，只会刷新@GetMapping("/index.html")请求
            //不会刷新本请求(@PostMapping("/login")),而导致表单的重复提交
            return "redirect:/index.html";
        }else{
            //错误提示信息
            model.addAttribute("msg", "账号密码错误");
            //没有登录成功，重新回到登录页
            return "login";
        }


    }

    /* 里面跳转的url也会根据thymeleaf规则查找资源*/
    @GetMapping("/index.html")
    public String mainPage(HttpSession session, Model model){
        /* 登录检查已经单独写一个拦截器进行验证，此处就不用再去验证
        //想要跳转到该页面需要判断是否登录成功，成功了才能跳转
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){
            //真正实现thymeleaf查找index.html静态资源
            return "index";
        }else {
            //错误提示信息
            model.addAttribute("msg", "请重新登录");
            //没有登录成功，重新回到登录页
            return "login";
        }*/
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String s = opsForValue.get("/index.html");
        String s1 = opsForValue.get("/sql");

        model.addAttribute("indexCount", s);
        model.addAttribute("sqlCount", s1);
        return "index";
    }
}
