package com.hspedu.furns.test;

import com.hspedu.furns.dao.MemberDAO;
import com.hspedu.furns.dao.daoimpl.MemberDAOImpl;
import com.hspedu.furns.entity.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * @author yangda
 * @description:
 * @create 2023-07-13-13:31
 */
public class MemberDAOImplTest {

    private MemberDAO memberDAO = new MemberDAOImpl();
    /**
     * 测试queryMemberByUsername
     */
    @Test
    public void queryMemberByUsername(){

        Member admin = memberDAO.queryMemberByUsername("admin");
        if (admin != null){
            System.out.println("该用户存在");
        }else {
            System.out.println("该用户不存在");
        }


    }

    /**
     * 测试saveMember
     */
    @Test
    public void saveMember (){

        // 构建Member对象
        Member member = new Member(null, "yangda1", "yangda", "yangda@qq.com");


        int affectedRows = memberDAO.saveMember(member);

        //int affectedRows = 0;
        //try {
        //    affectedRows = memberDAO.saveMember(member);
        //
        //} catch (Exception e) {
        //    System.out.println("出错了");
        //}

        if (affectedRows == 1){
            System.out.println("affectedRows= " + affectedRows);
            System.out.println("用户保存成功");
        }else {
            System.out.println("affectedRows= " + affectedRows);
            System.out.println("用户保存error");
        }


    }

    /**
     * 测试queryMemberByUsernameAndPassword()
     */
    @Test
    public void queryMemberByUsernameAndPassword (){

        // 构建Member对象
        Member member = new Member(null, "yangdax", "yangda", "yangda@qq.com");

        Member member1 = memberDAO.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());

        String queryResult = member1 != null ? "根据用户名和密码在数据库中查询到用户" : "根据用户名和密码在数据库中没有查询到用户";

        System.out.println(queryResult);
    }



}
