package com.hspedu.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.springboot.mybatisplus.bean.Monster;
import com.hspedu.springboot.mybatisplus.mapper.MonsterMapper;
import com.hspedu.springboot.mybatisplus.service.MonsterService;
import org.springframework.stereotype.Service;

/**
 * 1.传统方式:在实现类中直接进行 implements MonsterService
 * 2.在mybatis-plus中，我们开发Service实现类，需要继承 ServiceImpl
 * 3.我们观察看到 ServiceImpl类实现 IService接口
 * 4.MonsterService 接口它继承了IService接口
 * 5.这里MonsterServiceImpl 就可以认为是实现了MonsterService接口，这样MonsterServiceImpl
 *   就可以使用IService接口方法，也可以理解成可以使用MonsterService接口方法或父接口IService里的方法
 * 6.如果MonsterService接口中，声明了其他的方法/自定义方法，那么我们依然需要在MonsterServiceImpl类
 *   中进行实现
 *
 *
 * MonsterServiceImpl 类是一个自定义的服务实现类，用于处理与Monster实体相关的业务逻辑。
 * 它继承自 MyBatis-Plus 框架的 ServiceImpl 类，后者是IService接口的一个具体实现。
 * 在这种继承关系中，ServiceImpl 已经通过其泛型参数定义了Mapper接口和实体类的类型。
 * 对于MonsterServiceImpl，这些类型分别被具体化为 MonsterMapper（处理Monster实体的Mapper）和Monster（实体类）。
 * 这样，MonsterServiceImpl 类继承了 MyBatis-Plus 提供的各种 CRUD 操作方法，
 * 并且可以根据业务需求，在这些方法的基础上进行扩展或自定义新方法。
 *
 * MonsterServiceImpl 类通过继承 ServiceImpl<MonsterMapper, Monster>
 * 间接地实现了 IService<T> 接口。在这个继承关系中，ServiceImpl 类已经实现了 IService<T> 接口，
 * 提供了一系列通用的 CRUD 操作和其他服务方法。
 * 当您的 MonsterServiceImpl 类继承自 ServiceImpl，它继承了所有这些方法实现。
 * 这意味着 MonsterServiceImpl 不仅获得了 ServiceImpl 的功能，也通过 IService<T>
 * 接口提供的方法签名，从而实现了 IService<T> 接口。因此，MonsterServiceImpl
 * 可以被视为 IService<T> 的一个具体实现，即使它没有直接实现这个接口，
 * 而是通过继承 ServiceImpl 来实现的。
 *
 * @author yangda
 */

@Service
public class MonsterServiceImpl
        extends ServiceImpl<MonsterMapper, Monster>
        implements MonsterService {


    // @Override
    // public void t1() {
    //
    // }
}
