package com.hspedu.springboot;

import com.hspedu.springboot.bean.Furn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangda
 * @create 2023-12-14-21:47
 * @description: 在Springboot中开发测试类
 * @SpringBootTest 注解用于创建一个 Spring 应用上下文，从而支持整合测试，模拟整个应用的运行环境。
 */
@SpringBootTest
public class ApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads(){

        //rowMapper将会对Furn对象进行封装
        BeanPropertyRowMapper<Furn> rowMapper =
                new BeanPropertyRowMapper<>(Furn.class);

        List<Furn> furns = jdbcTemplate.query("SELECT * FROM `furn`", rowMapper);

        for (Furn furn : furns) {
            System.out.println("furn-" + furn);
        }

        //输出底层使用的是什么数据源类型[HikariDataSource]
        System.out.println(jdbcTemplate.getDataSource().getClass());
        /**
         * 是因为我在pom.xml中引入了 <dependency>
         *             <groupId>org.springframework.boot</groupId>
         *             <artifactId>spring-boot-starter-data-jdbc</artifactId>
         *         </dependency> 这个依赖后，JdbcTemplate 和 HikariDataSource 就自动的关联了吗?
         * GPT
         * 是的，您的理解是正确的。在 Spring Boot 中，当您引入 spring-boot-starter-data-jdbc 依赖时，
         * 它会自动配置 JdbcTemplate 和默认的数据源，而在 Spring Boot 的默认设置中，这个数据源通常是
         * HikariDataSource。下面是这个过程的详细解释：
         *
         * 引入 spring-boot-starter-data-jdbc：
         *
         * 这个 Starter 包含了使用 JDBC 需要的所有基本依赖，包括 Spring 的 JdbcTemplate。
         * Starter 包是 Spring Boot 的一部分，它的目的是简化依赖管理和自动配置。
         * 自动配置：
         *
         * Spring Boot 使用了一种叫作“约定优于配置”的方法。它尝试根据类路径中的依赖和应用的配置自动配置您的应用程序。
         * 当 Spring Boot 检测到 JDBC 相关的依赖时，它会自动配置 JdbcTemplate 和数据源（DataSource）。
         * 选择 HikariDataSource 作为默认数据源：
         *
         * 如果没有特别指定其他数据源，Spring Boot 会选择 HikariCP 作为默认的 JDBC 连接池实现，这意味着它
         * 会创建一个 HikariDataSource 实例。
         * 这种选择是基于 HikariCP 的性能和效率，它被认为是当前最快的连接池之一。
         * 配置数据库连接：
         *
         * 要让这一切工作，您还需要在应用的配置文件（如 application.properties 或 application.yml）中提供数据库
         * 连接信息（如 URL、用户名、密码等）。
         * 这些配置信息被用来设置 HikariDataSource 实例。
         * 所以，当您在 pom.xml 中引入 spring-boot-starter-data-jdbc 后，Spring Boot 会自动配置 JdbcTemplate
         * 和 HikariDataSource（作为默认数据源），只要您提供了必要的数据库连接配置。这就是为什么在您的测试类中能
         * 够直接使用 JdbcTemplate 并通过它获取 HikariDataSource 的类类型。
         */

    }
}
