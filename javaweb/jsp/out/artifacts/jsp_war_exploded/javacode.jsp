<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hspedu.entity.Monster" %><%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/10
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>演示代码脚本</title>

</head>
<body>
<h1>演示代码脚本</h1>
<%
    ArrayList<Monster> monsters = new ArrayList<>();
   /* arraylist.add（int index，E element）
    index（可选参数）- 表示元素所插入处的索引值
    element - 要插入的元素*/

    monsters.add(0,new Monster(1,"牛魔王a","芭蕉扇"));
    monsters.add(1,new Monster(2,"蜘蛛精a","吐口水"));
    //monsters.add(new Monster(1,"牛魔王b","芭蕉扇"));
    //monsters.add(new Monster(2,"蜘蛛精b","吐口水"));
%>
<table bgcolor="#4169e1" border="1px" width="300px">
<tr>
    <th>id</th>
    <th>名字</th>
    <th>技能</th>
</tr>
<%
    for (int i = 0; i < monsters.size(); i++) {
%>
<tr>
    <td><%=monsters.get(i).getId()%></td>
    <td><%=monsters.get(i).getName()%></td>
    <td><%=monsters.get(i).getSkill()%></td>
</tr>
<%
    }
%>
</table>


</body>
</html>
