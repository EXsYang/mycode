package com.hspedu1.spring.component;

import com.hspedu1.spring.annotation.Autowired;
import com.hspedu1.spring.annotation.Component;
import com.hspedu1.spring.annotation.Scope;
import com.hspedu1.spring.processor.InitializingBean;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-16:24
 */
@Scope(value = "prototype")
@Component
public class MonsterService implements InitializingBean {

    /**
     * 只要有注解@Autowired就进行依赖注入 这里按照属性名 查找容器中的bean对象
     * 的beanName进行依赖注入
     */
    @Autowired
    private MonsterDao monsterDao;

    public void m1(){
        monsterDao.hi();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MonsterService 初始化方法被调用...");
    }
}
