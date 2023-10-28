package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/27 23:55
 */
@Mapper
public interface AccountMapper {
    /**
     * 根据id返回一条Account记录
     * @param id
     * @return
     */
    public Account getAccount(Long id);
}
