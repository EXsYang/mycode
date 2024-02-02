package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangda
 * @create 2024-01-05-15:33
 * @description:
 * 1. 配置文件 application.yml 和 bootstrap.yml 结合会得到配置文件/资源的地址
 * 2. 参考文档: https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html
 * 3. 注意在 Nacos Server 的配置文件的后缀是 .yaml , 而不是 .yml
 * 4. 在项目初始化时，要保证先从配置中心进行配置拉取，拉取配置之后，才能保证项目
 * 的正常启动, 也就是说如果项目不能正确的获取到 Nacos Server 的配置数据，项目是启动
 * 不了的.[演示]
 * 5. springboot 中配置文件的加载是存在优先级顺序的，bootstrap.yml 优先级高于
 * application.yml
 * 6. @RefreshScope 是 springcloud 原生注解，实现配置信息自动刷新, 如果在 Nacos
 * Server 修改了配置数据，Client 端就会得到最新配置
 * 7. 在nacos server配置文件e-commerce-nacos-config-client-dev.yaml中层级关系对应好，config:后面的空格有几个都可以
 * config:
 *     ip: "211.11.11.11[百度搜索微服务使用IP]"
 *     name: "韩顺平教育"
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClientApplication5000 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientApplication5000.class,args);
        System.out.println("NacosConfigClientApplication5000 starting...");
    }
}
