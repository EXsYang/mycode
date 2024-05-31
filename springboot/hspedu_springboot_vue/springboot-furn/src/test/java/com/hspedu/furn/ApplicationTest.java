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

/**
 * 在 Spring 框架中，`@SpringBootTest` 注解是专门为 Spring Boot 应用中的集成测试设计的。它提供了以下几个关键功能：
 *
 * 1. **启动Spring应用上下文**：`@SpringBootTest` 注解确保在测试开始前，Spring Boot 应用的上下文完整启动，就像在生产环境中运行一样。这意味着所有的配置、服务和组件都会被初始化。
 *
 * 2. **自动装配支持**：该注解通过 Spring Boot 的自动装配机制自动装配测试类中所需要的组件。这包括但不限于由 Spring 管理的 beans，如通过 `@Autowired` 或 `@Inject` 注入的组件。
 *
 * 3. **环境独立**：它允许测试在隔离的环境中执行，确保不会干扰到生产环境或开发环境的数据。同时，你可以通过配置不同的属性来模拟不同的环境设置。
 *
 * 4. **灵活的测试属性**：通过给 `@SpringBootTest` 注解添加属性，可以定制应用的启动方式。例如，可以指定运行特定的配置类或不启动web环境（通过设置 `webEnvironment = SpringBootTest.WebEnvironment.NONE`）。
 *
 * 5. **集成测试辅助**：与 `@Test` 注解结合使用，使得可以在实际的 Spring Boot 环境中执行全面的集成测试，测试数据库交互、HTTP请求处理等。
 *
 * 总之，`@SpringBootTest` 注解是进行 Spring Boot 应用集成测试时的一个重要工具，它确保测试可以在一个模拟的真实运行环境中执行，同时提供强大的自动装配和配置功能。
 */
@SpringBootTest
public class ApplicationTest {

    /**
     * `@Resource` 和 `@Autowired` 都是 Spring 框架中用于依赖注入的注解，但它们之间存在一些关键区别：
     *
     * 1. **来源**：
     *    - `@Autowired` 是 Spring 的原生注解，主要用于自动按类型进行依赖注入。
     *    - `@Resource` 是由 JSR-250 提供的，Java 的标准注解，Spring 支持这个注解用于依赖注入。它可以按名称或类型自动装配。
     *
     * 2. **注入方式**：
     *    - `@Autowired` 默认按类型进行依赖注入。如果有多个同类型的实例，可以配合 `@Qualifier` 注解来按名称进行注入。
     *    - `@Resource` 默认按名称进行依赖注入。如果未指定名称，它会回退到按类型注入。
     *
     * 3. **必要性**：
     *    - `@Autowired` 默认情况下，注入的依赖必须存在，否则会抛出异常。可以通过设置 `required=false` 来改变这种行为，使得如果没有找到相应的 bean 也不会报错。
     *    - `@Resource` 默认也是必须依赖存在，但没有类似 `required` 的属性来修改这一默认行为。
     *
     * 4. **灵活性**：
     *    - `@Autowired` 在使用 Spring 时更为灵活，支持构造器注入、字段注入和方法注入。
     *    - `@Resource` 通常用于字段注入和方法注入，不支持构造器注入。
     *
     * 在选择使用哪一个注解时，通常考虑项目中已经采用的标准和个人或团队的偏好。如果项目需要遵循 JSR-250 标准，则可能倾向于使用 `@Resource`。如果你希望更紧密地与 Spring 的功能整合，或者需要更高的配置灵活性，则可能倾向于使用 `@Autowired`。
     *
     */

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
