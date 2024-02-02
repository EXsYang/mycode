package com.hspedu.web.homework;

import com.hspedu.web.homework.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangda
 * @description:
 * @create 2023-09-28-21:38
 */

@RequestMapping(value = "/user")
@Controller
public class LoginHandler {


    @RequestMapping(value = "/login1")
    public void doLogin1(User user){
        System.out.println("doLogin...");
        // 如果没有返回值 默认视图解析器会 默认按照 前缀 user/login1 后缀 进行拼接
        // HTTP Status 404 - /springmvc/WEB-INF/pages/user/login1.jsp

    }

    @RequestMapping(value = "/login")
    public String doLogin(User user){
        System.out.println("doLogin...");
        // 如果没有返回值 默认视图解析器会 默认按照 前缀 user/login1 后缀 进行拼接
        // HTTP Status 404 - /springmvc/WEB-INF/pages/user/login1.jsp

        //  前端什么都不提交 既不提交用户名username属性
        //  ，也不提交pwd属性 后端springmvc也会封装user对象
        //springmvc自动封装的user= User{username='', pwd=}

        System.out.println("springmvc自动封装的user= " + user);

        // 判断int 和 null 等等 ==  , 会报空指针异常
        //int t = 2;
        //Integer x = null;
        //System.out.print("t == x:");
        //System.out.println( t == x);


        /*
            当前端给了一个字符串 而 后端的Javabean 中应该是Integer时 ，
            SpringMVC在目标方法中形参位置自动的进行封装时就会失败 报错信息如下：
            28-Sep-2023 22:53:40.644 警告 [http-apr-8080-exec-87] org.springframework
            .web.servlet.mvc.support.DefaultHandlerExceptionResolver.logException Resolved
             [org.springframework.validation.BindException: org.springframework.validation
            .BeanPropertyBindingResult: 1 errors
            Field error in object 'user' on field 'pwd': rejected value [ssss];
            codes [typeMismatch.user.pwd,typeMismatch.pwd,typeMismatch.java.lang.
            Integer,typeMismatch]; arguments [org.springframework.context.support
            .DefaultMessageSourceResolvable: codes [user.pwd,pwd]; arguments [];
            default message [pwd]]; default message [Failed to convert property value
            of type 'java.lang.String' to required type 'java.lang.Integer' for
            property 'pwd'; nested exception is java.lang.NumberFormatException:
            For input string: "ssss"]]
        *
        * */

        // 判断user的密码是否为123
        //if ( 123 == user.getPwd() ){
        if ( "123".equals(user.getPwd())){
            return "login_ok";
        }

        return "login_error";
    }


    //处理登录
    @RequestMapping(value = "/login2")
    public String doLogin2(User user) {
        System.out.println("doLogin2。。。");
        System.out.println("springmvc自动封装的user= " + user);
        //判断
        //if("hsp".equals(user.getUsername())
        //        && 123 == user.getPwd()){
        if("hsp".equals(user.getUsername())
                && "123".equals(user.getPwd())){
            //验证OK
            return "forward:/WEB-INF/pages/homework/login_ok.jsp";
        } else {
            return "forward:/WEB-INF/pages/homework/login_error.jsp";
        }

    }
}
