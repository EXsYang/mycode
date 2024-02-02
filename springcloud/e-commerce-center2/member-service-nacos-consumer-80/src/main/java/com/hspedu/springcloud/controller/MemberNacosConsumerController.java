package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Member;
import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.service.MemberOpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-04-22:39
 * @description:
 */
@RestController
@Slf4j
public class MemberNacosConsumerController {

    //定义 member_service_provider_url 这是一个基础url地址
    //使用shift+ctrl+u 进行大小写字母的切换
    // http://member-service-nacos-provider 就是服务提供方注册到Nacos Server的服务名，这里是小写
    public static final String MEMBER_SERVICE_NACOS_PROVIDER_URL =
            "http://member-service-nacos-provider"; //服务提供方[集群]的别名[member-service-nacos-provider小写]

    //配置/自动装配RestTemplate
    @Resource
    private RestTemplate restTemplate;

    //配置/自动装配MemberOpenFeignService
    @Resource
    private MemberOpenFeignService memberOpenFeignService;


    //编写方法通过openfeign实现远程调用
    // http://localhost/member/openfeign/consumer/get/5
    @GetMapping("/member/openfeign/consumer/get/{id}")
    public Result<Member> getMemberOpenfeignById(@PathVariable("id") Long id){

        //这里我们使用openfeign接口方式调用
        log.info("调用方式是 openfeign...");
        return memberOpenFeignService.getMemberById(id);
    }


    //
    //方法/接口 添加member对象到数据库/表
    //这里有一个坑(即底层使用的是json传输)
    @PostMapping("member/nacos/consumer/save")
    public Result<Member> save(Member member) {
        log.info("service-consumer member={}",member);

        // 请求的完整的url地址: MEMBER_SERVICE_PROVIDER_URL+"member/save" => http://localhost:10000/member/save
        // member: 就是通过restTemplate 发出的post请求携带的数据(对象)
        // Result.class: 返回对象类型
        // 注意这里restTemplate是以对象的形式来向MemberConsumerController的save()方法来提交数据的,
        // 它的底层其实是一个json格式数据，而不是向url地址栏参数或者是表单参数的提交形式
        // ，所以在这个传输过程中已经变成了json格式
        // ，因此MemberConsumerController的save()在接收下面
        // 通过restTemplate.postForObject传递的member参数时，需要在save()方法的形参位置
        // 使用@RequestBody注解

        //这里通过restTemplate 调用服务提供模块的接口，就是一个远程调用RPC(Remote Procedure Call)

        return restTemplate.postForObject(MEMBER_SERVICE_NACOS_PROVIDER_URL
                + "member/save", member, Result.class);

    }

    //方法/接口,根据id 调用服务接口,返回member对象信息
    //http://localhost/member/consumer/get/4
    //http://localhost/member/nacos/consumer/get/4
    @GetMapping("/member/nacos/consumer/get/{id}")
    public Result<Member> getMemberById(@PathVariable("id") Long id){

        log.info("service-consumer id={}",id);
        return restTemplate.getForObject(MEMBER_SERVICE_NACOS_PROVIDER_URL
                + "/member/get/" + id, Result.class);

    }
}
