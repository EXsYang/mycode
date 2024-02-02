package com.hspedu.furns.dao.daoimpl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.MemberDAO;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yangda
 * @description:
 * @create 2023-07-13-10:55
 */
public
class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {

    /**
     * 通过用户名返回对应的Member
     *
     * @param username 用户名
     * @return 对应的Member, 如果没有该Member, 返回 null
     */
    @Override
    public Member queryMemberByUsername(String username) {

        // 构建sql语句
        //老师提示，sql 先在sqlyog 测试，然后再拿到程序中
        //这里可以提高我们的开发效率，减少不必要的bug
        //查询语句 最好不要用*
        //String sql = "SELECT * FROM member " +
        //        "WHERE `username` = ?";

        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member` " +
                " WHERE `username`=?";

        // 构建sql语句
        //String sql = "SELECT * FROM member" +   //这里WHERE前面必须有一个空格，不然会报错
        //        "WHERE `username` = ?";
        // 到数据库查询
        Member member = querySingle(sql, Member.class, username);

        // 返回数据
        return member;
    }

    /**
     * 保存一个会员
     *
     * @param member 传入Member对象
     * @return 返回0 就是失败，返回其它的数字就是受影响的行数
     */
    @Override
    public int saveMember(Member member) {

        // 构建sql语句
        // 'milan123@hanshunping.net' 单引号也拿掉，使用?替代 底层会自己加上''
        //String sql = "INSERT INTO member(`username`,`password`,`email`) " + // 这里有一个空格，一会测试 去掉这个空格好不好使
        //        "VALUES(?,MD5(?),?)";
        String sql = "INSERT INTO member(`username`,`password`,`email`)" + // VALUES这里加不加空格效果一样
                "VALUES(?,MD5(?),?)";

        int affectedRows = update(sql, member.getUsername(), member.getPassword(), member.getEmail());

        return affectedRows;

    }

    /**
     * 通过用户名和密码到数据库查询对应的Member对象
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {
        // 构建sql语句
        //String sql = "SELECT * FROM member WHERE `username` = ? AND `password` = MD5(?)";

        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member` " +
                " WHERE `username`=? and `password`=md5(?)";
        // 到数据库查询
        Member member = querySingle(sql, Member.class, username, password);

        // 返回数据
        return member;
    }


    /**
     * 通过id查询 Name
     *
     * @param id
     * @return
     */
    public String queryNameById(String id) {
        return null;
    }


}
