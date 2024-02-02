package com.hspedu.springboot.interceptor;

import com.hspedu.springboot.bean.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yangda
 * @create 2023-12-08-22:14
 * @description: 自定义拦截器- 注意需要配置才能生效
 * 基本步骤:
 * √ 编写一个拦截器实现 HandlerInterceptor 接口
 * √ 拦截器注册到配置类中(实现 WebMvcConfigurer 的 addInterceptors) 注册到配置类中有两种方式
 * √ 指定拦截规则
 *
 *
 * 需求: 使用拦截器防止用户非法登录, 如图 - 使用拦截器就不需要在每个方法验证了
 * ● 浏览器输入 : http://localhost:8080/manage.html , 如果用户没有登录，则返回登录界面.
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行之前被调用
     * 测试：自己注入的servlet，如果不登录是否会访问到 /servlet01, 即不登录过滤器会拦截没有排除的url
     * 看看这里会不会被拦截到
     * 结论: 当直接请求http://localhost:8080/servlet01时，
     *      就算没有登录也不会拦截到，而是直接请求到 /servlet01对应的Servlet_
     *
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拿到请求的URI
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();
        //URI 是已经到了站点的内部，在站点的内部唯一标识
        log.info("preHandle拦截到请求的URI={}",requestURI);
        //URL 可以提供找到该资源的路径
        log.info("preHandle拦截到请求的URL={}",requestURL);

        //进行登录的校验
        HttpSession session = request.getSession();
        //注意:如果只是判断loginAdmin是否为空，不需要进行类型转换，
        //但是如果使用lombok的方法，在该方法必须显示的定义一个 Admin loginAdmin = null;
        // 否则不能使用lombok的方法loginAdmin.getName();
        // Object loginAdmin = session.getAttribute("loginAdmin");
        Admin loginAdmin = null;
        loginAdmin = (Admin)session.getAttribute("loginAdmin");
        //
        if (null != loginAdmin){//说明该用户已经成功登录
            //放行
            log.info("该用户{}已经登录过了，放行",loginAdmin.getName());
            return true;
        }

        //登录失败，进行拦截，返回到重新登录的页面
        //将错误信息放入到request域中
        request.setAttribute("msg","没有登录，请登录@@");

        //请求转发到登录页面
        request.getRequestDispatcher("/").forward(request,response);

        return false;
    }

    /**
     * 目标方法执行完成以后被调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle被执行了...");
    }

    /**
     * 页面渲染以后被调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion被执行了...");
    }
}
