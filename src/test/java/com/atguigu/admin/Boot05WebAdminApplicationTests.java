package com.atguigu.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {

    // 虽然加上@Autowired会爆红，但是不加上运行时会出现空指针异常
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        /*// 查询出一条记录
        jdbcTemplate.queryForObject("select * from system_admin");
        // 查询出多条记录
        jdbcTemplate.queryForList("select * from system_admin");*/
        // 查询出一条记录，使用Long.class类型接收
        Long aLong = jdbcTemplate.queryForObject("select count(*) from system_admin", Long.class);
        log.info("记录总数{}", aLong);
    }

}
