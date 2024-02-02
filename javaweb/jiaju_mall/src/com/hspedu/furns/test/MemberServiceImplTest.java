package com.hspedu.furns.test;

import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.MemberService;
import com.hspedu.furns.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author yangda
 * @description:
 * @create 2023-07-13-14:41
 */
public class MemberServiceImplTest {

    private MemberService memberService = new MemberServiceImpl();

    @Test
    public void register(){
        //构建Member对象进行测试
        Member member = new Member(null, "yangdax", "yangda", "yangda@qq.com");
        boolean register = memberService.register(member);
        if (register){
            System.out.println("注册成功~");
        }else{
            System.out.println("注册失败~");
        }


    }

    @Test
    public void userExistsByUsername(){
        boolean userExistsByUsername = memberService.isExistsByUsername("yangda");
        if (userExistsByUsername){
            System.out.println("该用户名不存在，可以注册");
        }else{
            System.out.println("该用户名存在，不可以注册");
        }

    }

    /**
     * 登录业务方法login测试
     */
    @Test
    public void login(){
        //测试自己写代码：
        /*boolean login = memberService.login("yangdax","yangda");
        if (login){
            System.out.println("登录成功~");
        }else{
            System.out.println("登录失败~");
        }*/

        Member member = new Member(null, "admin", "123456", null);
        Member loginMember = memberService.login(member);
        if (loginMember != null){
            System.out.println("登录成功~");
            System.out.println("member= " + loginMember);
        }else{
            System.out.println("登录失败~");
            System.out.println("member= " + loginMember);
        }


    }
}
