package com.hspedu.hspspringmvc.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hspedu.hspspringmvc.annotation.Controller;
import com.hspedu.hspspringmvc.annotation.RequestMapping;
import com.hspedu.hspspringmvc.annotation.RequestParam;
import com.hspedu.hspspringmvc.annotation.ResponseBody;
import com.hspedu.hspspringmvc.context.HspWebApplicationContext;
import com.hspedu.hspspringmvc.handler.HspHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @create 2023-09-30-18:06
 * @description: 1. HspDispatcherServlet充当原生DispatcherServlet
 * 2. 本质是一个Servlet,继承HttpServlet
 * 3. 提示：这里我们需要使用到javaweb 讲解的servlet
 * <p>
 * 注意: 对Maven项目而言 src/main/resource/  目录就是类的加载路径 classpath
 */
public class HspDispatcherServlet extends HttpServlet {

    //@Override
    //public void init(ServletConfig config) throws ServletException {
    //    super.init(config);
    //}

    // 定义属性handlerList 用于保存HspHandler[url和控制器方法的映射]
    private List<HspHandler> handlerList =
            new ArrayList<>();

    private HspWebApplicationContext hspWebApplicationContext = null;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 走一圈代码 对应老韩的代码 看哪里不同

        // 获取到web.xml文件中的 contextConfigLocation
        /* <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:hspspringmvc.xml</param-value>
           </init-param>
        */

        // getInitParameter() 方法拿到web.xml 文件中的 该Servlet配置的初始化参数
        String configLocation =
                servletConfig.getInitParameter("contextConfigLocation");

        //将从web.xml 得到的configLocation 传入到HspWebApplicationContext对象中
        //

        // 在这里完成对spring容器的初始化
        //hspWebApplicationContext = new HspWebApplicationContext();

        hspWebApplicationContext = new HspWebApplicationContext(configLocation);


        hspWebApplicationContext.init();
        //在这里调用initHandlerMapping 完成url和控制器方法的映射
        initHandlerMapping();
        //输出handlerList
        System.out.println("初始化后的handlerList= " + handlerList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HspDispatcherServlet doGet() 被调用...");
        //在这里进行请求的分发处理
        String requestURI = req.getRequestURI();
        System.out.println("requestURI= " + requestURI);

        String contextPath = req.getContextPath();
        System.out.println("contextPath= " + contextPath);
        //  requestURI= /hsp_springmvc/monster/list
        executeDispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //编写方法 完成url和控制器方法的映射
    private void initHandlerMapping() {
        //前端http请求发送来的url 映射到处理器Handler/Controller 的某一个方法
        //将服务器端的所有有映射的方法封装成HspHandler放入handlerList集合中

        //首先判断有没有扫描到包 bean集合是否为空 如果为空就说明服务器端没有Controller
        //不用进行映射
        //通过容器对象拿到ioc集合
        //if (hspWebApplicationContext.ioc.size() == 0){
        if (hspWebApplicationContext.ioc.isEmpty()) {
            //判断当前的ioc容器是否为空
            return;
        }

        //取出ioc容器中的所有的bean
        ConcurrentHashMap<String, Object> ioc = hspWebApplicationContext.ioc;
        //遍历ioc容器的bean对象，然后进行url映射处理 Java基础 Map的遍历
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //entry 就是保存的Controller对象
            //得到注入的bean/Object 的 clazz对象
            Class<?> clazz = entry.getValue().getClass();
            //只要通过clazz对象才能拿到类的结构 Object对象拿不到
            //判断该clazz对象是否有@Controller注解 因为该bean也有可能是Service
            if (clazz.isAnnotationPresent(Controller.class)) {
                // 是一个有Controller注解的clazz对象 是一个Controller
                // 拿到类中所有的方法 因为可能有多个方法需要进行映射
                Method[] declaredMethods = clazz.getDeclaredMethods();
                // 遍历方法
                for (Method declaredMethod : declaredMethods) {
                    //判断这个方法是否有@RequestMapping注解
                    if (declaredMethod.isAnnotationPresent(RequestMapping.class)) {
                        //该方法有@RequestMapping注解
                        //获取方法上的value 即需要映射的url 映射路径
                        RequestMapping requestMappingAnnotation =
                                declaredMethod.getAnnotation(RequestMapping.class);

                        // 注意这里保存的方法上的url没有带上项目名称
                        // 单单只是方法上的value值 "/monster/list"
                        // 可以在这里拼接上项目路径
                        //String contextPath = getServletContext().getContextPath();

                        String url = requestMappingAnnotation.value();

                        //String fullURI = contextPath + url;
                        //构建HspHandler对象 保存映射关系
                        //entry.getValue() 是注入到ioc容器中的bean对象 即Controller对象
                        HspHandler hspHandler = new HspHandler(url, entry.getValue(), declaredMethod);
                        //将这个hspHandler放入到handlerList集合
                        handlerList.add(hspHandler);
                    }

                }


            }

        }
    }

