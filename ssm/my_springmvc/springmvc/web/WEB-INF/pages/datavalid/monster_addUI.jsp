<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/10/10
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>添加妖怪~~</h3>
<!-- 这里的表单，我们使用 springMVC 的标签来完成
特别说明几点:
1. SpringMVC 表单标签在显示之前必须在 request 中有一个 bean, 该 bean 的属性
和表单标签的字段要对应!
request 中的 key 为: form 标签的 modelAttrite 属性值， 比如这里的 monsters
2. SpringMVC 的 form:form 标签的 action 属性值中的 / 不代表 WEB 应用的根目
   如果在 这里的action 中写 "/save" 找到的是 http://localhost:8080/save

3. 这里老韩使用springmvc的标签的主要的目的是方便提示信息回显
4. 使用springMVC 表单标签 必须指定属性 modelAttribute ,也不可以不写这个属性 会报错
   属性的值必须和request域中设置的bean的属性名保持一致 即在Handler处理器中必须设置一个bean
   如 map.put("monster", new Monster()); 而这里modelAttribute的属性值 必须与其保持一致
   看成一个规定 就是要这样写
录. -->

<%--<form:form action="/save" method="post" modelAttribute="monster">--%>

<%--下面这里使用表达式脚本会报错 ：
attribute value for [action] is not properly terminated
description The server encountered an internal error that prevented it from fulfilling this request.--%>
<%--<form:form action="<%=request.getContextPath()%>/save" method="post" modelAttribute="monster">--%>

<form:form action="save" method="post" modelAttribute="monster">
    妖怪名字: <form:input path="name"/> <form:errors path="name"/><br><br>
    <%--form:errors标签来显示错误消息, 这个标签，需要写在<form:form> 标签内生效--%>
    妖怪年龄~: <form:input path="age"/> <form:errors path="age"/><br><br>
    电子邮件: <form:input path="email"/> <form:errors path="email"/> <br><br>
    生日: <form:input path="birthday"/> <form:errors path="birthday"/> 按照 "yyyy-MM-dd" 格式输入 <br><br>
    薪水: <form:input path="salary"/> <form:errors path="salary"/> 按照 "###,###.##" 格式输入<br><br>
    <input type="submit" value="添加妖怪"/>
</form:form>
</body>
</html>
