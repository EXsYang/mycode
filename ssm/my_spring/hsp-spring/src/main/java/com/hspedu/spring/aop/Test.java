package com.hspedu.spring.aop;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 韩顺平
 * @version 1.0
 * 简单分析 AOP 和 BeanPostProcessor 关系
 * 实现了接口@EnableAspectJAutoProxy
 * 就是启用切面自动代理
 * 4. 老韩解读
 * 1) AOP 底层是基于 BeanPostProcessor 机制的. 2) 即在 Bean 创建好后，根据是否需要 AOP 处理，决定返回代理对象，还是原生 Bean
 * 3) 在返回代理对象时，就可以根据要代理的类和方法来返回
 * 4) 其实这个机制并不难，本质就是在 BeanPostProcessor 机制 + 动态代理技术
 * 5) 下面我们就准备自己来实现 AOP 机制, 这样小伙伴们就不在觉得 AOP 神秘，通透很多
 * 了.
 */
@EnableAspectJAutoProxy
public class Test {
}
