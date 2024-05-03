package com.hspedu.springboot.config;

import com.hspedu.springboot.bean.Cat;
import com.hspedu.springboot.bean.Dog;
import com.hspedu.springboot.bean.Furn;
import com.hspedu.springboot.bean.Monster;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @author yangda
 * @create 2023-11-25-21:51
 * @description:
 *
 * 默认情况下，通过 `@Configuration` 和 `@Bean` 注解注入的对象是单例的。
 * 这意味着 Spring 容器在启动时会创建这些对象的唯一实例，
 * 并且在整个应用程序的生命周期内都会重用这些实例。
 *
 * 1. @Configuration 注解标识这是一个配置类，等价于配置文件，类似于spring中的beans.xml文件
 * 2. 程序员可以通过@Bean 注解注入bean对象到容器
 * 3. 当一个类被 @Configuration 标识，该类对应的bean 也会注入到ioc容器中
 * 即BeanConfig类对应的bean对象也在ioc中
 * <p>
 * 1. proxyBeanMethods：代理 bean 的方法
 * (1) Full(proxyBeanMethods = true)【保证每个@Bean 方法被调用多少次返回的组件都是单实例的, 是代理方式】
 * (2) Lite(proxyBeanMethods = false)【每个@Bean 方法被调用多少次返回的组件都是新创建的, 是非代理方式】
 * (3) 特别说明: proxyBeanMethods 是在 调用@Bean 方法 才生效，因此，需要先获取
 * BeanConfig 组件，再调用方法 而不是直接通过 SpringBoot主程序得到的容器来获取 bean,
 * 注意观察直接通过ioc.getBean() 获取 Bean, proxyBeanMethods 值并没有生效
 * (4) 如何选择: 组件依赖必须使用 Full 模式默认。如果不需要组件依赖使用 Lite 模
 * (5) Lite 模 也称为轻量级模式，因为不检测依赖关系，运行速度快
 */

/**
 * 1. proxyBeanMethods：代理 bean 的方法
 * (1) Full(proxyBeanMethods = true)【保证每个@Bean 方法被调用多少次返回的组件都是单实例的, 是代理方式】
 * (2) Lite(proxyBeanMethods = false)【每个@Bean 方法被调用多少次返回的组件都是新创建的, 是非代理方式】
 * (3) 特别说明: proxyBeanMethods 是在 调用@Bean 方法 才生效，因此，需要先获取
 * BeanConfig 组件，再调用方法 而不是直接通过 SpringBoot主程序得到的容器来获取 bean,
 * 注意观察直接通过ioc.getBean() 获取 Bean, proxyBeanMethods 值并没有生效
 * (4) 如何选择: 组件依赖必须使用 Full 模式默认。如果不需要组件依赖使用 Lite 模
 * (5) Lite 模 也称为轻量级模式，因为不检测依赖关系，运行速度快
 */

/**
 * 老师解读
 * 1. @Import 代码 可以看到，可以指定 class的数组, 可以注入指定类型的Bean (默认是单例注入的)
 * public @interface Import {
 *
 * 	 	Class<?>[] value()}
 *
 * 2. 通过@Import 方式注入了组件, 默认组件名字/id就是对应类型的全类名 com.hspedu.springboot.bean.Cat
 */
// @Configuration(proxyBeanMethods = true) //默认就是proxyBeanMethods = true 通过 beanConfig.monster01();获取返回单例对象
// @Configuration(proxyBeanMethods = false) // 通过 beanConfig.monster01();获取返回多实例对象
@Import({Dog.class, Cat.class})
@Configuration
// @ConditionalOnBean(name = "monster_nmw")
/**
 * //@EnableConfigurationProperties(Furn.class)解读
 * 1、开启Furn配置绑定功能
 * 2、把Furn组件自动注册到ioc容器中 此时不需要@Component注解了
 * 只需要 @ConfigurationProperties(prefix = "furn01") 和 @EnableConfigurationProperties({Furn.class})
 * 这两个注解就可以完成 注入bean 和 Furn配置绑定功能
 * 3.注入的furnBean 的 key/id值 为=》 furn01-com.hspedu.springboot.bean.Furn
 *  而不是按照类名首字母小写进行注入的 这里注意一下
 */
