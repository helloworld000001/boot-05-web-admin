package com.atguigu.admin;

import com.atguigu.admin.bean.User;
import com.atguigu.admin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {

    // 虽然加上@Autowired会爆红，但是不加上运行时会出现空指针异常
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    // 用于操作redis客户端
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // 配置文件配好，依赖引入，此时底层就会使用jedis
    // 这里是在配置文件中设置成 client-type: jedis
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {
        /*// 查询出一条记录
        jdbcTemplate.queryForObject("select * from system_admin");
        // 查询出多条记录
        jdbcTemplate.queryForList("select * from system_admin");*/
        // 查询出一条记录，使用Long.class类型接收
        Long aLong = jdbcTemplate.queryForObject("select count(*) from system_admin", Long.class);
        log.info("记录总数{}", aLong);

        /* 此时发现数据源就是我们自己设置的dataSource */
        log.info("数据源类型{}", dataSource.getClass());//com.alibaba.druid.pool.DruidDataSource
    }

    @Test
    void testUserMapper() {
        User user = userMapper.selectById(1);
        log.info("用户信息，{}", user);
    }

    @Test
    void testRedis(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        // 往redis数据库中存放k-v
        operations.set("hello", "world");

        // 获取v
        String hello = operations.get("hello");
        System.out.println(hello);

        System.out.println(redisConnectionFactory.getClass());
        // class org.springframework.data.redis.connection.jedis.JedisConnectionFactory
    }
}
