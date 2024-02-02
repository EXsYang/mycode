package com.hspedu.springcloud.service;

import com.hspedu.springcloud.entity.Result;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @create 2024-01-08-23:06
 * @description:
 *
 * 要使用openfeign + sentinel 对远程调用熔断降级 必须的步骤
 * 1.在本类实现openfeign的调用接口MemberOpenFeignService
 * 2.在接口MemberOpenFeignService的注解@FeignClient(value = "member-service-nacos-provider",fallback = MemberFeignFallbackService.class)增加fallback属性
 * 3.在application.yml增加以下配置
 *
 * #openfeign和sentinel整合，必须配置
 * feign:
 *   sentinel:
 *     enabled: true
 *
 *
 * 测试方法：挂掉10004和10006(就会访问超时time out报错 或者就是连接失败客户端异常 即出现了Java异常/业务异常 就会由fallback进行处理，这时不会返回默认处理页面而是走下面的代码) 再访问http://localhost/member/openfeign/consumer/get/5
 */
@Component //不能少
public class MemberFeignFallbackService implements MemberOpenFeignService {
    @Override
    public Result getMemberById(Long id) {
        return Result.error("500","被调用服务异常,熔断降级,快速返回结果,防止请求的线程堆积");
    }
}
