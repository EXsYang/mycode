package com.hspedu.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangda
 * @description:
 *         /***********************************
 *          *  获取和请求参数相关信息, 注意要求在返回数据前，获取参数
 *          *    //返回给浏览器，回显
 *          *         response.setContentType("text/html;charset=utf-8");
 *          *
 *          *         //解决乱码的方式
 *          *         //老韩解读
 *          *         //1. 设置服务器使用utf-8
 *          *         //response.setCharacterEncoding("utf-8");
 *          *         //2. 设置浏览器端是utf-8, 而且类型是 text/html
 *          *         //response.setHeader("Content-Type", "text/html;charset=utf-8");
 *          *         PrintWriter writer = response.getWriter();
 *
 *         //解决接收参数的中文乱码问题, 老师提示，写在 getParameter前.
         *         //1. 获取表单的数据[单个数据]
         *         //username=tom&pwd=&hobby=hsp&hobby=spls
         *              request.setCharacterEncoding("utf-8");
 * @create 2023-05-25-3:31
 */
public class HttpServletRequestMethods extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在这里使用request对象，获取表单提交的各种数据
        System.out.println("HttpServletRequestMethods doPost()被调用...");
        /***********************************
         *  获取和http请求头相关信息
         ***********************************/

        System.out.println("请求的资源路径URI= " + request.getRequestURI());
        //http://主机/uri
        System.out.println("请求的统一资源定位符（绝对路径）URL= " + request.getRequestURL());
        System.out.println("请求的客户端ip 地址= " + request.getRemoteAddr());//本地就是127.0.01
        //思考题：如发现某个ip 在10s中，访问的次数超过 100次，就封ip
        //实现思路： 1用一个集合concurrentHashmap[ip:访问次数] 2[线程/定时扫描] 3 做成处理
        // 获取http请求头的信息，可以指定其他，比如 User-Agent , Host等待 老师就举一个例子
        System.out.println("http请求头HOST= " + request.getHeader("Host"));//不区分大小写
        // 说明，如果我们希望得到请求的头的相关信息，可以使用request.getHeader("请求头字段")
        System.out.println("该请求的发起地址是= " + request.getHeader("referer"));
        // 请获取访问网站的浏览器是什么？
        //User-Agent:
        //Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36
        String[] s = request.getHeader("User-Agent").split(" ");
        if (s.length > 10) {
            System.out.println("使用的浏览器： " + s[s.length - 2].split("\\/")[0]);
        } else if (s.length < 10) {
            System.out.println("使用的浏览器： " + s[s.length - 1].split("\\/")[0]);

        }

        // 取出FireFox, 取出最后

        //获取 Cookie
        // 	JSESSIONID=8CBBD23BDE01BAE6705E03C5C8916BD1
        String[] cookies = request.getHeader("Cookie").split("=");
        System.out.println("Cookie= " + cookies[1]);

        //课堂练习: 要求同学们取出  Windows NT 10.0  和 Win64
        //System.out.println("课堂练习： " + s[1].split("[\\w]\\s[\\w]\\s\\d*\\.\\d+")[0]);
        //Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36
        //String[] s2 = request.getHeader("User-Agent").split("\\(.+\\)");
        //String[] s2 = request.getHeader("User-Agent").split("\\(.+?\\)+");
        String content = request.getHeader("User-Agent");

        // Enumeration<String> headers = request.getHeaders("");

        System.out.println("课堂练习： ");

        //找到： (Windows NT 10.0; Win64; x64)
        //找到： (KHTML, like Gecko)
        //String regStr = "\\(.+?\\)+";
        //Pattern pattern = Pattern.compile(regStr);
        //Matcher matcher = pattern.matcher(content);
        //while (matcher.find()){
        //    System.out.println("找到： " + matcher.group(0));
        //}

        String regStr = "\\w+\\s+\\w+\\s+\\d+\\.\\d";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("找到： " + matcher.group(0));
        }


        //if (matcher.find()){
        //    System.out.println("匹配成功");
        //}else{
        //    System.out.println("匹配失败");
        //}


        // 主要是Get / Post
        System.out.println("http请求方式~= " + request.getMethod());
        /***********************************
         *  获取和请求参数相关信息, 注意要求在返回数据前，获取参数
         *    //返回给浏览器，回显
         *         response.setContentType("text/html;charset=utf-8");
         *
         *         //解决乱码的方式
         *         //老韩解读
         *         //1. 设置服务器使用utf-8
         *         //request.setCharacterEncoding("utf-8");
         *         //2. 设置浏览器端是utf-8, 而且类型是 text/html
         *         //response.setHeader("Content-Type", "text/html;charset=utf-8");
         *         PrintWriter writer = response.getWriter();
         ***********************************/

        //解决接收参数的中文乱码问题, 老师提示，写在 getParameter前.
        //1. 获取表单的数据[单个数据]
        //username=tom&pwd=&hobby=hsp&hobby=spls
        //1. 设置服务器使用utf-8接收浏览器发送来的数据，对浏览器内容显示没有影响
        request.setCharacterEncoding("utf-8"); // 一定要设置在获取参数之前！！

        /*如下方式不能解决服务器端获取浏览器参数的中文乱码问题 即这种设置后 下面的用户名和密码依然是乱码*/
        //response.setCharacterEncoding("utf-8");//response对象也有setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        //在获取参数的过程中 再根据下面的前端参数是否有中文，设置编码方式是不行的 设置不生效！！！
        //request.setCharacterEncoding("utf-8"); //设置不生效！！！！
        String pwd = request.getParameter("pwd");
        String[] hobbies = request.getParameterValues("hobby");

        System.out.println("username= " + username);
        System.out.println("pwd= " + pwd);
        for (String hobby : hobbies) {
            System.out.println("喜欢的老师：" + hobby);
        }


        //推而广之, 如果是 单选 , 下拉框 等等. => 作业布置
        //单选框
        String sex = request.getParameter("sex");
        System.out.println("sex= " + sex);

        //下拉框
        //公司：company
        //国家：country
        //String country = request.getParameter("%E5%88%97%E8%A1%A8%E5%90%8D%E7%A7%B0");//null
        String country = request.getParameter("列表名称");//
        System.out.println("country= " + country);
        String textarea = request.getParameter("textarea");//
        System.out.println("textarea= " + textarea);

        //返回接收到的信息， 给浏览器回显
        //本质就是在http响应头，加上 Content-Type: text/html;charset=utf-8
        //说 text/html 表示返回的数据类型，浏览器会根据这个类型来解析数据
        // text/plain 表示返回的数据，请浏览器使用文本方式解析
        // application/x-tar 表示返回的是文件，浏览器就会以下载文件的方式处理

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>提交的用户名： " + username + "</h1><br/>");
        writer.print("<h1>提交的密码： " + pwd + "</h1><br/>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