    //编写方法，通过request对象，返回HspHandler对象
    //如果没有就返回null
    private HspHandler getHspHandler(HttpServletRequest request) {
        // 如果保存url和控制器方法映射关系的集合为空就直接返回一个null
        if (handlerList.isEmpty()) {
            return null;
        }
        // 1 获取用户请求的URI 比如 http://localhost:8080/hsp_springmvc/monster/list
        //  uri= /hsp_springmvc/monster/list
        // 2 注意这里得到的uri 和保存的uri 是有一个工程路径的问题
        //两个解决方案：第一个：简单 tomcat 直接配置 application context => /
        //第二个方案：保存 hspHandler对象 url 拼接上项目路径 getServletContext().getContextPath();
        String requestURI = request.getRequestURI();

        //遍历handlerList
        for (HspHandler hspHandler : handlerList) {
            if (requestURI.equals(hspHandler.getUrl())) {
                //说明该http请求发过来的url 和服务器端保存的url映射匹配上了
                //返回该hspHandler对象
                return hspHandler;
            }
        }
        // 遍历完handlerList都没有返回 说明没有匹配的url 返回一个null
        return null;
    }


    //编写方法完成分发请求任务
    private void executeDispatch(HttpServletRequest request,
                                 HttpServletResponse response) {
        // 通过请求对象request 去拿对应的url映射关系的hspHandler对象
        HspHandler hspHandler = getHspHandler(request);
        //如果拿回来的是null 说明没有对应的映射关系 不往下走了 给前端返回404
        try {
            if (null == hspHandler) { //说明用户请求的路径/资源不存在
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write("<h1>404 NOT FOUND</h1>");
                writer.close();
                return;
            } else {
                // 有映射关系 进行反射调用 分发请求

                //目标: 将HttpServletRequest HttpServletResponse 封装到参数数组
                //1. 得到目标方法的所有形参参数信息[对应的数组]
                Class<?>[] parameterTypes = hspHandler.getMethod().getParameterTypes();

                //2. 创建一个参数数组[对应实参数组], 在后面调用目标方法时 会使用到
                Object[] params = new Object[parameterTypes.length];

                //3. 遍历parameterTypes形参数组, 根据形参数组信息, 将实参填充到实参数组
                //   即根据该参数在目标方法上对应的位置 在实参数组params 对应的位置填充数据
                //   当传入params 给 目标方法使用时 填充的数据 不能错位

                // 这里使用普通的循环是为了拿到索引信息
                for (int i = 0; i < parameterTypes.length; i++) {
                    //取出每一个形参的类型
                    Class<?> parameterType = parameterTypes[i];
                    // 如果形参数组在这个索引位置形参是HttpServletRequest,
                    // 就在实参数组中该索引位置填充request对象
                    // 在原生SpringMVC中 是按照类型来进行匹配 这里简化成按照名字匹配了
                    if ("HttpServletRequest".equals(parameterType.getSimpleName())){
                        params[i] = request;
                    }else if ("HttpServletResponse".equals(parameterType.getSimpleName())){
                        params[i] = response;
                    }
                }

                // 在反射调用目标方法之前 在获取前端传递的参数之前 处理中文乱码问题
                request.setCharacterEncoding("utf-8");

                // 将http请求参数封装到params数组中，老韩提示，要注意填充实参的时候，顺序问题
                // 1.获取http请求的参数集合
                Map<String, String[]> parameterMap = request.getParameterMap();

                // 2. 遍历parameterMap 将请求参数 填充到实参数组params
                // 解读
                // http://localhost:8080/monster/find?name=牛魔王&hobby=打篮球&hobby=喝酒
                // String[]:表示http请求的参数值，即使前端http提交的只有一个值 这里也是用数组进行接收的
                // 因此要考虑 不管提交的字段值是一个还是多个  这里都是使用数组接收处理的情况

                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {

                    // 取出key 这里取出的name 就是对应请求的参数名
                    String name = entry.getKey();

                    // 说明：这里老韩只考虑提交的参数是单值的情况，即不考虑类似checkbox提交的数据
                    // 这里做了简化，考虑多值也不能
                    String value = entry.getValue()[0];

                    // 我们得到请求的参数对应目标方法的第几个形参 然后将其填充
                    // 这里专门写一个方法 得到请求参数对应的是第几个形参
                    int parameterIndex = getIndexRequestParameterIndex(hspHandler.getMethod(), name);

                    if (parameterIndex != -1){
                        //说明找到目标方法形参对应位置 有@RequestParam("name") 注解 同时value 匹配上了url传过来的name
                        params[parameterIndex] = value;
                    }else {
                        // 说明没有找到@RequestParam("name")注解对应的参数
                        // 比如 目标方法形参位置没有写@RequestParam("name") 就按照默认的方式进行处理

                        //思路
                        // 得到目标方法的所有的参数的参数名 - 编写一个方法获取形参名getParameterNames()
                        // 对得到目标方法的所有形参名进行遍历 如果匹配上了 就把当前请求的参数值填充到params
                        List<String> parameterNames = getParameterNames(hspHandler.getMethod());

                        for (int i = 0; i < parameterNames.size(); i++) {
                            String parameterName = parameterNames.get(i);
                            //if (parameterName.equals(name)){
                            if (name.equals(parameterName)){
                                // 说明url传过来的字段名 和 目标方法的形参名匹配上了
                                // 将该url传过来的字段name的值设置给该位置的实参数组对应的位置上
                                params[i] = value;
                                break;
                            }
                                // 前端 可能瞎传了一个 namex 同时 目标方法没有找到形参名为namex的形参
                                //return;
                                //throw new RuntimeException("前端 可能瞎传了一个 namex 同时 目标方法没有找到形参名为namex的形参");

                        }

                    }

                }


                /*
                * 解读：
                * 1.下面这种写法，其实是针对目标方法有两个固定形参
                * m1(HttpServletRequest request,
                                 HttpServletResponse response)
                * 如果写的目标方法里的形参 不是这两个形参 就会调用失败
                * 2.这里将需要传递给目标方法的 实参=> 封装到参数数组 =》 然后以反射调用的方式传递给目标方法
                * 3.因为反射的invoke()方法 可变参数可以直接传进去一个数组进去 底层会 遍历取出参数
                * public Object invoke(Object obj, Object... args)
                *
                * */

                //hspHandler.getMethod()
                //        .invoke(hspHandler.getController(), request, response);

                // 调用并接收目标方法返回的结果 result
                Object result = hspHandler.getMethod()
                        .invoke(hspHandler.getController(), params);

                //这里我们直接对返回的结果进行解析=> 原生springmvc 可以通过视图解析器来完成
                //这里老师直接解析，只要把视图解析器的核心机制讲清楚就OK

                // 判断返回的结果的运行类型 因为返回的结果可能是String/View/ModelAndView
                if(result instanceof String){
                    // 如果返回的是String类型
                    // 该结果可能是 "forward:/login_ok.jsp" 或  "redirect:/login_ok.jsp"  或者"/login_ok.jsp"

                    // 先强转成String类型
                    String ViewName = (String) result;
                    // 判断该字符串中是否包含 冒号 :
                    // 如果包含冒号 则可能是 "forward:/login_ok.jsp" 或  "redirect:/login_ok.jsp"
                    if ( ViewName.contains(":")){
                        String[] split = ViewName.split(":");
                        // ViewType 决定将要请求转发还是请求重定向
                        String viewType = split[0]; // forward | redirect
                        // ViewPage 决定将要访问的页面的地址
                        String viewPage = split[1]; //是你要跳转的页面名

                        // 判断是要请求转发还是重定向
                        if ("forward".equals(viewType)){
                            // 说明要进行请求转发 进行请求转发
                            request.getRequestDispatcher(viewPage).forward(request,response);
                        }else if ("redirect".equals(viewType)){
                            // 说明要进行请求重定向 进行 重定向
                            response.sendRedirect(viewPage);
                        }

                    }else {
                        // 说明 返回String类型的结果中没有冒号 ":"
                        // ,就按照默认的方式进行处理 默认进行请求转发

                        request.getRequestDispatcher(ViewName).forward(request,response);
                    }
                }// 如果返回的是其他类型的结果 可以扩展
                else if (result instanceof ArrayList){ // 如果是ArrayList
                    //判断目标方法上是否有@ResponseBody
                    Method method = hspHandler.getMethod();
                    if (method.isAnnotationPresent(ResponseBody.class)){
                        // 把result [ArrayList] 转成json格式数据 => 返回
                        // 这里我们需要使用到java中如何将ArrayList 转成 json
                        // 这里我们需要使用jackson包下的工具类可以轻松搞定
                        // 老师演示如何操作
                        ObjectMapper objectMapper = new ObjectMapper();
                        String resultJson = objectMapper.writeValueAsString(result);

                        // 这里简单处理直接返回
                        response.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.write(resultJson);
                        writer.flush();
                        writer.close();


                    }


                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 编写方法 返回请求参数是目标方法的第几个形参

    /**
     *
     * @param method 目标方法
     * @param name 请求的参数名
     * @return 是目标方法的第几个形参
     */
    public int getIndexRequestParameterIndex(Method method , String name){

        // 获取到目标方法的所有的类型
        //Class<?>[] parameterTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            //取出当前的形参参数
            Parameter parameter = parameters[i];

            if (parameter.isAnnotationPresent(RequestParam.class)){
                // 说明这个参数的 参数位置有注解RequestParam
                // 获取该注解
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                // 获取该参数注解上的value
                String value = requestParam.value();
                // 判断该参数是否和传进来的参数名一致
                if (name.equals(value)){
                    // 说明找到了 即该方法形参位置上有 RequestParam 同时 value和url中传递的参数名一致
                    // 返回这个参数 在方法的形参列表中的位置
                    return i;
                }

            }
        }



        // 没有找到/传入的name 和 RequestParam注解上的value没有匹配上 返回-1
        return -1;
    }


    // 编写方法, 得到目标方法的所有形参的名称，并放入到集合中返回

    /**
     *
     * @param method 目标方法
     * @return 所有形参的名称 并放入到集合中返回
     */
    public List<String> getParameterNames(Method method){

         List<String> parameterList = new ArrayList<>();
        // 获取到所有的参数名 有一个小细节
        // 在默认情况下 通过parameter.getName() 得到的名字不是真正的名字
        // 而是 [arg0,arg1,arg2...]  这里要引入一个插件，使用java8的特性 才能解决

        Parameter[] parameters = method.getParameters();
        //遍历parameters 放入到parameterList
        for (Parameter parameter : parameters) {


            String parameterName = parameter.getName();
            // 这里是有序集合 按照顺序一个一个添加进去的
            parameterList.add(parameterName);

        }
        System.out.println("目标方法 形参参数列表= " + parameterList);
        return parameterList;
    }




}
