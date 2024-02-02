package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Member;
import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author yangda
 * @create 2023-12-29-14:25
 * @description:
 */
@Slf4j
@RestController
public class MemberController {

    //装配memberService
    @Resource
    private MemberService memberService;

    /**
     * 老师解读
     *
     * @RequestBody作用
     * @RequestBody的作用 第1个功能
     * 1. @RequestBody User user 在形参指定了 @RequestBody
     * 2. springmvc就会将提交的json字符串数据填充给指定Javabean
     * @RequestBody注解,用来接收json格式数据 1.我们的前端如果是以json格式来发送添加信息furn, 那么我们后端需要使用@RequestBody注解
     * ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
     * 2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data
     * @RequestBody第2个功能 是整体取出 Post 请求内容！ 即获取post请求体
     * 注意:1.如果前端使用`Postman的Post方式提交数据，同时指定params提交`
     * ，在不使用@RequestBody的情况下，即形参位置没有使用任何注解，
     * 也可以将params正常的封装到下面方法形参的User对象中
     * --下面这种方式接收参数是接收的表单提交的数据,完成自动封装
     * 在springbootweb搜‘自定义参数封装’ 接收参数相关注解 自定义对象参数-自动封装时讲过
     * 下面这种方式接收参数 2.如果前端是在`地址栏的参数位置`提交的数据,如:Post请求 http://localhost:8080/save?userName=jack&age=22
     * 也可以完成封装--
     * 即：如果前端是以表单形式/或者是以parameters提交了,则不需要@RequestBody,才会进行对象封装，
     * 同时保证http的请求头的content-type是对应的
     * <p>
     * 如果前端是以json格式提交的数据，但是目标方法形参没有使用@RequestBody注解，在这个项目中也可以添加成功
     * ，只是表除了自增长的字段之外的各个字段的值为null [ MemberController save() 形参member=Member(id=null, name=null, pwd=null, mobile=null, email=null, gender=null) ]
     * 原因是创建数据库表`member`时，除了id字段都可以为null值，这里又指定了，id为自增长的，所以可以添加成功，
     * 即使在这里member对象的属性id=null,也可以添加成功, 因为设置了
     * {
     * "name": "老鼠精",
     * "pwd": "22",
     * "mobile": "122222222",
     * "email": "lsj@sohu.com",
     * "gender": 0
     * }
     * //@RequestBody注解,用来接收json格式数据
     * //1.我们的前端如果是以json格式来发送添加信息furn,那么我们后端需要使用@RequestBody注解
     * //  ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
     * //2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data
     */
    //添加会员方法/接口
    @PostMapping("member/save")
    public Result save(@RequestBody Member member) {
        log.info("service-provider member={}", member);
        // System.out.println("MemberController save() 形参member=" + member);
        int affected = memberService.save(member);

        if (affected > 0) {//添加会员成功
            System.out.println("member-service-nacos-provider-10006 添加会员成功后，member对象主键id的值= " + member.getId());
            // 添加会员成功后，member对象主键id的值= 13
            return Result.success("添加会员成功 member-service-nacos-provider-10006", affected);
        } else {
            return Result.error("401", "添加会员失败");
        }

    }

    //查询会员方法/接口
    //这里使用url占位符+@PathVariable
    @GetMapping("/member/get/{id}")
    // public Result getMemberById(@PathVariable("id") Long id, HttpServletRequest request) {
    public Result getMemberById(@PathVariable("id") Long id) {

        // String color = request.getParameter("color");
        // String address = request.getParameter("address");

        //模拟超时,5s
        // try {
        //     TimeUnit.MILLISECONDS.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }


        log.info("service-provider id={}", id);
        Member member = memberService.queryMemberById(id);

        //使用Result将查询的结果返回
        if (member != null) {
            // 获取member成功
            // return Result.success("通过id获取member成功 member-service-nacos-provider-10006 " + color + " " + address, member);
            return Result.success("通过id获取member成功 member-service-nacos-provider-10006", member);
        } else {
            return Result.error("402", "通过id=" + id + "获取member失败");
        }

    }
}
