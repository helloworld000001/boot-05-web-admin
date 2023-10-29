package com.atguigu.admin.service.impl;

import com.atguigu.admin.bean.User;
import com.atguigu.admin.mapper.UserMapper;
import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/29 13:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
