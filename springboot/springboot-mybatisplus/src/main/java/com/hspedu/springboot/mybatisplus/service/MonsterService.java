package com.hspedu.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hspedu.springboot.mybatisplus.bean.Monster;

/**
 * @author yangda
 * @create 2023-12-17-22:06
 * @description:
 *
 * 1. 传统方式 在接口中 定义方法/声明方法, 然后再实现类中进行实现
 * 2. 在mybatis-plus中，我们可以继承父接口 IService
 * 3. 这个IService接口声明很多方法,比如crud
 * 4. 如果默认提供方法不能满足需求,我们可以再声明需要的方法，然后在实现类中进行实现即可
 *
 */
public interface MonsterService extends IService<Monster> {

    // public void t1();

}
