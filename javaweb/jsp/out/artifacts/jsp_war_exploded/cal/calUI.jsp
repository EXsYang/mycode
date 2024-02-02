<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/11
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>简单的计算器</title>

    <script type="text/javascript">
        function checkReg() {
            // 1、input标签的name 属性是规定 input 元素的名称。用于对提交到服务器后的表单数据进行标识，
            //    或者在客户端通过 JavaScript 引用表单数据。只有设置了 name 属性的表单元素才能在
            //    提交表单时传递它们的值。
            // 2、id 在HTML中的作用是给一个单元(元素,标签)一个独一无二的标识或标记,让浏览器在分析
            //    处理网页时找到id所在的地方。并且要符合标识的要求,如大小写敏感,最好不要包含下划线
            //    (不兼容CSS)
            // 3、id 一般用于css和js中引用,name用于表单提交,只有加了name属性的标签元素才会提交到服务器。
            //    var inputs = document.getElementsByTagName("input");
            var input1 = document.getElementById("num1");
            var input2 = document.getElementById("num2");

            // alert("num1= " + input1);
            // alert("num2= " + input2);
            // alert("num1Val= " + input1.value);
            // alert("num2Val= " + input2.value);
            // 设置正则规则
            let pattern = /^[-]?([1-9]\d*|0)$/;

            if (!pattern.test(input1.value)) {
                alert("请输入整数！")
                return false;
            }
            if (!pattern.test(input2.value)) {
                alert("请输入整数！")
                return false;
            }
            return true;
        }
    </script>

</head>
<body>
<h1>jsp版本-计算器</h1>
<form action="<%=request.getContextPath()%>/calServlet" method="post" onsubmit="return checkReg()">
    <%--输入的必须是整数--%>
    <%--num1:<input type="text" name="num1" pattern="[-]?\d+"><br/>--%>
    <%--num2:<input type="text"name="num2" pattern="[-]?\d+"><br/>--%>


    <%--        num1:<input type="text"  name="num1"><br/>--%>
    <%--        num2:<input type="text"  name="num2"><br/>--%>
    <%-- document.getElementById("num1");得到的是null  --%>
    <%-- document.getElementById("num2");得到的是null  --%>


    <%

        Object num1 = session.getAttribute("num1");
        Object num2 = session.getAttribute("num2");

    %>


    <%
        if (num1 != null && num2 != null) {
            System.out.println("num1= " + (String) num1);%>
    num1:<input type="text" id="num1" name="num1">num1错误：<%=session.getAttribute("num1")%><br/>
    num2:<input type="text" id="num2" name="num2">num2错误：<%=session.getAttribute("num2")%><br/>
    <%
        }%>

    <%
        System.out.println("num1= " + num1);
        if (num1 == null) {
    %>
    num1:<input type="text" id="num1" name="num1"> <br/>
    <%
        }
    %>
    <%
        System.out.println("num1= " + num2);
        if (num2 == null) {
    %>
    num2:<input type="text" id="num2" name="num2"> <br/>
    <%
        }
    %>

    <p>运算符号：
        <select name="operator">
            <option value="add" selected>+</option>
            <option value="subtract">-</option>
            <option value="multiply">*</option>
            <option value="divide">/</option>
        </select>
    </p>
    <input type="submit" value="计算">

</form>
运算符号：operator:add, subtract, multiply and divide
</body>
</html>
