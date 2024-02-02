package com.hspedu.springboot.mybatis;

import com.hspedu.springboot.mybatis.bean.Monster;
import com.hspedu.springboot.mybatis.mapper.MonsterMapper;
import com.hspedu.springboot.mybatis.service.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-12-16-15:34
 * @description:
 *
 * 注意:如果要想使用 springboot的测试程序 一定要有一个主程序 com.hspedu.springboot.mybatis.Application
 * @SpringBootApplication
 * public class Application {
 *     public static void main(String[] args) {
 *         ConfigurableApplicationContext ioc =
 *                 SpringApplication.run(Application.class, args);
 *         System.out.println("springboot starting...");
 *     }
 * }
 *
 * 否则是跑不起来的！！！
 *
 *
 *
 */
@SpringBootTest
public class ApplicationTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 这里为什么可以进行装配？是因为在接口 MonsterMapper 中配置了@Mapper注解
     * 会在ioc容器中注入MonsterMapper接口的代理对象
     * 因此ioc容器中已经有了/包含了 MonsterMapper接口的代理对象，因此可以进行自动装配
     */
    @Resource
    private MonsterMapper monsterMapper;

    //注意 这里虽然注入的是接口，实际上注入的是接口的实现类对象
    @Resource
    private MonsterService monsterService;

    //在springboot项目使用@SpringBootTest进行测试时，
    // 一定要引`import org.junit.jupiter.api.Test;` 即junit5
    // ,不可以引junit4 `import org.junit.Test;` 否则自动装配失败，装配的是null
    // 这个junit5包含在test starter中
    @Test
    public void t1() {

        // 看看输出的数据源是什么
        System.out.println("数据源是="+jdbcTemplate.getDataSource().getClass());
        //class com.zaxxer.hikari.HikariDataSource

    }

    //测试MonsterMapper接口
    //测试 MonsterService 接口
    @Test
    public void getMonsterById(){

        // Monster monster = monsterMapper.getMonsterById(1);
        // System.out.println("monster--" + monster);

        System.out.println("monsterMapper= " + monsterMapper);
        System.out.println("monsterMapper运行类型= " + monsterMapper.getClass());
        // monsterMapper运行类型= class com.sun.proxy.$Proxy63
        System.out.println("-------------------------");
        System.out.println("monsterService= " + monsterService);
        System.out.println("monsterService运行类型= " + monsterService.getClass());
        // monsterService运行类型= class com.hspedu.springboot.mybatis.service.impl.MonsterServiceImpl

        Monster monster = monsterService.getMonsterById(2);
        System.out.println("monster###" + monster);
    }
}
