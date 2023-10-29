package com.atguigu.admin.service.impl;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.mapper.AccountMapper;
import com.atguigu.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/28 0:18
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    public Account getAccountById(Long id){
        return accountMapper.getAccount(id);
    }
}
