<%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/9/26
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>rest </title>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        $(function (){  // 当页面加载完后就执行
            //alert("ok..")
            $("#delete_a").click(function (){ // 给删除超链接绑定一个点击事件
                //alert("点击了删除 a标签")
                //将该被点击的dom元素的href的属性值 赋给 form
                $("#delete_form").attr("action",this.href);
                // 注意 jQuery对象 的方法是val() dom元素的属性是value
                //$(":hidden").value("DELETE"); //书写有误

                $(":hidden").val("DELETE");
                $("#delete_form").submit();//这里就是提交删除请求了
                //这里必须返回 false,否则会提交两次

                return false; // 改变点击超链接的行为，不再提交
            })


        })


    </script>
</head>
<body>
<h3>Rest 风格的 crud 操作案例</h3>
<br><hr>
<h3>rest 风格的 url 查询书籍[get]</h3>
<a href="user/getBook/100">点击查询书籍</a>
<br><hr>
<h3>rest 风格的 url 添加书籍[post]</h3>
<%--服务器处理器Handler使用 @PostMapping 接收请求 这里使用 a标签超链接方式
 get方式发送请求 报错 HTTP Status 405 - Request method 'GET' not supported --%>
<%--<a href="user/addBook?bookName=java" id="request_get_a">点击添加书籍</a> <br/><br/>--%>
<%--使用rest方式 将get请求方式改成post方式 --%>
<form action="user/addBook" method="post">
    <input type="text" name="bookName">
    <input type="submit" value="添加书籍">
</form>

<br><hr>
<h3>rest 风格的 url, 删除一本书[DELETE]</h3>
<%--
1. 默认情况下 <a href="user/book/100"">删除指定 id 的书</a> 是get请求
2. 怎样将 get 请求转成 springmvc 可以识别到 delete请求 就要考虑HiddenHttpMethodFilter机制
 public static final String DEFAULT_METHOD_PARAM = "_method";
   ---------------------------------------------------
   private static final List<String> ALLOWED_METHODS =
			Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(),
					HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));
  ---------------------------------------------------
   if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
			String paramValue = request.getParameter(this.methodParam);
			if (StringUtils.hasLength(paramValue)) {
				String method = paramValue.toUpperCase(Locale.ENGLISH);
				if (ALLOWED_METHODS.contains(method)) {
					requestToUse = new HttpMethodRequestWrapper(request, method);
				}
			}
		}
3. 上面代码可以看到 HiddenHttpMethodFilter 过滤器可以对以Post方式提交的delete,put,patch进行转换,成springmvc
   识别的 RequestMethod.DELETE / RequestMethod.PUT /...
4. 我们需要将 get <a href="user/book/600">删除指定id的书</a> 以post方式提交给后端handler, 这样过滤器还会生效
5. 我们可以同jquery来处理-引入jquery
--%>
<a href="user/book/100" id="delete_a">删除指定 id 的书</a>
<form action="" method="post" id="delete_form">
    <input type="hidden" name="_method">
    <%--不用写下面这个提交按钮 直接在js代码中submit--%>
    <%--<input type="submit">--%>
</form>

<br><hr>
<h3>rest 风格的 url 修改书籍[put]~</h3>
<form action="user/book/444" method="post" id="put_form">
    <input type="hidden" name="_method" value="PUT">
    <%--不用写下面这个提交按钮 直接在js代码中submit--%>
    <input type="submit" value="put修改书籍">
</form>
</body>
</html>
