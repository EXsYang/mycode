package com.hspedu.springcloud;

import com.hspedu.springcloud.dao.MemberDao;
import com.hspedu.springcloud.entity.Member;
import com.hspedu.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
// import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-12-28-23:10
 * @description:
 */
@SpringBootTest
@Slf4j
public class MemberApplication10000Test {

    //自动装配memberDao
    @Resource
    private MemberDao memberDao;

    @Resource
    private MemberService memberService;


    // 老师强调：这里的@Test  org.junit.jupiter.api.Test;
    // 在springboot项目使用@SpringBootTest进行测试时，
    // 一定要引`import org.junit.jupiter.api.Test;` 即junit5
    // ,不可以引junit4 `import org.junit.Test;` 否则memberDao自动装配失败，装配的是null
    // 这个junit5包含在test starter中
    @Test
    public void queryMemberById(){

        // Member member = memberDao.queryMemberById(1L);

        Member member = memberService.queryMemberById(2L);

        log.info("member@@{}" , member);
    }
    @Test
    public void save(){

        // Member member = memberDao.queryMemberById(1L);
        // int affected = memberDao.save(new Member(null, "狐狸精2", "2344", "12333333333", "hlj@sohu.com", 0));

        int affected = memberService.save(new Member(null, "小猫精", "2344", "12333333333", "hlj@sohu.com", 0));

        if (affected > 0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        // log.info("member={}" , member);
    }

}
