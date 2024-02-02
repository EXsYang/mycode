package com.hspedu1.spring.component;

import com.hspedu1.spring.annotation.Component;
import com.hspedu1.spring.annotation.Scope;
import com.hspedu1.spring.processor.InitializingBean;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-16:24
 */
@Scope
@Component
public class MonsterDao implements InitializingBean {

    public void hi(){
        System.out.println("MonsterDao hi...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MonsterDao 初始化方法...");
    }
}
