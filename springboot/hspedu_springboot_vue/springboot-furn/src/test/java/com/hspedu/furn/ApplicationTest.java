package com.hspedu.furn;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.mapper.FurnMapper;
import com.hspedu.furn.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-12-22-20:33
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
    //自动装配Mapper接口对象-是代理对象 在讲解mybatis时讲过
    @Resource
    private FurnMapper furnMapper;

    @Resource
    private FurnService furnService;

    //在springboot项目使用@SpringBootTest进行测试时，
    // 一定要引`import org.junit.jupiter.api.Test;` 即junit5
    // ,不可以引junit4 `import org.junit.Test;` 否则自动装配失败，装配的是null
    // 这个junit5包含在test starter中
    @Test
    public void testFurnMapper() {

        System.out.println("furnMapper.getClass() = " + furnMapper.getClass());
        // furnMapper.getClass() = class com.sun.proxy.$Proxy72

        Furn furn = furnMapper.selectById(4);

        System.out.println("furn=" + furn);

    }


    @Test
    public void testFurnService() {

        System.out.println("furnService.getClass() = " + furnService.getClass());
        // furnService.getClass() = class com.hspedu.furn.service.impl.FurnServiceImpl$$EnhancerBySpringCGLIB$$6e910ccc

        Furn furn = furnService.getById(4);

        System.out.println("Service furn=" + furn);

    }
}
