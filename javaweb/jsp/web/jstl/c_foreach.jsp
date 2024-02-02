<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hspedu.entity.Monster" %>
<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
  Filename: jstl_foreach
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>c:forEach 标签</title>
</head>
<body>
<h1>c:forEach 标签</h1>

<hr>
<%--< c: forEach>标签的语法定义如下所示。<br>

< c: forEach var="每个变量名字" items="要迭代的list" varStatus="每个对象的状态"<br>

begin="循环从哪儿开始" end="循环到哪儿结束" step="循环的步长"><br>

循环要输出的东西<br>

< c: forEach><br>

< c: forEach>标签具有以下一些属性：<br>

l var：迭代参数的名称。在迭代体中可以使用的变量的名称，用来表示每一个迭代变量。类型为String。<br>

l items：要进行迭代的集合。对于它所支持的类型将在下面进行讲解。<br>

l varStatus：迭代变量的名称，用来表示迭代的状态，可以访问到迭代自身的信息。<br>

l begin：如果指定了items，那么迭代就从items[begin]开始进行迭代；如果没有指定items，那么就从begin开始迭代。它的类型为整数。<br>

l end：如果指定了items，那么就在items[ end]结束迭代；如果没有指定items，那么就在 end结束迭代。它的类型也为整数。<br>

l step：迭代的步长。<br>--%>

<hr/>
<h1>第1种遍历方式从i到j</h1>
<ul>
    <%--
    1.遍历 1 到 5，
    2. 输出 begin 属性设置开始的索引 end 属性设置结束的索引
    3. var 属性表示循环的变量(也是当前正在遍历到的数据)
    4. 等价 for (int i = 1; i <= 5; i++) {}
    5. 在默认情况下, i 每次会递增1
    step="2"  i 每次会递增2
    begin="1" end="5" 等价于 i = 1; i <= 5 注意包含5 有等号 这里是<=5
    --%>
    <%--<c:forEach begin="1" end="5" step="2" var="i"> --%>
    <c:forEach begin="1" end="5" var="i">
        <li>排名!=${i}</li>
    </c:forEach>
    <hr>
    <br>
        <%--下方循环 循环了一次 i=0--%>
    <c:forEach begin="0" end="0" var="i">
        <li>begin="0" end="0"   i=${i}</li>
    </c:forEach>
        <hr>
        <br>
        <%--下方循环 循环了2次 i=0,1--%>
    <c:forEach begin="0" end="1" var="i">
        <li>begin="0" end="1"   i=${i}</li>
    </c:forEach>


</ul>
<%--c:forEach 注意事项：
   1.forEach 属性位置 数字写在双引号""中，
   2.<c:forEach begin="1" end="${end}" var="i"> 中的var="i"
     可以在el表达式中直接获取 ${i} 或 <c:if test="${sessionScope.page.pageNo == i}">

<%--显示所有页数的页码--%>
<%--<c:set scope="request" var="begin" value="1"></c:set>
<c:set scope="request" var="end" value="${sessionScope.page.pageTotalCount}"></c:set>
<c:forEach begin="1" end="${end}" var="i">
    <c:if test="${sessionScope.page.pageNo == i}">
        <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>
    </c:if>

    <c:if test="${sessionScope.page.pageNo != i}">
        &lt;%&ndash;<li><a href="manage/furnServlet?action=page&pageNo=${sessionScope.page.pageNo}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>&ndash;%&gt;
        <li><a href="manage/furnServlet?action=page&pageNo=${i}&pageSize=${sessionScope.page.pageSize}">${i}</a></li>
    </c:if>
</c:forEach>--%>

<hr/>
<h1>第2种遍历方式：遍历数组</h1>
<%
    request.setAttribute("sports", new String[]{"打篮球", "乒乓球"});
%>
<%--
    <c:forEach items="${ requestScope.sports }" var="item"/>
    1. items 遍历的集合/数组
    2. var 遍历到的数据 每循环一次就取一个数据到var="sport"变量中
    3. 等价 for (Object item: arr) {}
--%>
<c:forEach items="${requestScope.sports}" var="sport">
    取出的sports数组中的值sport= ${sport} <br/>
</c:forEach>


<%--<c:forEach items="${requestScope.sports}" var="sport">--%>
<%--    运动名称= ${sport}<br/>--%>
<%--</c:forEach>--%>
<hr/>
<h1>第3种遍历方式：遍历Map</h1>
<%
    Map<String, Object> map = new HashMap<>();
    map.put("key1", "北京");
    map.put("key2", "上海");
    map.put("key3", "天津");
    request.setAttribute("cities", map);
%>
<%--
    1. items 遍历的map集合
    2. var 遍历到的数据
    3. entry.key 取出key
    4. entry.value 取出值
--%>
<c:forEach items="${requestScope.cities}" var="city">
    key-${city.key} &nbsp;城市信息=${city.value} <br/>
</c:forEach>


<%--<c:forEach items="${requestScope.cities}" var="city">--%>
<%--    城市信息: ${city.key}--${city.value}<br/>--%>
<%--</c:forEach>--%>
<hr/>
<h1>第4种遍历方式：遍历List</h1>
<%
    List<Monster> monsters = new ArrayList<>();
    monsters.add(new Monster(100, "小妖怪", "巡山的"));
    monsters.add(new Monster(200, "大妖怪", "做饭的"));
    monsters.add(new Monster(300, "老妖怪", "打扫位置的"));
    request.setAttribute("monsters", monsters);
%>
<%--
    items 表示遍历的集合
    var 表示遍历到的数据
    begin 表示遍历的开始索引值 ,从0开始计算
    end 表示结束的索引值
    step 属性表示遍历的步长值
    varStatus 属性表示当前遍历到的数据的状态,可以得到step,begin,end等属性值
    //老师提示, 对于jstl标签，能看懂，会使用即可
--%>
foreach 中 有items var 属性 <br>
<%--其中monster 在下面的forEach中使用el表达式${monster}取出时有效
出了forEach 范围无效
--%>
<c:forEach items="${requestScope.monsters}" var="monster">
    妖怪信息!：${monster.id}-${monster.name}-${monster.skill}<br/>
</c:forEach> <br>
在forEach 外获取保存在var中的值monster :${monster}  获取不到<br>
<hr>
<br>
foreach 中 同时有items begin end var 属性 <br>
<%--<c:forEach items="${requestScope.monsters}" begin="0" end="1" var="i">--%>
<%--在含有items 的forEach 中 var属性中保存的值是items 集合中保存的单个的元素
相当于遍历 items[0] 到 items[1]
--%>
<c:forEach items="${requestScope.monsters}" begin="0" end="1" var="monster">
    <li>begin="0" end="1" 妖怪信息!：${monster.id}-${monster.name}-${monster.skill}<br/></li>
    <%--<li> i = ${i}</li>--%>

    <li> monster = ${monster}</li>
</c:forEach>
<%--<c:forEach items="${requestScope.monsters}" var="monster">--%>
<%--    妖怪的信息: ${monster.id}-${monster.name}-${monster.skill}<br/>--%>
<%--</c:forEach>--%>
</body>
</html>
