package com.hspedu.furns.service;

import com.hspedu.furns.entity.Member;

/**
 * @author yangda
 * @description:
 * @create 2023-07-13-14:26
 */
public interface MemberService {

    // 提供 注册用户方法
    public boolean register(Member member);

    // 根据用户名判断 该用户是否存在的方法
    public boolean isExistsByUsername(String username);
    // 老韩写的如下 命名方式参考 判断用户名是否存在
    //public boolean isExistsUsername(String username);

    //// 根据用户名判断 该用户是否存在的方法
    //public boolean login(String username,String password);


    // 根据用户名判断 该用户是否存在的方法   【自己写的方法 设计的扩展性太差，参考老师的】
    //public Member login(String username,String password);

    /**
     * 在 Web层根据用户输入的用户名和密码 构建的一个Member对象
     * 根据登录传入的member信息，返回对应的在DB中的member对象
     * @param member 是根据用户登录构建一个member
     * @return 返回的是对应的db中的member对象，如果不存在,返回null
     */
    public Member login(Member member);


    public Member queryMemberByUsername(String username);

}
