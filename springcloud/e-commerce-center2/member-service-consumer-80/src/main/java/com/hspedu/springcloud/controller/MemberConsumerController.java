package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Member;
import com.hspedu.springcloud.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangda
 * @create 2023-12-29-21:30
 * @description:
 */
@RestController
@Slf4j
public class MemberConsumerController {

    //定义 member_service_provider_url 这是一个基础url地址
    //使用shift+ctrl+u 进行大小写字母的切换
    public static final String MEMBER_SERVICE_PROVIDER_URL =
            // "http://localhost:10000"; //后面这里`localhost:10000`,会修改成提供服务模块的注册别名
            /**
             * 1. MEMBER-SERVICE-PROVIDER 就是 服务提供方[集群],注册到Eureka-Server的名称
             * 2. 也解释服务提供方[集群]对外暴露的名称为 MEMBER-SERVICE-PROVIDER
             * 3. MEMBER-SERVICE-PROVIDER 目前有两个 Availability Zones =>
             *    localhost:member-service-provider:10000 , localhost:member-service-provider:10002
             *    需要增加注解 @LoadBalanced (在配置类CustomizationBean注入Bean时增加注解 @LoadBalanced)
             *    赋予RestTemplate负载均衡的能力,也就是说会根据你的负载均衡算法
             *    来选择某个服务(10000?or10002?)去访问,默认是轮询算法,当然我们也可以自己配置负载均衡的算法
             */
            "http://MEMBER-SERVICE-PROVIDER"; //服务提供方[集群]的别名

    //装配RestTemplate bean/对象
    @Resource
    private RestTemplate restTemplate;


    //装配DiscoveryClient,注意要选择接口的DiscoveryClient,不要选错了。 注意要启用@EnableDiscoveryClient //启用服务发现
    @Resource
    private DiscoveryClient discoveryClient;

    // discovery:发现
    @GetMapping("/member/consumer/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("服务名={}",service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {

                log.info("id={},host={},port={},uri={}",instance.getServiceId(),instance.getHost(),instance.getPort(),instance.getUri());
            }
        }

        return discoveryClient;

    }


    //方法/接口 添加member对象到数据库/表
    //这里有一个坑(即底层使用的是json传输)
    @PostMapping("member/consumer/save")
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

        return restTemplate.postForObject(MEMBER_SERVICE_PROVIDER_URL
                + "member/save", member, Result.class);

    }

    //方法/接口,根据id 调用服务接口,返回member对象信息
    //http://localhost/member/consumer/get/4
    @GetMapping("/member/consumer/get/{id}")
    public Result<Member> getMemberById(@PathVariable("id") Long id){

        log.info("service-consumer id={}",id);
        return restTemplate.getForObject(MEMBER_SERVICE_PROVIDER_URL
                + "/member/get/" + id, Result.class);

    }







    /**在调用Spring的 RestTemplate 方法，如 exchange 或 getForObject 时，如果你希望返回的对象包含泛型信息，就可以使用 ParameterizedTypeReference。以下是一个示例：
     * 在这个例子中，ParameterizedTypeReference 被用来指定 List<Member> 类型，这样 RestTemplate 就能够正确地将响应体解析为一个包含 Member 实例的列表。
     * 结论
     * ParameterizedTypeReference 在处理Java的泛型擦除问题时非常有用，尤其是在使用Spring的REST客户端进行类型安全的HTTP请求和响应处理时。通过使用这个类，开发者可以确保他们的代码能够正确地处理复杂的泛型类型。
     */
    public List<Member> forExample1(Member member) {

        ParameterizedTypeReference<List<Member>> typeRef = new ParameterizedTypeReference<List<Member>>() {
        };
        ResponseEntity<List<Member>> response = restTemplate.exchange(
                "http://example.com/api/members",
                HttpMethod.GET,
                null,
                typeRef);

        List<Member> memberList = response.getBody();
        return memberList;
    }

    // 在Spring框架的 RestTemplate 类中，exchange 方法是一个非常灵活的方法，用于发送HTTP请求并接收响应。它提供了对HTTP请求和响应的完全控制，包括HTTP方法、请求头、请求体、URL参数，以及响应类型。
    // exchange 方法的作用和特点
    // 发送不同类型的HTTP请求:
    // exchange 方法可以用于发送GET、POST、PUT、DELETE等各种类型的HTTP请求。这是通过指定 HttpMethod 参数来实现的。
    // 自定义请求头和请求体:
    // 通过 HttpEntity 参数，你可以自定义请求的头信息（headers）和体信息（body）。这在需要发送包含特定头信息，如认证信息、内容类型等的请求时非常有用。
    // 灵活的URL和参数处理:
    // 方法允许你通过URL字符串直接传递参数，或者通过URI参数来构建更复杂的URL。
    // 指定响应体的类型:
    // 使用泛型参数，你可以指定期望的响应体类型。这对于接收JSON或XML等格式的响应并将其转换为Java对象特别有用。
    // 处理响应实体:
    // exchange 方法返回一个 ResponseEntity 对象，它封装了响应体和相关的元数据，如状态码和响应头。
    // 示例使用
    public List<Member> forExample2(Member member) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer yourtoken");
        org.springframework.http.HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ParameterizedTypeReference<List<Member>> typeRef = new ParameterizedTypeReference<List<Member>>() {
        };
        ResponseEntity<List<Member>> response = restTemplate.exchange(
                "http://example.com/api/members",
                HttpMethod.GET,
                entity,
                typeRef);

        List<Member> Members = response.getBody();
        // 在这个示例中，exchange 方法用于发送一个带有自定义头信息的GET请求，并接收一个类型为 List<Member> 的响应体。
        // 结论
        // exchange 方法是 RestTemplate 中最通用和灵活的方法之一，适用于需要精确控制HTTP请求细节的场景。通过这个方法，你可以自定义几乎所有的HTTP请求和响应处理细节。
        return Members;
    }
}

