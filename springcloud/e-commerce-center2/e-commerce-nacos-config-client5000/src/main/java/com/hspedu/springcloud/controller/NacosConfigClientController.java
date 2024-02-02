package com.hspedu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangda
 * @create 2024-01-05-15:35
 * @description:
 * 注解 @RefreshScope 是 spring cloud 原生注解，实现配置信息自动刷新, 如果在 Nacos
 * Server 修改了配置数据，Client 端就会得到最新配置
 */
@RestController
@Slf4j
@RefreshScope
public class NacosConfigClientController {


    /**
     * 解读：
     * 1.这里使用的注解@Value是 import org.springframework.beans.factory.annotation.Value;
     * 而不是 lombok 包下的
     * 2. client 会拉取nacos server 的 e-commerce-nacos-config-client-dev.yaml
     * config:
     *     ip: "122.11.11.11"
     *     name: "韩顺平教育"
     * 3. @Value("${config.ip}") 表示 会把config.ip 赋给 configIp
     * 4. 这里的${config.ip} 不能乱写,要有依据,即在配置中心必须要有配置对应的值，否则这个微服务启都启不来
     */
    @Value("${config.ip}")
    private String configIp;

    @Value("${config.name}")
    private String configName;

    @GetMapping("/nacos/config/ip")
    public String getConfigIp(){
        return configIp;
    }

    @GetMapping("/nacos/config/name")
    public String getConfigName(){
        return configName;
    }





}
