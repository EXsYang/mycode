package com.hspedu.springboot.bean;

import com.sun.istack.internal.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @create 2023-11-27-14:13
 * @description: @ConfigurationProperties 注解的使用
 * 配置绑定
 * 一句话：使用 Java 读取到 SpringBoot 核心配置文件 application.properties 的内容，
 * 并且把它封装到 JavaBean 中
 * 使用 @ConfigurationProperties(prefix = "furn01") 该Furn类上面会提示如下信息, 但是不会影响使用
 * Spring Boot Configuration Annotation Processor not configured [未配置Spring Boot配置注释处理器]
 * 3. 解决 @ConfigurationProperties(prefix = "furn01") 提示信息, 在 pom.xml 增加依赖, 即可
 * 在pom.xml引入配置处理器后 该Furn类上面的提示变为
 * Re-run Spring Boot Configuration Annotation Processor to update generated metadata     Hide notification
 * [重新运行Spring Boot Configuration Annotation Processor以更新生成的元数据   隐藏通知]
 * 点击Hide notification【隐藏通知】 烦人的提示信息就没有了！
 */

@Component //注入ioc容器还是按照类名首字母小写来注入的
//@Component注解 在注入对象时会用到Furn的空参构造器 如果没有提供空参构造器就会报错
//比如只提过了@AllArgsConstructor注解时 会把默认提供的空参构造器覆盖掉 导致该类没有空参构造器
//程序就会报错

//这里只不过是用"furn01"去application.properties
// 文件中找到要 注入的furnBean 的属性赋值时所需要的 application.properties 中是哪个元素的值
@ConfigurationProperties(prefix = "furn01")
// @ToString //在编译时会生成toString()方法 默认情况下会生成无参构造器，即使没有lombok注解 也会生成一个无参构造器
/**
 * Equivalent to {@code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode}.
 * @Data 注解等价使用了 如下注解  @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
 */
// @Data
//老师说明: @NoArgsConstructor 在编译时，会生成无参构造器, 前面老师说过，默认情况下，会生成一个无参构造器
//老师说明：当我们有其它构造器生成时，如果你希望仍然有无参构造器就需要使用@NoArgsConstructor指定一下 就会显示的指定/生成一个无参构造器
//        ,否则就会覆盖无参构造器，从而代码错误
@NoArgsConstructor
//老师说明：@AllArgsConstructor 在编译时，会生成全参构造器
@AllArgsConstructor
@ToString
// 用于 在application.properties 中配置的
// , 通过@ConfigurationProperties(prefix = "furn01") 给要注入容器的furnBean设置属性
// , 底层需要用到Furn的set()方法
@Setter
// 给前端返回json数据时 用的是getter方法 如果Furn类不提供get()方法 会导致
//, @ResponseBody 处理为Json数据时返回时 为空值 具体是为=》 {}
@Getter
public class Furn {


    private Integer id;
    private String name;
    private Double price;

    // public Integer getId() {
    //     return id;
    // }
    //
    // public void setId(Integer id) {
    //     this.id = id;
    // }
    //
    // public String getName() {
    //     return name;
    // }
    //
    // public void setName(String name) {
    //     this.name = name;
    // }
    //
    // public Double getPrice() {
    //     return price;
    // }
    //
    // public void setPrice(Double price) {
    //     this.price = price;
    // }
    //
    // @Override
    // public String toString() {
    //     return "Furn{" +
    //             "id=" + id +
    //             ", name='" + name + '\'' +
    //             ", price=" + price +
    //             '}';
    // }
}
