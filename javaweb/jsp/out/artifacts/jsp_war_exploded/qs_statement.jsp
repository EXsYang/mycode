<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp声明脚本</title>
</head>
<body>
<h1>jsp声明脚本</h1>

<%--
    当导入 servlet-api.jar	jsp-api.jar
    (jasper.jar 将jsp翻译成.java文件后 查看继承的类  查看相关的类图 相关的父类在此包下)
    后就可以使用 <%!%> <%%> <%=%> el表达式:${}、还有jsp标签了	<jsp:forward page="/bb.jsp"></jsp:forward>
    要想使用jstl标签库 替代 <%%> 代码脚本 需要导入两个jar包 即
    taglibs-standard-impl-1.2.1.jar
    taglibs-standard-spec-1.2.1.jar
    如上 相关笔记见 JavaWeb随手笔记.md

<% %> 代码脚本
<%= %> 表达式脚本
<%! %> 声明脚本

<% %>和< %! %>的不同之处在于:翻译不同，定义不同，声明不同。
一、翻译不同
1. <%%>:将<%%>的内容转换为Servlet的服务方法。
2.<%! %>: <%! %>的内容被直接翻译到Servlet类中。
二、定义不同
1. <%%>: <%%>定义局部变量或调用方法，但不能定义方法。
2.<%! %>: <%!局部变量和方法不能仅由%>定义。
三、声明不同
1.<%%>:<%%>不能声明方法、属性、全局变量。
2.<%! %>: <%! %>可以声明方法、属性和全局变量。
--%>

<%--
首先，我们要了解jsp运行原理。JSP的本质就是一个Servlet，JSP的运行之前会先被Tomcat
服务器翻译为.java文件，然后在将.java文本编译为.class文件，而我们在访问jsp时，处理请求
的就是那个翻译后的类。
1.<% %>叫做脚本片段，其中写的内容会翻译在Servlet的Service方法中，显然我们可以在Service方法
中定义局部变量或者调用其他方法，但是不能在Service中再定义其他的方法，也就是我们可以在<%%>中
定义局部变量或者调用方法，但不能定义方法。在jsp页面可以有多个脚本片段，但是多个脚本片段之间要
保证结构完整。
2.<%!%>称作声明，其中写的内容将来会直接翻译在Servlet类中，因为我们可以在类中定义方法和属性
以及全局变量，所以我们可以在<%!%>中声明方法、属性、全局变量。
3.<%=%>称作jsp表达式，用于将已经声明的变量或者表达式输出到网页上面。
--%>
<%!
    //这里我们可以声明该jsp需要使用的属性，方法，静态代码块, 内部类
    //老师一句话: 也就是给 qs_statement.jsp 对应的 statement_jsp 类定义成员
    //1. 属性
    private String name = "jack";
    private int age;
    private static String company;

    //2 方法
    public String getName() {
        return name;
    }
    //3. 静态代码块
    static {
        company = "字节跳动";
    }
%>
<%

%>
</body>
</html>
