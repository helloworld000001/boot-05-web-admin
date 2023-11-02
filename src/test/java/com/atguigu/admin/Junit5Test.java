package com.atguigu.admin;

import org.junit.jupiter.api.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/2 15:25
 */
// 想要使用springboot中的容器功能，就需要加上该注解@SpringBootTest
@SpringBootTest
@DisplayName("junit5功能测试类")
public class Junit5Test {
    @DisplayName("测试DisplayName注解")
    @Test
    void testDisplayName(){
        System.out.println(1);
    }

    // 禁用方法
    @Disabled
    @DisplayName("测试2")
    @Test
    void test2(){
        System.out.println(2);
    }

    /**
     * 规定方法的超时时间，如果超出这个时间就会抛出异常
     * @throws InterruptedException
     */
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        // Thread.sleep(600);
    }

    /**
     * 该方法重复执行5次
     */
    @RepeatedTest(5)
    @Test
    void test3(){
        System.out.println(3);
    }

    /**
     * 每个测试方法运行之前都要运行这个方法
     */
    @BeforeEach
    void  testBeforeEach(){
        System.out.println("测试就要开始了");
    }

    @AfterEach
    void testAfterEach(){
        System.out.println("测试结束了");
    }

    /**
     * 所有方法调用之前都会调用这个方法，一般定义为static
     */
    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试就要开始了");
    }
}
