       /*
        * Decompiled with CFR.
        * 
        * Could not load the following classes:
        *  com.itheima.springbootclassfile.common.UserType
        *  com.itheima.springbootclassfile.pojo.vo.UserVO
        */
       package com.itheima.springbootclassfile.controller;
       
       import com.itheima.springbootclassfile.common.UserType;
       import com.itheima.springbootclassfile.pojo.vo.UserVO;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.PathVariable;
       import org.springframework.web.bind.annotation.RequestMapping;
       import org.springframework.web.bind.annotation.RestController;
       
       @RestController
       @RequestMapping(value={"/user"})
       public class UserController {
           @GetMapping(value={"/{type}/{id}"})
           public UserVO user(@PathVariable(value="type") Integer type, @PathVariable(value="id") Integer id) {
/*21*/         if (type == UserType.REGULAR.getType()) {
                   return new UserVO(id.intValue(), "普通用户无权限查看");
               }
               return new UserVO(id.intValue(), "这是尊贵的收费用户才能看的秘密!");
           }
       }

