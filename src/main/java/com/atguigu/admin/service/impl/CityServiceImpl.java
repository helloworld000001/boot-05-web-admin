package com.atguigu.admin.service.impl;

import com.atguigu.admin.bean.City;
import com.atguigu.admin.mapper.CityMapper;
import com.atguigu.admin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/28 9:36
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;

    public City queryCityById(Long id){
        return cityMapper.queryCityById(id);
    }

    public void insertCity(City city){
        cityMapper.insert(city);
    }
}