// @EnableConfigurationProperties({Furn.class})
public class BeanConfig {

    /**
     * 解读：@Bean注解作用
     * 1. @Bean ：给容器添加/注入组件, 就是Monster bean
     * 2. monster01() : 默认你的方法名monster01 作为bean的名字/id 是唯一的
     * 3. 该方法的返回类型 Monster: 表示注入ioc容器的类型 注入bean的类型是Monster
     * 4. new Monster(200,"牛魔王",500,"疯魔拳@"); 注入到容器中具体的bean信息
     * 5.  @Bean(name/value = "monster_nmw")  name/value 都一样 设置了别名
     *     @AliasFor("name")
     *     String[] value() default {};
     *
     *     @AliasFor("value")
     *     String[] name() default {};
     *
     *  6.  @Bean(name = "monster_nmw") : 在配置/注入bean指定名字/id monster_nmw
     *      ,如果不指定 默认以方法名作为bean的名字/id ,如果指定了name 则以指定的为准
     *  7. 默认是单例注入(注入到的ioc容器是 springboot的ioc容器 即 =》 ConfigurableApplicationContext ioc =
     *                 SpringApplication.run(MainApp.class, args);)
     *  8. 在Spring框架中，@Bean注解的方法可以有参数，这些参数表示该Bean的依赖。Spring容器会自动地为这些参数寻找合适的实例来注入。这是Spring的依赖注入（Dependency Injection, DI）功能的一个关键方面。具体来说：
     *
     * 寻找合适的Bean进行注入：
     *
     * 当Spring容器创建一个由@Bean注解的方法定义的Bean时，它会检查这个方法的参数。
     * 对于每个参数，Spring容器会在其当前管理的Bean中寻找与参数类型相匹配的Bean。
     * 如果找到了合适的Bean，Spring容器就会将这个Bean注入作为参数。
     * 处理多个相同类型的Bean：
     *
     * 如果有多个相同类型的Bean可用，Spring容器需要额外的信息来确定使用哪一个。这可以通过指定Bean的名称或使用其他限定符（如@Qualifier注解）来实现。
     * 无法找到合适的Bean时的行为：
     *
     * 如果Spring容器无法找到匹配的Bean来注入，它通常会抛出异常，因为这通常表示配置错误或者所需的Bean尚未定义。
     * 使用@ConfigurationProperties进行细粒度控制：
     *
     * 有时，您可能会看到与@Bean一起使用@ConfigurationProperties注解。这用于将外部配置（如属性文件中的属性）绑定到Bean的属性上，进一步控制Bean的配置。
     * 总的来说，@Bean注解的依赖注入机制使得在Spring中管理和配置Bean更加灵活和强大。
     */

    // @Bean(name = "monster_nmw")
    // @Bean(value = "monster_nmw")
    @Bean
    // @Scope("prototype") //多实例 每次返回都是新的对象
    public Monster monster01() {
        return new Monster(200, "牛魔王", 500, "疯魔拳@");
    }

    // @Bean(name = "monster_nmw")
    // public Cat cat01() {
    //     return new Cat();
    // }

    @Bean
    /**
     * 老师解读
     * 1. @ConditionalOnBean(name = "monster_nmw") 表示
     * 2. 当容器中有一个Bean , 名字是monster_nmw (类型不做约束), 就注入dog01这个Dog bean
     * 3. 如果没有 名字是monster_nmw Bean 就不注入dog01这个Dog bean
     * 4. 还有很多其它的条件约束注解，小伙伴可以自己测试
     *
     * 5. @ConditionalOnMissingBean(name = "monster_nmw") 表示在容器中,
     * 没有 名字/id 为 monster_nmw 才注入dog01这个Bean
     *
     * 6. @ConditionalOnBean(name = "monster_nmw") 也可以放在配置类
     * 表示对该配置类的所有要注入的组件，都进行条件约束.
     *
     */
    @ConditionalOnBean(name = "monster_nmw")
    // @ConditionalOnMissingBean(name = "monster_nmw") //当容器中没有名为name="monster_nmw" 的bean时才注入dog01 这个注解可以解决循环注入问题
    public Dog dog01() {
        return new Dog();
    }
}
