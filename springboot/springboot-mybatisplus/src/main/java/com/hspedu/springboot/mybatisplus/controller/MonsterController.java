package com.hspedu.springboot.mybatisplus.controller;

import com.hspedu.springboot.mybatisplus.bean.Monster;
import com.hspedu.springboot.mybatisplus.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangda
 * @create 2023-12-17-22:57
 * @description:
 */
@Controller
public class MonsterController {

    @Resource
    private MonsterService monsterService;

    //根据id返回Monster对象
    @ResponseBody
    @GetMapping("/monster")
    public Monster getMonsterById(@RequestParam("id") Integer id){

        return monsterService.getById(id);
    }

    //返回所有的monster信息
    //后面再说分页
    /**
     * 当return的是一个实体类，对象，集合的时候，
     * 就不能普通的return，那样回报解析不了的错误，这里使用jackson来进行类型转换
     *
     * 如果不引入 jackson依赖 即使加了@ResponseBody 这里解析也会失败 HTTP Status 415
     * The server refused this request because the request entity is in a format not supported by the requested resource for the requested method.
     * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.logException Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/json;charset=UTF-8' not supported]
     * 需要引入  jackson 使springmvc支持json 格式的请求数据
     *
     * 如果没有写注解@ResponseBody 会按照下面这种 去找视图
     * HTTP Status 404 - /ssm/WEB-INF/views/save.html
     */
    // 经过测试 结果: 就算没有引入jackson,只要在形参上标注了 @RequestBody
    // 前端如果使用发送过来的是json格式的数据 就会被封装到形参furn中
    // 即@RequestBody 封装json数据到形参furn 不需要用到jackson
    // 但是 @ResponseBody 注解 需要和jackson配合使用 否则返回一个对象的形式 会报错
    // 想要返回一个对象的形式 使用 @ResponseBody 以json格式返回数据给客户端
    // 但是必须引入jackson
    @ResponseBody
    @GetMapping("/list")
    public List<Monster> monsterList(){

        return monsterService.list();
    }
}
