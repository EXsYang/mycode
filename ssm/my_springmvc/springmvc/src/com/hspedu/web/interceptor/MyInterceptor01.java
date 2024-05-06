package com.hspedu.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangda
 * @create 2023-10-14-16:47
 * @description: 自定义拦截器
 * 注意 想要生效 除了注入ioc容器 还需要在spring配置文件中 配置一下
 * 3、拦截器需要配置才生效，不配置是不生效的.
 */
@Component //拦截器需要作为组件注入到ioc容器
public class MyInterceptor01 implements HandlerInterceptor {

    /**
     * 1. preHandle() 在目标方法执行前被执行
     * 2. 如果preHandle() 返回false , 不再执行目标方法
     * 3. 该方法可以获取到request, response, handler
     * 4. 这里根据业务，可以进行拦截，并指定跳转到哪个页面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--MyInterceptor01--preHandle()--");

        String keyword = request.getParameter("keyword");
        if ("病毒".equals(keyword)){
            request.getRequestDispatcher("/WEB-INF/pages/warning.jsp")
                    .forward(request,response);
            return false;
        }

        System.out.println("得到的keyword= " + keyword);
        return true;
    }

    /**
     * 老师解读
     * 1. 在目标方法执行后，会执行postHandle
     * 2. 该方法可以获取到 目标方法，返回的ModelAndView对象
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("--MyInterceptor01--postHandle()--");
    }

    /**
     * 老师解读
     * 1. afterCompletion() 在视图渲染后被执行, 这里可以进行资源清理工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("--MyInterceptor01--afterCompletion()--");
    }
}
