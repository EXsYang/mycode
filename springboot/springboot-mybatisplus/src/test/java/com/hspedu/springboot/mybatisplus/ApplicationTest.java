package com.hspedu.springboot.mybatisplus;

import com.hspedu.springboot.mybatisplus.bean.Monster;
import com.hspedu.springboot.mybatisplus.mapper.MonsterMapper;
import com.hspedu.springboot.mybatisplus.service.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangda
 * @create 2023-12-17-21:31
 * @description:
 */
@SpringBootTest
public class ApplicationTest {

    /**
     * 这里的furnMapper通过@Resource注解自动装配的是一个代理对象，这里的furnService自动装配的是代理对象吗？
     * GPT
     * 在您提供的Java Spring Boot测试代码中，furnMapper 和 furnService 都是通过 @Resource 注解自动装配的。根据输出结果，可以看出 furnMapper 是一个代理对象，这是因为在 Spring 中使用 MyBatis 时，Mapper 接口通常是通过 JDK 动态代理或者 CGLIB 来实现的，从而不需要编写实现类。
     *
     * 对于 furnService，它的输出结果显示它是 FurnServiceImpl$$EnhancerBySpringCGLIB$$... 的一个实例。这意味着 furnService 也是一个代理对象，使用的是 Spring 的 CGLIB 动态代理技术。在 Spring 中，当你的服务类 (Service 类) 上使用了像 @Transactional 这样的注解时，Spring 会创建一个代理来包装这个服务类，以便能够在运行时处理这些注解所定义的行为（比如事务管理）。
     *
     * 总结来说，furnMapper 是通过 JDK 动态代理或 CGLIB 实现的 MyBatis Mapper 代理，而 furnService 是 Spring 通过 CGLIB 创建的服务类代理，用于处理像事务这样的跨切面关注点。
     */
    @Resource
    private MonsterMapper monsterMapper;

    @Resource
    private MonsterService monsterService;

    @Test
    public void testMonsterMapper(){
        System.out.println("monsterMapper.getClass() = " + monsterMapper.getClass());
        // monsterMapper.getClass() = class com.sun.proxy.$Proxy69
        Monster monster = monsterMapper.selectById(2);
        System.out.println("monster--" + monster);

    }


    @Test
    public void testMonsterService(){
        System.out.println("monsterService.getClass() = " + monsterService.getClass());
        // monsterService.getClass() = class com.hspedu.springboot.mybatisplus.service.impl.MonsterServiceImpl$$EnhancerBySpringCGLIB$$a34cb5be

        Monster monster = monsterService.getById(1);
        System.out.println("monster==" + monster);

        System.out.println("----------------");
        List<Monster> list = monsterService.list();
        for (Monster monster1 : list) {
            System.out.println("monster=" + monster1);
        }
    }
}
