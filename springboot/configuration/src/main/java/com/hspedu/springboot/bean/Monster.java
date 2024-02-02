package com.hspedu.springboot.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yangda
 * @create 2023-12-02-20:14
 * @description: 注意该类要放在 springboot主程序所在的包/子包 否则扫描不到
 */
@Component
@Data
@ConfigurationProperties(prefix = "monster")
public class Monster {

    // application.yml 文件中配置的值 也可以像application.properties文件一样
    // 对Bean中的某一个属性进行绑定
    // 也可以通过注解绑定 形式如@Value("${monster.id}")
    // @lombok.Value //注意不是这个@Value
    //import org.springframework.beans.factory.annotation.Value;
    // @Value("${monster.id}")
    private Integer id;

    private String name;
    private Integer age;
    private Boolean isMarried;
    private Date birth;
    private Car car;
    private String[] skill;
    private List<String> hobby;
    private Map<String, Object> wife;
    private Set<Double> salaries;
    private Map<String, List<Car>> cars;



}
