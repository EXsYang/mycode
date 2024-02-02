package com.hspedu;

import com.hspedu.springboot.bean.Furn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-11-23-20:48
 * @description:
 * Slf 和单词 yourself 很像
 */
@Slf4j
@Controller
public class HiController {


    //如果需要该属性的值来自属性文件application.properties 配置的k-v 可以使用注解
    //加上@Value("${my.website}")注解 后 HiController注入ioc容器时，该属性的值
    //会按照配置文件中配置的k-v值进行注入
    //import org.springframework.beans.factory.annotation.Value;
    @Value("${my.website}")
    private String website;
// @lombok.Value //注意不是这个@Value
    //自动装配到HiController 即furn对象已经注入到了ioc容器中
    @Resource
    private Furn furn;


    //返回hi,springboot字符串
    @RequestMapping("/hi")
    @ResponseBody //加了该注解 将返回的结果按照合适的格式 直接写入响应体中
    public String hi() {
        System.out.println("hi");
        System.out.println("website= " + website);
        return "hi!!,springboot!!";
    }


    @RequestMapping("/furn")
    @ResponseBody
    public Furn furn() {
        //普通方式输出
        System.out.println("furn--" + furn);

        //使用slf4j日志输出
        //普通方式
        log.info("furn--" + furn);

        //占位符方式输出 第二个参数位置的值会替代 占位符{}
        log.info("furn={} myfurn={}", furn, furn);


        return furn;
    }
}
