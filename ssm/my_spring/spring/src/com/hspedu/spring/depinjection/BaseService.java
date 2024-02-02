package com.hspedu.spring.depinjection;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 韩顺平
 * @version 1.0
 *
 * 自定义泛型类
 */

public class BaseService<T> {
    @Autowired
    private BaseDao<T> baseDao; // 这里报错也不影响使用！可以改为语法检查就看不到报错了

    public void save() {
        baseDao.save();
    }
}
