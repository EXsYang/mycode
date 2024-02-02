package com.hspedu.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 * @Controller 标识该类是一个控制器Controller通常, 这个类是一个Servlet
 */
@Controller
public class UserAction {

    // xml 使用的是 ref 装配属性的

    //xml配置 ref
    //老师说明 @Autowired  autowire: 自动装配   先按类型再按名字
    //1)在IOC容器中查找待装配的组件的类型，如果有唯一的bean匹配(按照类型)，则使用该bean装配
    //  注意：容器中只有一个时 待装配的属性的属性名无所谓 会直接按类型装配
    //2)如待装配的类型对应的bean在IOC容器中有多个，则使用待装配的属性的属性名作为id值再进行查找,
    //  找到就装配，找不到就抛异常
    //@Autowired

    //老师说明 @Resource 自动装配
    //1) @Resource有两个属性是比较重要的,分是name和type,Spring将@Resource注解的name属性解析为bean的名字,
    //  而type属性则解析为bean的类型.所以如果使用name属性,则使用byName的自动注入策略,
    //  而使用type属性时则使用byType自动注入策略
    //  比如@Resource(name = "userService") 表示装配 id=userService 对象
    //  比如@Resource(type = UserService.class) 表示按照UserService.class类型进行装配, 这时要求容器中，只能有一个这样类型的对象
    //2) 如果@Resource 没有指定 name 和 type ,则先使用byName注入策略,即按照属性的属性名 查找对应的id
    //   如果匹配不上, 再使用byType策略, 如果都不成功，就会报错

    //=================================
    //老师说明： @Autowired + @Qualifier(value = "userService02") 组合也可以完成指定 name/id 来进行自动装配
    //指定id进行组装, 也可以使用@Autowired 和 @Qualifier(value = "userService02")
    // 这时，是装配的 id=userService02 , 需要两个注解都需要写上

    // 共同点：
    //       1) @Autowired 和 @Resource 相同点 按照类型进行装配时 必须保证容器中只有一个待装配类型的对象
    //       2) 如果@Resource没有指定名字 也和@Autowired一样按照 待装配的属性的属性名
    //       ，去容器中找对应的id的对象进行装配

    //@Autowired 会在容器中有多个待装配的对象时 自动检测到 待装配的属性的属性名在容器中没有对应的id名时 报错
    //@Resource(name = "userService600") name属性只要在容器中没有找到待装配类型的属性的id 就会报错
    //即使是其他类型的 name 的值 和 ioc 容器中的 id 值 匹配上了也不行 会同时检测容器中对象的name/id 和 类型

    //@Autowired //Field injection is not recommended 建议;推荐;

    //@Resource(name = "userService600")

    @Resource
    private UserService userService600;

    public void sayOk(){
        System.out.println("UserAction 的sayOk()");
        System.out.println("userAction 装配的 userService属性=" + userService600);
        userService600.hi();
    }
}
