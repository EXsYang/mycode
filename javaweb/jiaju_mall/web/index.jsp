<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/7/10
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--直接请求转发到/customerFurnServlet 获取网站首页要显示的分页数据 默认pageNo=1--%>
<%--直接请求CustomerFurnServlet, 获取网站首页要显示的分页数据
 类似我们网站的入口页面
--%>
<%--<jsp:forward page="/customerFurnServlet?action=page&pageNo=1"></jsp:forward>--%>

<%--走这条线 会在pageByName中将url 设置到page对象中
第一次访问首页面 默认name是null 我们已经将null处理为空串"" 因此
返回数据库中所有的记录数    在页面中点击其他页会再次走action=pageByName
后端获得到name=null 会按照返回所有结果进行查询 并生成对应页pageNo的page对象
--%>
<jsp:forward page="/customerFurnServlet?action=pageByName&pageNo=1"></jsp:forward>
