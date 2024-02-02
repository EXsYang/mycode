package com.hspedu.springboot.controller;

import com.alibaba.druid.support.http.WebStatFilter;
import com.hspedu.springboot.bean.Furn;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangda
 * @create 2023-12-15-20:23
 * @description:  测试 Druid监控功能
 */
@Controller
public class DruidSqlController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 测试sql监控功能的控制器
     * 可以监控 请求的 SQL语句
     * 执行时间分布
     * [17,0,2,0,0,0,0,0]
     *
     * 3.7区间分布
     * SQ监控项上，执行时间、读取行数、使新行数都有区同份布，将耗时分布城8个区问：
     * 0,1耗时0到1毫秒的次数
     * 1,10耗时1到10亮秒的次数
     * 10,100耗时10列100亭秒的次数
     * 100,1.000耗时100到1000亭秒的次数
     * 1.000.10,000时1到10秒的次数
     * 10.000,100.000时10到100秒的次数
     * 100.000,1.000.000时1002到1000秒的次数
     * 1.000,000-耗时1000秒以上的次数
     */
    @GetMapping("/sql")
    @ResponseBody
    public List<Furn> crudDB(){

        BeanPropertyRowMapper<Furn> rowMapper =
                new BeanPropertyRowMapper<>(Furn.class);

        List<Furn> furns = jdbcTemplate.query("select * from `furn`", rowMapper);
        for (Furn furn : furns) {
            System.out.println(furn);
        }

        return furns;
    }



    /**
     * 确实，在实际应用中，即使将 @Bean 注解用于 @Controller 标注的类，Spring 仍可能正确地注册和初始化这个 Bean，使其正常工作。在你的例子中，即使 webStatFilter 方法定义在 @Controller 标注的类中，Spring 也可能成功注册 WebStatFilter，使其正常工作。
     *
     * 尽管这样做在技术上可行，但从软件工程的最佳实践角度来看，这仍然不是推荐的做法。这些原因包括：
     *
     * 职责分离：在软件开发中，保持代码的职责清晰分离是一种重要的实践。@Controller 用于定义处理 HTTP 请求的控制器，而 @Configuration 用于定义应用程序的配置。混合使用这两者可能会让代码变得混乱，难以维护和理解。
     *
     * 可读性和维护性：当一个类同时处理 HTTP 请求和应用配置时，它承担了过多的职责，这可能降低代码的可读性和维护性。
     *
     * 一致性和预期行为：在大型项目或团队中，遵循一致的编码标准和模式是很重要的。使用 @Configuration 类来定义 Bean 是一个通行的做法，这有助于维持代码的一致性，并确保其他开发者能够正确理解代码的意图。
     *
     * 综上所述，虽然将 @Bean 定义在 @Controller 类中在技术上可能可行且能正常工作，但从代码组织和最佳实践的角度来看，仍然建议将 Bean 的定义放在专门的 @Configuration 类中。这样做有助于保持代码结构的清晰、职责的分离，并提高代码的可读性和维护性。
     */

    /**
     * 配置WebStatFilter,用于采集web-jdbc关联的监控数据
     * 主要是监控 请求的 uri
     */
    // @Bean
    // public FilterRegistrationBean webStatFilter(){
    //     // 创建 WebStatFilter
    //     WebStatFilter webStatFilter = new WebStatFilter();
    //
    //     FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
    //             new FilterRegistrationBean<>(webStatFilter);
    //
    //     //默认对所有url请求进行监控
    //     filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
    //
    //     //排除指定url
    //     filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    //
    //     return filterRegistrationBean;
    //
    // }
}
