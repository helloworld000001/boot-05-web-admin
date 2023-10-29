package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/29 12:42
 */

/**有了mybatis-plus，只需要mapper继承BaseMapper，标注上泛型（操作对应的bean）
就可以用于简单crud方法。对于复杂的方法可以使用自定义配置文件
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
}
