package com.atguigu.admin;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.NestedTestConfiguration;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/2 15:25
 */
// 想要使用springboot中的容器功能，就需要加上该注解@SpringBootTest
@SpringBootTest
@DisplayName("junit5功能测试类")
public class Junit5Test {

    /**
     * 如果前面的断言失败了，后面的代码都不会执行
     */
    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertion() {
        int i = cal(2, 3);
        // 断言结果为 5，实际结果为 i, 如果不正确则会报错，报错信息为"业务逻辑计算失败"
        assertEquals(5, i, "业务逻辑计算失败");

        Object o1 = new Object();
        Object o2 = new Object();
        assertSame(o1, o2, "两个对象不一样");
    }

    int cal(int i, int j) {
        return i + j;
    }

    /**
     * 声明的所有断言执行成功才能成功
     */
    @Test
    @DisplayName("组合测试")
    void all() {
        assertAll("test",
                () -> assertTrue(true && true, "结果不为true"),
                () -> assertEquals(1, 2, "结果不是1"));
    }

    /**
     * 断言业务逻辑一定会出现异常
     */
    @DisplayName("异常断言")
    @Test
    void testException() {
        /* 断言 int i = 10 / 0;会抛异常，没有抛异常输出信息"业务逻辑居然正常运行" */
        assertThrows(ArithmeticException.class,() -> {
            int i = 10 / 2;
        }, "业务逻辑居然正常运行");
    }

    /**
     * 可以快速响应一个失败
     */
    @DisplayName("快速失败")
    @Test
    void testFail(){
        if(1 == 2){
            fail("测试失败");
        }
    }


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
