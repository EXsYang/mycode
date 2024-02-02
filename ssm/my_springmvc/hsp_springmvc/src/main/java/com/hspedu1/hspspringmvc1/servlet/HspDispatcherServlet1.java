package com.hspedu1.hspspringmvc1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hspedu1.hspspringmvc1.annotation.Controller;
import com.hspedu1.hspspringmvc1.annotation.RequestMapping;
import com.hspedu1.hspspringmvc1.annotation.RequestParam;
import com.hspedu1.hspspringmvc1.annotation.ResponseBody;
import com.hspedu1.hspspringmvc1.context.HspWebApplicationContext1;
import com.hspedu1.hspspringmvc1.handler.HspHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-10-06-12:27
 * @description: 该类充当原生的springmvc的DispatcherServlet 中央控制器
 * 是一个servlet 因此需要在web.xml文件中进行配置
 * 1. HspDispatcherServlet充当原生DispatcherServlet
 * * 2. 本质是一个Servlet,继承HttpServlet
 * * 3. 提示：这里我们需要使用到javaweb 讲解的servlet
 * * <p>
 * * 注意: 对Maven项目而言 src/main/resource/  目录就是类的加载路径 classpath
 */
public class HspDispatcherServlet1 extends HttpServlet {


    HspWebApplicationContext1 hspWebApplicationContext = null;
    //定义属性 用于存放hspHandler 保存映射关系对象的集合
    private List<HspHandler> hspHandlerList =
            new ArrayList<>();


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        //使用servletConfig对象 动态的获取web.xml的初始化参数
        String configLocationString = servletConfig.getInitParameter("contextConfigLocation");
        //取出要扫描的 spring文件名
        String[] split = configLocationString.split(":");
        String configLocation = split[1];


        // 创建我们自己的spring容器 并初始化
        hspWebApplicationContext = new HspWebApplicationContext1(configLocation);
        hspWebApplicationContext.init();

        //初始化时将url映射关系生成HspHandler对象 并放入到hspHandlerList集合
        initHandlerMapping();
        System.out.println("映射关系集合hspHandlerList= " + hspHandlerList);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HspDispatcherServlet1 doPost() 被调用...");

        // 完成分发请求到控制器 单独写一个方法
        executeDispatch(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }


