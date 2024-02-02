package com.hspedu2.spring.component;

import com.hspedu2.spring.annotation.Component;
import com.hspedu2.spring.annotation.Scope;
import com.hspedu2.spring.processor.InitializingBean;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-16:24
 */
@Scope
@Component
public class MonsterDao implements InitializingBean{

    public void hi(){
        System.out.println("MonsterDao hi...");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MonsterDao 的初始化方法被调用...");
    }
}
