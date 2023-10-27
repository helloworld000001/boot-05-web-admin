package com.atguigu.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/10/27 12:55
 */
@Configuration
public class MyDataSourceConfig {
    /* 源码查看
    * 默认的自动配置时判断容器中没有才会配@ConditionalOnMissingBean(DataSource.class)
    * 这里我们已经自己配置了，相当于不会再帮我们配置数据源了
    * */
    @Bean
    /* 表示将DataSource组件的属性与配置文件中的spring:datasource:下的内容进行绑定 */
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        /* setXXX可以在配置文件直接设，因为已经和配置文件进行绑定 */
        // Druid内置一个statFilter,用于统计监控信息。加入监控功能，配置StatFilter:
        // 配置防火墙wall
        // druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    // 配置druid的监控页功能：监控/druid/**路径下的SQL，反映相关执行SQL的执行情况
    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(statViewServlet, "/druid/*");

        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "123456");

        return registrationBean;
    }

    // 采集web-jdbc关联监控的数据
    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        /* 排除对静态资源和/druid/*等的监控 */
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
