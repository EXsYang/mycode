<%@ page import="com.hspedu.entity.Book" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yangda
  Date: 2023/6/17
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL 表达式输出</title>
</head>

<body>
<h1>el 表达式输出数据演示</h1>
<%
    Book book = new Book();
    book.setName("昆虫总动员");
    book.setWriter(new String[]{"jack", "tom"});

    ArrayList<String> readers = new ArrayList<>();
    readers.add("老韩");
    readers.add("小杨");
    book.setReader(readers);

    HashMap<String, String> topics = new HashMap<>();
    topics.put("topics1", "这是最好看的动漫");
    topics.put("topics2", "不错的电影!");
    book.setTopics(topics);

    //把book 放入到request域对象
    request.setAttribute("bookkey", book);
%>

book对象: ${bookkey} <br/>
book.name= ${bookkey.name}<br/>
book.writer= ${bookkey.writer}<br/>
book.writer[0]= ${bookkey.writer[0]}<br/>

book.readers=${bookkey.reader} <br/>
book.readers第2个=${bookkey.reader.get(1)} <br/>
book.readers第2个= ${bookkey.reader[1]} <br/>

book.topics= ${bookkey.topics}<br/>
book.topics第一个评论= ${bookkey.topics.get("topics2")}<br/>
book.topics第一个评论= ${bookkey.topics["topics2"]}<br/>


</body>
</html>
