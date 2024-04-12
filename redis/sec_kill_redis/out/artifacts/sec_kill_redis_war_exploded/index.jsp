<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <base href="<%=request.getContextPath() + "/"%>">
</head>
<body>
<h1>北京-成都 火车票 ! 秒杀！
</h1>
<form id="secKillform" action="secKillServlet" enctype="application/x-www-form-urlencoded">
  <input type="hidden" id="ticketNo" name="ticketNo" value="bj_cd">
  <input type="button" id="seckillBtn" name="seckillBtn" value="秒杀火车票【北京-成都】"/>
</form>
</body>
<script type="text/javascript" src="script/jquery/jquery-3.1.0.js"></script>
<script type="text/javascript">
  $(function () {
    $("#seckillBtn").click(function () {
      var url = $("#secKillform").attr("action");

      console.log("url-->",url) //url--> secKillServlet
      // ,完整的url是 http://localhost:8080/seckill/secKillServlet
      //1. 在 AJAX 发送请求时， $.post 会考虑当前页面地址栏中的 URL，或者更具体地说，它会考虑 <base> 标签（如果有的话）指定的基本 URL。如果没有 <base> 标签，它默认使用当前页面的路径作为基路径。
      //因此，你的 AJAX 请求的最终 URL 将由页面的 URL 或 <base> 标签（如果设置了的话）决定。这样确保了无论页面如何被服务器移动或嵌套，AJAX 请求都能正确指向目标 Servlet。这是 Web 开发中常用的技巧，用于确保资源路径的正确性，特别是在可能存在多个应用上下文或复杂目录结构的大型应用中。
      //2. 当你在 AJAX 的 $.post 方法中使用 URL，并且该 URL 以斜杠 (/) 开头，这种 URL 被称为绝对路径 URL。这意味着 URL 的解析将基于当前页面的域名和端口号，而不是页面的路径或 <base> 标签（如果存在）。

      console.log("serialize-->",$("#secKillform").serialize()) //serialize--> ticketNo=bj_cd

      $.post(url, $("#secKillform").serialize(), function (data) {
        if (data == "false") {
          alert("火车票 抢光了:)");
          $("#seckillBtn").attr("disabled", true);
        }
      });
    })
  })
</script>
</html>