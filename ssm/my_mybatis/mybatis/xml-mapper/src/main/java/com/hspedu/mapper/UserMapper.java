package com.hspedu.mapper;

import com.hspedu.entity.User;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-29-22:37
 * @description:
 */
public interface UserMapper {

    //添加方法
    public void addUser(User user);
    //查询所有的 User
    public List<User> findAllUser();
}