    // 定义方法 用于初始化时完成映射 将映射关系保存到hspHandlerList集合中
    public void initHandlerMapping() {
        // 首先判断ioc容器中 是否有bean对象
        if (hspWebApplicationContext.ioc.isEmpty()) {
            return;
        }

        // 如果ioc容器中有bean对象
        // 遍历ioc 判断对象所在的类是否为Controller 取出所有的方法 判断是否为目标方法
        for (Map.Entry<String, Object> entry : hspWebApplicationContext.ioc.entrySet()) {
            String beanName = entry.getKey();
            Object controller = entry.getValue();
            // 得到clazz对象
            Class<?> clazz = controller.getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                // 说明该类是一个Controller 不是Service/Repository
                // 取出该类的所有方法
                Method[] declaredMethods = clazz.getDeclaredMethods();
                if (declaredMethods.length > 0) {
                    // 如果方法的个数大于0 遍历所有方法
                    for (Method declaredMethod : declaredMethods) {
                        // 判断该方法是否为目标方法 即判断该方法上是否有注解@RequestMapping
                        if (declaredMethod.isAnnotationPresent(RequestMapping.class)) {
                            // 说明该方法为目标方法
                            // 获取该方法上的@RequestMapping注解
                            RequestMapping requestMappingAnnotation = declaredMethod.getAnnotation(RequestMapping.class);

                            String url = requestMappingAnnotation.value();

                            // 创建hspHandler对象 保存映射关系 并放入到hspHandlerList集合中
                            HspHandler hspHandler = new HspHandler(url, controller, declaredMethod);

                            hspHandlerList.add(hspHandler);


                        }


                    }


                }

            }

        }
    }


    // 编写方法完成分发请求到控制器目标方法
    public void executeDispatch(HttpServletRequest request, HttpServletResponse response) {

        String requestURI = request.getRequestURI();
        System.out.println("requestURI= " + requestURI);

        //判断url映射集合hspHandlerList是否有元素 如果没有直接返回404
        if (hspHandlerList.isEmpty()) {

            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.write("<h1>404 FILE NOT FOUND</h1>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        //说明有映射关系的集合 进行匹配
        for (HspHandler hspHandler : hspHandlerList) {
            if (requestURI.equals(hspHandler.getUrl())) {
                //说明有匹配的url 使用反射调用目标方法
                Method method = hspHandler.getMethod();
                //防止方法为private 进行反射暴破
                method.setAccessible(true);
                try {
                    // 使用反射 调用目标方法
                    // 暂时先认为所有目标方法的形参都是request,response 之后在优化

                    // 拿到method方法后 拿到所有的形参参数类型
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    // 创建一个用于保存实参的参数数组params 数组的长度与目标方法所有
                    // 形参类型数组parameterTypes长度相同
                    //
                    Object[] params = new Object[parameterTypes.length];

                    //遍历目标方法参数的参数类型 判断是否有 使用普通的for循环 可以拿到
                    //形参在第几个位置
                    for (int i = 0; i < parameterTypes.length; i++) {
                        //这里简化处理 使用形参的类型名进行判断
                        //String typeName = parameterTypes[i].getTypeName();
                        //System.out.println("目标方法形参类型的typeName= " + typeName);
                        //目标方法形参类型的typeName= javax.servlet.http.HttpServletRequest

                        //String name = parameterTypes[i].getName();
                        //System.out.println("目标方法形参类型的name= " + name);
                        //目标方法形参类型的name= javax.servlet.http.HttpServletRequest

                        //String simpleName = parameterTypes[i].getSimpleName();
                        //System.out.println("目标方法形参类型的simpleName= " + simpleName);
                        //目标方法形参类型的simpleName= HttpServletRequest

                        //使用目标方法形参类型的simpleName 和字符串进行equals判断
                        String simpleName = parameterTypes[i].getSimpleName();

                        if ("HttpServletRequest".equals(simpleName)) {
                            //说明该目标方法的第i个位置 参数的类型是HttpServletRequest
                            //将request对象封装到实参数组的第i位置
                            params[i] = request;
                        } else if ("HttpServletResponse".equals(simpleName)) {
                            //说明该目标方法的第i个位置 参数的类型是HttpServletResponse
                            //将response对象封装到实参数组的第i位置
                            params[i] = response;
                        }
                    }

                    // 在获取http请求的参数前解决中文乱码问题
                    request.setCharacterEncoding("utf-8");

                    // 获取前端传过来的所有参数
                    // 说明：
                    // String 是参数名
                    // String[] 是参数值 使用的是数组进行保存的 即使是一个参数值
                    // 也是使用数组进行保存 String[0]
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    System.out.println("parameterMap= " + parameterMap);
                    //parameterMap= org.apache.catalina.util.ParameterMap@36f20747
                    // 这里如果前端直接通过地址栏发出一个不带参数的请求 得到的parameterMap为空集合
                    // 虽然还是会生成map对象但是里面却没有元素
                    if(parameterMap.isEmpty()){
                        System.out.println("parameterMap集合为空集合 即前端发送http请求是没有带任何参数过来");
                        // 集合为空 和集合为null是两回事
                    }

                    // 因此不会进入到for循环中
                    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                        // url传递时的参数名
                        String requestParamName = entry.getKey();


                        // value 就是参数值 注意这里得到的是一个String数组
                        //注意这里的value 是一个数组 就是遍历到的请求数据保存的值
                        //注意要取出数组第0位置的值 才是真正需要的值！！
                        String[] value = entry.getValue();
                        // 我们得到请求的参数对应目标方法的第几个形参 然后将其填充
                        // 这里专门写一个方法 得到请求参数对应的是第几个形参
                        int paramIndexFromTargetMethod = getParamIndexFromTargetMethod(requestParamName,method, request);
                        System.out.println("paramIndexFromTargetMethod= " + paramIndexFromTargetMethod);
                        if (-1 != paramIndexFromTargetMethod){
                            // 说明匹配成功 将该value 设置到实参数组的该位置上
                            params[paramIndexFromTargetMethod] = value[0];
                        }else{
                            // 匹配失败 按照形参名匹配请求参数
                            System.out.println("匹配失败 按照形参名匹配请求参数");
                            // 拿到方法的形参名
                            // 在默认情况下 method.getParameters(); 遍历后得到parameter
                            // 通过parameter.getName() 得到的名字不是真正的名字
                            // 而是 [arg0,arg1,arg2...]  这里要引入一个插件，使用java8的特性 才能解决
                            // 这里专门写一个方法  getIndexFromParamName() 返回值是匹配成后请求参数在目标方法形参数组中的位置
                            // 下面按照 请求参数名 <=> 目标方法形参名 进行匹配
                            int indexFromParamName = getIndexFromParamName(requestParamName, method);
                            if (-1 != indexFromParamName){
                                // 说明请求参数名 和目标方法参数名匹配成功
                                // 将该请求参数名对应的实参封装到实参数组params对应的参数位置
                                params[indexFromParamName] = value[0];
                                // 只要有一个参数的参数名和前端传过来的请求参数匹配上了 就跳出循环
                                // 不再填充后面两个名称匹配上的请求参数 实参数组 与目标形参 之间的对应关系了
                                break;
                            }

                            // 走到这 说明前端请求参数与目标方法形参名没有匹配上
                            // 前端 可能瞎传了一个 namex 同时 目标方法没有找到形参名为namex的形参
                            //return;
                            //throw new RuntimeException("前端 可能瞎传了一个 namex 同时 目标方法没有找到形参名为namex的形参");

                        }
                    }

                    //method.invoke(hspHandler.getController(),request,response);
                    //直接将实参数组传入
                    //接收目标方法返回的参数 即返回的可能时String、ModelAndView、View
                    Object result = method.invoke(hspHandler.getController(), params);
                    //判断返回的结果的运行类型
                    if (result instanceof String){
                        // 说明返回的是一个字符串
                        // 先强转成String类型并按照冒号":"对其进行分割
                        String[] split = ((String) result).split(":");
                        // 当长度为1 时 viewName 就是默认请求转发的视图名
                        String viewName = split[0];
                        //判断长度是否大于1 分割时没有匹配上 分割失败默认返回值的length为1
                        if (split.length > 1){
                            // 说明该字符串中包含冒号 : 可能是 "forward:/login_ok.jsp"或者"redirect:/xxx/xxx.jsp"
                            // 判断是请求转发还是重定向
                            //
                            String viewType = split[0]; // forward | redirect
                            String viewPage = split[1]; // 要请求转发/重定向到的页面
                            if ("forward".equals(viewType)){
                                //说明是请求转发
                                //进行请求转发
                                request.getRequestDispatcher(viewPage).forward(request,response);
                            }else if ("redirect".equals(viewType)){
                                //说明是请求重定向
                                //进行重定向
                                response.sendRedirect(viewPage);
                            }
                        }else {
                            // 说明不包含冒号 , 目标方法 返回的result="/login_ok.jsp"
                            // 使用默认处理 进行请求转发
                            request.getRequestDispatcher(viewName).forward(request,response);
                        }


                    }// 可以根据目标方法返回值的类型不同 进行不同的业务
                    else if (result instanceof ArrayList){
                        // 返回值的运行类型是ArrayList
                        // 判断目标方法上 是否有@ResponseBody注解
                        if (method.isAnnotationPresent(ResponseBody.class)){
                            // 这里可以根据该注解上标注的类型 确定给前端返回什么类型的值
                            // 这里简化处理 只要有该注解 就返回json格式的数据

                            // 将ArrayList转化为json 这里使用jackson 的工具类简单完成
                            ObjectMapper objectMapper = new ObjectMapper();
                            String goodsListJson = objectMapper.writeValueAsString(result);
                            System.out.println("goodsListJson= " + goodsListJson);

                            // 将该json数据返回给浏览器
                            response.setContentType("text/html;charset=utf-8");
                            PrintWriter writer = response.getWriter();
                            writer.write(goodsListJson);
                            writer.flush();
                            writer.close();


                        }



                    }



                    // 调用完方法后直接跳出循环
                    break;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                //直接返回404
                response.setContentType("text/html;charset=utf-8");
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write("404 file not found");
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }

    //编写方法用于返回形参名在目标方法中的位置

    /**
     *
     * @param paramName 前端传过来的字段名
     * @param method 目标方法
     * @param request
     * @return
     */
    public int getParamIndexFromTargetMethod(String paramName,Method method, HttpServletRequest request) {

        // 封装目标方法上带有注解@RequestParam的参数
        // 拿到目标方法的所有的形参
        Parameter[] parameters = method.getParameters();
        //System.out.println("获取目标方法的所有的形参名=");
        // 遍历形参数组
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            //判断这个形参上是否有@RequestParam注解修饰
            if (parameter.isAnnotationPresent(RequestParam.class)) {
                //这个形参上有@RequestParam注解修饰
                //拿到该@RequestParam注解
                RequestParam parameterRequestParamAnnotation =
                        parameter.getAnnotation(RequestParam.class);
                //拿到该注解上面值
                String name = parameterRequestParamAnnotation.value();
                // url传递参数的字段名 必须与这个name一致才可以获取到参数
                // 即通过name从request请求中获取数据 并封装到实参数组中 再传入到目标方法中
                if (paramName.equals(name)) {
                    //说明目标方法的形参上有RequestParam注解 同时和url匹配上了
                    //返回所在的形参的位置
                    return i;
                }
            }


        }


        //如果该形参名  没有找到/传入的name 和 RequestParam注解上的value没有匹配上  在目标方法中不存在就返回-1
        return -1;
    }


    //编写方法 当使用请求参数 和目标方法形参的参数名 进行匹配时 返回匹配后的参数位置
    public int getIndexFromParamName(String requestParamName,Method method){
        System.out.println("传进来的请求参数名requestParamName= " + requestParamName);
        Parameter[] parameters = method.getParameters();
        // 创建一个String[]用于保存形参名
        //String[] paramNames = new String[parameters.length];
        // 创建一个paramNamesList用于保存形参名
        ArrayList<String> paramNamesList = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            String targetMethodParamName = parameters[i].getName();
            paramNamesList.add(targetMethodParamName);
            //System.out.println("目标方法形参名parameters[i].getName()= " + parameters[i].getName());
            if (requestParamName.equals(targetMethodParamName)){
                //说明匹配上了 可以按照 请求参数名 <=> 目标方法形参名 进行匹配
                // 返回该参数在方法形参中的位置 找到直接返回该值 就不往下循环了！！
                return i;
            }

        }
        System.out.println("目标方法形参名的集合paramNamesList= " + paramNamesList);

        return -1;

    }

}
