package com.hspedu.springboot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yangda
 * @create 2023-10-14-16:47
 * @description:
 *
 * 自定义拦截器知识点补充
 *
 * 在Spring框架中使用HandlerInterceptor接口实现的拦截器，
 * 可以在某种程度上获取到要访问的目标方法的信息，但这取决于handler对象的具体类型。
 * 通常，handler参数是一个处理器（Controller中的方法）的表示，
 * 可以是HandlerMethod对象的实例，如果请求对应的是一个由Spring MVC处理的方法。
 *
 *
 */
@Component //拦截器需要作为组件注入到ioc容器
public class MyInterceptor03 implements HandlerInterceptor {

    /**
     * 解析HandlerMethod
     * 当handler是HandlerMethod的实例时，你可以通过它获取到很多关于目标方法的详细信息，
     * 包括方法的名称、所属类、参数类型等。以下是如何在拦截器中获取这些信息的示例代码：
     *
     * 限制和注意事项
     * 访问权限：虽然你可以获取方法的名称、参数类型等信息，但你不能访问方法参数的实际运行时值，
     * 除非这些值被作为请求参数传递或以某种方式存储（例如，在请求属性或会话中）。
     * 静态资源和直接的Servlet调用：如果请求是针对静态资源或不经过Spring的直接Servlet调用，
     * handler可能不是HandlerMethod的实例，而是其他类型，如ResourceHttpRequestHandler等，
     * 在这种情况下你将无法获取到方法级的信息。
     * 实际应用
     * 在实际应用中，使用拦截器获取目标方法的信息非常有用，尤其是在执行权限检查、日志记录、
     * 或者进行特定的业务逻辑处理前验证方法的特定属性时。这使得拦截器成为Spring MVC中
     * 功能强大且灵活的工具，可以用来增强应用的控制流和业务逻辑处理能力。
     */
    /**
     * 在使用Spring框架的`HandlerInterceptor`接口实现的拦截器中，确实有限制访问控制器方法参数的实际运行时值。这是因为`HandlerInterceptor`接口的设计主要是用来拦截请求的处理过程，在处理请求之前(`preHandle`)、之后(`postHandle`)以及请求完成后(`afterCompletion`)进行操作，但它不提供直接访问方法参数实际值的功能。
     *
     * ### 分析拦截器的三个方法：
     *
     * 1. **preHandle**：
     *    - 执行于控制器方法之前。
     *    - 此时还没有调用目标方法，因此无法访问到方法的参数值。
     *    - 最常用于权限检查、日志记录或请求预处理。
     *
     * 2. **postHandle**：
     *    - 执行于控制器方法之后，视图渲染之前。
     *    - 此时虽然目标方法已执行，但`HandlerInterceptor`接口并不提供直接访问控制器方法参数的机制。
     *    - 可以访问并修改`ModelAndView`对象，但这不包括方法参数的值。适用于修改视图层数据或进一步处理请求（如添加额外模型数据）。
     *
     * 3. **afterCompletion**：
     *    - 请求完成后执行，即视图渲染之后。
     *    - 用于资源清理或记录整体请求处理的日志。
     *    - 此时无法访问控制器方法的参数值，因为请求已经处理完毕。
     *
     * ### 如何访问方法参数的值？
     *
     * 要在拦截器中访问方法参数的实际值，可以考虑以下替代方案：
     *
     * - **使用AOP（Aspect-Oriented Programming）**：Spring AOP可以在方法执行前后织入逻辑，允许访问方法的参数值。通过定义切面（Aspect），可以在方法调用前后获取参数值进行操作。
     * - **通过Request对象**：如果方法参数是作为请求参数传递的，可以通过`HttpServletRequest`对象从请求中直解析这些参数。例如，使用`request.getParameter("paramName")`获取。
     *
     * 总结来说，`HandlerInterceptor`设计的重点在于提供在请求处理过程中的拦截点，并不直接支持访问控制器方法参数的实际值。如果需要这种功能，可能需要考虑使用Spring AOP或其他机制来达成。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查handler的类型
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 获取方法名
            String methodName = handlerMethod.getMethod().getName();

            // 获取方法所在的类
            Class<?> controllerClass = handlerMethod.getBeanType();

            // 获取方法的参数类型
            Method method = handlerMethod.getMethod();
            Class<?>[] paramTypes = method.getParameterTypes();

            System.out.println("即将执行的方法名: " + methodName);
            System.out.println("方法所在的类: " + controllerClass.getSimpleName());
            System.out.println("方法的参数类型: " + Arrays.toString(paramTypes));

            // 更多的详细操作...
            // 比如获取目标方法上的自定义注解的相关信息


        }

        //在自定义拦截器中，你可以获取到目标方法的自定义注解信息。
        // 当你在拦截器的preHandle方法中访问handler对象，
        // 并且确认它是一个HandlerMethod实例时，
        // 可以利用这个HandlerMethod对象来访问与之关联的方法的注解。
        // 这允许你根据方法上的注解来实现特定的逻辑，例如权限检查、日志记录等。

        /**
         * 步骤说明
         * 确认handler是HandlerMethod实例：这保证了你能够访问由Spring MVC控制的方法的相关信息。
         * 获取目标方法的引用：通过HandlerMethod的getMethod()方法获得。
         * 访问方法的注解：使用Java反射的功能，如getAnnotation()方法，来检索方法上的特定注解。
         * 示例代码
         * 假设你有一个自定义注解@MyAnnotation，你希望根据这个注解的存在与否或其属性来调整拦截器的行为：
         */
        // if (handler instanceof HandlerMethod) {
        //     HandlerMethod handlerMethod = (HandlerMethod) handler;
        //     Method method = handlerMethod.getMethod();
        //
        //     // 检查方法是否有特定注解
        //     MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
        //     if (myAnnotation != null) {
        //         System.out.println("找到注解MyAnnotation，注解值: " + myAnnotation.value());
        //
        //         // 根据注解内容执行特定操作
        //         // 例如，根据注解值决定是否继续处理请求
        //     } else {
        //         System.out.println("方法 " + method.getName() + " 没有MyAnnotation注解");
        //     }
        // }


        return true;
    }


    // 自定义注解
// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;
//
//     @Target(ElementType.METHOD)
//     @Retention(RetentionPolicy.RUNTIME)
//     public @interface MyAnnotation {
//         String value() default "";
//     }

    // 拦截器中检查注解
    // @Override
    // public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //     if (handler instanceof HandlerMethod) {
    //         HandlerMethod handlerMethod = (HandlerMethod) handler;
    //         Method method = handlerMethod.getMethod();
    //
    //         // 检查方法是否有特定注解
    //         MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
    //         if (myAnnotation != null) {
    //             System.out.println("找到注解MyAnnotation，注解值: " + myAnnotation.value());
    //
    //             // 根据注解内容执行特定操作
    //             // 例如，根据注解值决定是否继续处理请求
    //         } else {
    //             System.out.println("方法 " + method.getName() + " 没有MyAnnotation注解");
    //         }
    //     }
    //
    //     return true;
    // }






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
        System.out.println("--MyInterceptor02--postHandle()--");
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
        System.out.println("--MyInterceptor02--afterCompletion()--");
    }
}
