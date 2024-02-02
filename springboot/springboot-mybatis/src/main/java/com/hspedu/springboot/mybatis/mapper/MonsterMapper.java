package com.hspedu.springboot.mybatis.mapper;

import com.hspedu.springboot.mybatis.bean.Monster;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangda
 * @create 2023-12-16-22:53
 * @description:
 * 在Mapper接口类上 标注注解@Mapper 就会扫描，并将Mapper接口代理对象注入
 *
 * @Mapper接口的作用和ssm整合项目中spring配置文件中的
 *
 * ~~~xml
 *  配置扫描器，将 mybatis 在dao层 接口的实现加入到 ioc 容器中
 *  1. 我们的mapper接口放在com.hspedu.furn.dao
 *  2. mybatis就是处于DAO层，操作DB
 * <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
 *    1. 扫描所有的 dao 接口的实现，加入到 ioc 容器中
 *    2. 这里 dao 接口，就是 mapper 接口 -->
 *      <property name="basePackage" value="com.hspedu.furn.dao"/>
 * </bean>
 * ~~~
 *  是否相同？
 * 艾丽丝格雷拉特：
 *
 * `@Mapper` 注解和配置 `MapperScannerConfigurer` 两种方法都用于在 MyBatis 和 Spring 集成中扫描和注册 Mapper 接口，但它们的使用方式和上下文有所不同。
 *
 * ### @Mapper 注解
 *
 * - **用途**：`@Mapper` 注解直接标记在 Mapper 接口上。
 * - **作用**：这个注解告诉 MyBatis，该接口是一个 Mapper 接口，MyBatis 在启动时会自动扫描这些标记了 `@Mapper` 的接口，并为它们创建代理对象。
 * - **优势**：使用 `@Mapper` 注解的主要优势是简单明了，直接在接口上声明，无需其他配置。
 * - **缺点**：需要在每个 Mapper 接口上单独添加注解，如果 Mapper 接口很多，可能会稍显麻烦。
 *
 * ### MapperScannerConfigurer 配置
 *
 * - **用途**：`MapperScannerConfigurer` 是在 Spring 配置文件中配置的。
 * - **作用**：它用于指定一个包路径，Spring 启动时会扫描这个包及其子包下所有的接口，如果它们是 MyBatis 的 Mapper 接口，Spring 会为它们创建代理对象并加入到 IOC 容器中。
 * - **优势**：使用 `MapperScannerConfigurer` 的主要优势是集中管理，可以一次性扫描整个包路径下的所有 Mapper 接口，无需在每个接口上单独添加注解。
 * - **缺点**：配置较为复杂，需要在 Spring 的配置文件中进行配置。
 *
 * ### 相同点和不同点
 *
 * - **相同点**：两者的最终目的都是让 Spring 容器管理 MyBatis 的 Mapper 接口，并为这些接口创建代理对象。
 * - **不同点**：使用方式不同，`@Mapper` 是注解方式，直接标记在接口上；而 `MapperScannerConfigurer` 是通过 XML 或 Java 配置的方式，在配置文件中指定扫描的包路径。
 *
 * 在 Spring Boot 项目中，通常推荐使用 `@Mapper` 或者使用 `@MapperScan` 注解在配置类上指定扫描的包路径，这两种方式更符合 Spring Boot 的自动配置和约定优于配置的理念。而在传统的 Spring 项目中，使用 `MapperScannerConfigurer` 可能更常见。
 */
@Mapper
public interface MonsterMapper {

    //方法 根据id返回Monster对象
    public Monster getMonsterById(Integer id);
}
