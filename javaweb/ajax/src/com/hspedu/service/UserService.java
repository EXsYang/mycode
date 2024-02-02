package com.hspedu.service;

import com.hspedu.dao.UserDAO;
import com.hspedu.javabean.User;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-06-30-21:44
 */
public class UserService {

    // 定义方法 到数据库获取数据 //属性userDao, 方便操作数据库
    private UserDAO userDAO = new UserDAO();

    /*
老韩解读 UserService 提供业务方法
，比如 getUserByName
 */


    public User getUserByName(String username) {
        //这里老师在讲解jdbc 和 满汉楼时，说的非常清楚.
        User user = userDAO.querySingle
                ("select * from `user` where username=?", User.class, username);
        return user;
    }


    // 查询所有数据
    public List<User> selectAll() {
        System.out.println("selectAll 被调用");


        List<User> users = userDAO.queryMulti("select * from `user`", User.class);
        return users;
    }

    // 查询数据库是否有相同名字的用户的username 是否相同
    public boolean selectUserName(String username) {
        //System.out.println("selectUserName 被调用");

        Object reUsername = userDAO.queryScalar("select username from `user` where username = ?;", username);
        // 如果名字相同，就返回false,说明已经被注册过了
        if (username.equals(reUsername)) {
            return false;
        }

        // 如果名字不相同，就返回true,说明没注册过
        return true;

    }

    // 查询数据库是否有该名字的用户 返回true 说明可以使用
    public User selectUser(String username) {
        //System.out.println("selectUserName 被调用");

        User user = userDAO.querySingle("select * from `user` where username = ?;", User.class, username);
        //if (user == null) {
        //    return user;
        //}
        //User newUser = (User) user;
        //if (!username.equals(newUser.getUsername())) {
        //    return false;
        //}

        //return false;
        return user;

    }


}
