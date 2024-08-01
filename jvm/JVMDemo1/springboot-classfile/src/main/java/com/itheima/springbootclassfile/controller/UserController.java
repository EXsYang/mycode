package com.itheima.springbootclassfile.controller;

import com.itheima.springbootclassfile.common.UserType;
import com.itheima.springbootclassfile.pojo.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    //企业中代码一般通过调用service从数据库查,本案例简化代码
    // http://localhost:8080/user/1001/1
    // D:\Java_developer_tools\mycode\jvm\JVMDemo1\springboot-classfile\target\classes\com\itheima\springbootclassfile\controller\UserController.class
    @GetMapping("/{type}/{id}")
    public UserVO user(@PathVariable("type") Integer type, @PathVariable("id") Integer id) {
        //前边有一大堆逻辑，巴拉巴拉
        // 如果用户的type是 1001 则会进入到下面这行代码
        // 但是这里使用的是 == 引用数据类型做等等比较为false!
        // 即如果传入的type是非1002(普通用户),也会看到VIP用户的内容！
        if (type == UserType.REGULAR.getType()) {
            // if(type.equals(UserType.REGULAR.getType())){
            return new UserVO(id, "普通用户无权限查看");
        }

        return new UserVO(id, "这是尊贵的收费用户才能看的秘密!");
    }
}
