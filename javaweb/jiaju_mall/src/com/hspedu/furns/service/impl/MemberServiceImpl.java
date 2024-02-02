package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.MemberDAO;
import com.hspedu.furns.dao.daoimpl.MemberDAOImpl;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.MemberService;

/**
 * @author yangda
 * @description:
 * @create 2023-07-13-14:26
 */
public class MemberServiceImpl implements MemberService {
    //定义一个MemberDao属性
    private MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public boolean register(Member member) {
        // 这里老韩只写了一行代码 他将用户名是否存在的判断在Web层写的
        return memberDAO.saveMember(member) == 1 ? true : false;

      /*  // 如果该用户的用户名不存在可以进行注册
        if (userExistsByUsername(member.getUsername())){
            //System.out.println("进行注册");
            int affectedRows = memberDAO.saveMember(member);
            if (affectedRows == 1){
                //System.out.println("注册成功！");
                return true;
            }else {
                //System.out.println("注册失败！");
                return false;
            }
        }else{
            System.out.println("该用户已经存在，不能注册");
            return false;
        }
*/

    }

    /**
     *
     * @param username
     * @return 返回true 说明传入的用户名存在，不可以注册
     */
    @Override
    public boolean isExistsByUsername(String username) {
        //老韩的小技巧
        //如果看某个方法 ctrl + b  => 定位到memberDAO 遍历类型的方法
        //如果使用 ctrl+alt+ b=> 实现类的方法
        //如果有多个类，实现了该方法 会弹出选择的对话框.

        // 不为空返回true 说明存在 返回true说明该用户名存在 不可以进行注册
        return memberDAO.queryMemberByUsername(username) == null ? false : true;

       /* // 根据用户名判断 该用户是否存在
        Member member = memberDAO.queryMemberByUsername(username);
        if (member != null){
            //System.out.println("该用户存在，不能注册");
            return false;
        }else{
            //System.out.println("该用户不存在，能注册");
            return true;
        }*/

    }



    /*
      传递的过程中使用Member对象更加合理，可扩展性更强，只传递用户名和密码扩展性差
     */
    /**
     *
     * @param member 是根据用户登录构建一个member
     * @return  返回的是对应的db中的member对象，如果不存在,返回null
     */
    @Override
    public Member login(Member member) {
        //返回对象
        return  memberDAO.queryMemberByUsernameAndPassword(member.getUsername(),member.getPassword());
    }

    @Override
    public Member queryMemberByUsername(String username) {
        return memberDAO.queryMemberByUsername(username);
    }

    ///**
    // * 根据用户名和密码 进行登录操作
    // * @param username
    // * @param password
    // * @return Member对象
    // */
    //@Override
    //public Member login(String username, String password) {
    //    // 调用DAO层的方法，到数据库查询
    //    Member member = memberDAO.queryMemberByUsernameAndPassword(username, password);
    //
    //
    //    return member == null ? null : member;
    //    return member;
    //}

    /**
     * 根据用户名和密码 进行登录操作
     * @param username
     * @param password
     * @return 布尔值
     */
    //@Override
    //public boolean login(String username, String password) {
    //    // 调用DAO层的方法，到数据库查询
    //    Member member = memberDAO.queryMemberByUsernameAndPassword(username, password);
    //
    //    // 在数据库查询到结果不为空，即为登录成功
    //    return member != null;
    //}



}
