package com.hspedu.spring.ioc;

import com.hspedu.spring.annotation.ComponentScan;

/**
 * @author yangda
 * @description: 这是一个配置类, 作用类似我们原生spring的 beans.xml 容器配置文件
 * @create 2023-09-12-15:26
 */
//@ComponentScan("com.hspedu.spring.component")  // 直接写字符串也可以！！！
@ComponentScan(value = "com.hspedu.spring.component")
public class HspSpringConfig {

}
