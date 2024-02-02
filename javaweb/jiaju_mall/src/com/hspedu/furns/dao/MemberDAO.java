package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Member;

import java.sql.SQLException;

/**
 * @author yangda
 * @description:
 * @create 2023-07-13-10:24
 */
public interface MemberDAO {


    //小伙伴要自己分析，需要哪些方法
    //提供一个通过用户名返回对应的Member
    public Member queryMemberByUsername(String username);

    /**
     * 提供一个保存Member对象到数据库/表member表
     * 返回0 就是失败，返回其它的数字就是受影响的行数
     * @param member
     * @return
     */
    public int saveMember(Member member);

    /**
     * 根据用户名和密码返回Member
     * @param username 用户名
     * @param password 密码
     * @return 返回的对象，如果不存在，返回null
     *
     */
    public Member queryMemberByUsernameAndPassword
    (String username, String password);


}
