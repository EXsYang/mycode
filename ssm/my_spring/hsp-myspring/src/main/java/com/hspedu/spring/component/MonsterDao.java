package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.annotation.Scope;
import com.hspedu.spring.processor.InitializingBean;

/**
 * @author yangda
 * @description:
 * @create 2023-09-12-15:31
 */
//@Scope(value = "prototype")
@Component(value = "monsterDao")
public class MonsterDao implements InitializingBean {


    public void hi(){
        System.out.println("MonsterDao-hi()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MonsterDao 初始化方法被调用 程序员可以在这里加入初始化的业务..");

    }
}
