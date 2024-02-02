package com.hspedu.springboot.mybatis.service.impl;

import com.hspedu.springboot.mybatis.bean.Monster;
import com.hspedu.springboot.mybatis.mapper.MonsterMapper;
import com.hspedu.springboot.mybatis.service.MonsterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-12-17-19:38
 * @description:
 */
//注意如果想要将MonsterService注入到ioc容器中，需要在Service实现类 MonsterServiceImpl
//上加上@Service注解，注意不要加在接口上，要加在实现类上
@Service
public class MonsterServiceImpl implements MonsterService {

    /**
     * 这里为什么可以进行装配？是因为在接口 MonsterMapper 中配置了@Mapper注解
     * 会在ioc容器中注入MonsterMapper接口的代理对象
     * 因此ioc容器中已经有了/包含了 MonsterMapper接口的代理对象，因此可以进行自动装配
     */
    //装配MonsterMapper
    @Resource
    private MonsterMapper monsterMapper;

    @Override
    public Monster getMonsterById(Integer id) {
        return monsterMapper.getMonsterById(id);
    }
}
