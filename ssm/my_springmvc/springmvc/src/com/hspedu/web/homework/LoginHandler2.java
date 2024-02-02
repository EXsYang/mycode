package com.hspedu.web.homework;

import com.hspedu.web.homework.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangda
 * @description:
 * @create 2023-09-28-22:13
 */
/*在多个类上标注'/user' 没事，不会像方法一样会报错 但是不建议   */
@RequestMapping(value = "/user")
@Controller
public class LoginHandler2 {

    //@RequestMapping(value = "/login1")
    //public void doLogin1(User user){
    //    System.out.println("doLogin...");
    //    // 如果没有返回值 默认视图解析器会 默认按照 前缀 user/login1 后缀 进行拼接
    //    // HTTP Status 404 - /springmvc/WEB-INF/pages/user/login1.jsp
    //
    //    /*
    //    但是 目标方法上的路径重复 重新发布项目会报错
    //    Invocation of init method failed; nested exception is java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'loginHandler2' method
    //    com.hspedu.web.homework.LoginHandler2#doLogin1(User)
    //    to { [/user/login1]}: There is already 'loginHandler' bean method
    //    com.hspedu.web.homework.LoginHandler#doLogin1(User) mapped.
    //    */
    //}

}
