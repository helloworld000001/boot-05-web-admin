package com.atguigu.admin.service;

import com.atguigu.admin.bean.City;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/29 13:27
 */
public interface CityService {
    public City queryCityById(Long id);

    public void insertCity(City city);
}
