package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/28 9:32
 */
@Mapper
public interface CityMapper {
    /* 纯注解版整合mybatis:不推荐，对于复杂语句不友好 */
    @Select("select * from city where id = #{id}")
    public City queryCityById(Long id);

    /* 混合模式整合mybatis:注销掉xml中的sql语句配置，在此处使用注解，这就是混合模式 */
    @Insert("insert into city(`name`,`state`,`country`) values(#{name},#{state},#{country})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insert(City city);
}
