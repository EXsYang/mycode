package com.hspedu.springboot.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @create 2023-12-14-20:49
 * @description: 通过类来配置 Tomcat(说明: 配置文件可配置的更全.)
 */
@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(10000);
    }


}
