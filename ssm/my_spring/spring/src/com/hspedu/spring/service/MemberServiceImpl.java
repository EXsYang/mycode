package com.hspedu.spring.service;

import com.hspedu.spring.dao.MemberDAOImpl;

/**
 * @author 韩顺平
 * @version 1.0
 * Service类
 */
public class MemberServiceImpl {

    private MemberDAOImpl memberDAO;

    public MemberServiceImpl() {
        // 配置好后 在spring底层被初始化了 会被执行
        //System.out.println("MemberServiceImpl() 构造器被执行");
    }

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    // 一定要提供一个 setMemberDAO()方法 否则beans.xml 配置memberDao属性的位置会直接爆红！
    public void setMemberDAO(MemberDAOImpl memberDAO) {
        //System.out.println("setMemberDAO()...");
        this.memberDAO = memberDAO;
    }

    public void add() {
        System.out.println("MemberServiceImpl add() 被调用..");
        memberDAO.add();
    }
}
