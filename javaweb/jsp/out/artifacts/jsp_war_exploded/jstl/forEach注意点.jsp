<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/7/30
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ForEach</title>
</head>
<body>
<h1>forEach注意点！</h1>
</body>
</html>

<%--
<html>
<head>
    <title>ForEach</title>
</head>
<body>
在JSP的开发中，迭代是经常要使用到的操作。例如，逐行的显示查询的结果等。
在早期的JSP中，通常使用Scriptlets来实现Iterator或者Enumeration对象的迭代输出。
现在，通过JSTL的迭代标签可以在很大的程度上简化迭代操作。

JSTL所支持的迭代标签有两个，分别是<c:forEach>和<c:forTokens>。在这里介绍的是<c:forEach>标签。

    简单点说，<c:forEach>标签的作用就是迭代输出标签内部的内容。它既可以进行固定次数的迭代输出，也可以依据集合中对象的个数来决定迭代的次数。

        <c:forEach>标签，需要与el表达式联合使用
            < c: forEach>标签的语法定义如下所示。

            < c: forEach var="每个变量名字"   items="要迭代的list"   varStatus="每个对象的状态"

            begin="循环从哪儿开始"    end="循环到哪儿结束"    step="循环的步长">

            循环要输出的东西

            </ c: forEach>

            < c: forEach>标签具有以下一些属性：

            l          var：迭代参数的名称。在迭代体中可以使用的变量的名称，用来表示每一个迭代变量。类型为String。

            l          items：要进行迭代的集合。对于它所支持的类型将在下面进行讲解。

            l          varStatus：迭代变量的名称，用来表示迭代的状态，可以访问到迭代自身的信息。

            l          begin：如果指定了items，那么迭代就从items[begin]开始进行迭代；如果没有指定items，那么就从begin开始迭代。它的类型为整数。

            l          end：如果指定了items，那么就在items[ end]结束迭代；如果没有指定items，那么就在 end结束迭代。它的类型也为整数。

            l          step：迭代的步长。

            < c: forEach>标签的items属性支持Java平台所提供的所有标准集合类型。此外，您可以使用该操作来迭代数组（包括基本类型数组）中的元素。它所支持的集合类型以及迭代的元素如下所示：

            l          java.util.Collection：调用iterator()来获得的元素。

            l          java.util.Map：通过java.util.Map.Entry所获得的实例。

            l          java.util.Iterator：迭代器元素。

            l          java.util.Enumeration：枚举元素。

            l          Object实例数组：数组元素。

            l          基本类型值数组：经过包装的数组元素。

            l          用逗号定界的String：分割后的子字符串。

            l          javax.servlet.jsp.jstl.sql.Result：SQL查询所获得的行。

            不论是对整数还是对集合进行迭代，< c: forEach>的varStatus属性所起的作用相同。和var属性一样，varStatus用于创建限定了作用域的变量（改变量只在当前标签体内起作用）。不过，由varStatus属性命名的变量并不存储当前索引值或当前元素，而是赋予javax.servlet.jsp.jstl.core.LoopTagStatus类的实例。该类包含了一系列的特性，它们描述了迭代的当前状态，如下这些属性的含义如下所示：

            l          current：当前这次迭代的（集合中的）项。

            l          index：当前这次迭代从0开始的迭代索引。

            l          count：当前这次迭代从1开始的迭代计数。

            l          first：用来表明当前这轮迭代是否为第一次迭代，该属性为boolean类型。

            l          last：用来表明当前这轮迭代是否为最后一次迭代，该属性为boolean类型。

            l          begin：begin属性的值。

            l          end： end属性的值

            l          step：step属性的值

            下面就来看两个基本的例子，第一个例子是依次输出集合内的元素。
            < c: forEach var="item" items="${contents}" varStatus="status">
            $status.count：${item}
            < / c: forEach>
            下面的例子是一个固定次数的迭代，用来输出1到9的平方。
            < c: forEach var="x" begin="1" end="9" step="1">
            ${x*x}

        </c:forEach>

        下面写一下，我做的项目中用到的例子：

        分页：

        <table>
        <tr><th>名字</th><th>说明</th><th>图片预览</th></tr>
        <c:forEach items="${data}" var="item">
            <tr><td>${item.advertName}</td><td>${item.notes}</td><td><img src="${item.defPath}"/></td></tr>
        </c:forEach>
        </table>
        <ul>
        <li><a href='?nowPage=${nowPage-1}'>←上一页</a></li>
        <c:forEach varStatus="i" begin="1" end="${sumPage}">
            <c:choose>
                <c:when test="${nowPage==i.count}">
                    <li class='disabled'>${i.count}</li>
                </c:when>
                <c:otherwise>
                    <li  class='active'><a href='?nowPage=${i.count}'>${i.count}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li><a href='?nowPage=${nowPage+1}'>下一页→</a></li>
        </ul>

        首页展示图片的例子：

        迭代后台传过来的list, src的路径要写绝对路径，写成相对路径会报错，有时绝对路径用<c:ulr>来写

            <c:forEach items="${lists}" var="item">
                <img id="img${i}" height="250" width="500"  class="img" src='UploadImages/${item.advertPath}'/>
            </c:forEach>

            获得下标，其中size是后台传过来的list的长度，此处不能写成end="${list.size}"

            <c:forEach begin="1" end="${size}"  step="1" varStatus="i">
                <li> <a href="http://www.baidu.com/" class="showimg">${i.index}</a></li>
            </c:forEach>

</body>
</html>
--%>
