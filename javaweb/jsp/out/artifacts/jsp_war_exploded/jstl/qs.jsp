<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/18
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%--注意事项：
taglib引入标签放在第一行！！
导入jstl jar包后，要重新发布web工程，否则不识别jstl!!

EL 表达式是为了替换 jsp 中的表达式脚本，JSTL 是为了替换代码脚本。
表达式脚本: <%=%>
代码脚本:   <%%>
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jstl的快速入门</title>
</head>
<body>
<h1>jstl的快速入门</h1>
<%--老韩解读
    1. c:if ... 类似
    2. if(10>2){
       out.println("<h1>10 > 2 成立~</h1>")
    }
--%>
<c:if test="${10 > 2}">
    <h1>10 > 2 成立！</h1>
</c:if>



</body>
</html>
