package com.hspedu.mapper;

import com.hspedu.entity.User;

/**
 * @author yangda
 * @create 2023-11-03-14:32
 * @description:
 */
public interface UserMapper {

    //通过 id 获取 User 对象
    public User getUserById(Integer id);

}
