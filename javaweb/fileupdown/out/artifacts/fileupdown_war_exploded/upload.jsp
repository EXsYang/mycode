<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FileUpload</title>
    <%--http://localhost:8080/fileupdown/upload.jsp--%>
    <!-- 指定了base标签 -->
    <base href="<%=request.getContextPath()+"/"%>>">
    <style type="text/css">
        input[type="submit"] {
            outline: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #31B0D5;
            border: none;
            width: 70px;
            height: 35px;
            font-size: 20px;
        }

        img {
            /*这里是 将图片设置为圆形*/
            border-radius: 50%;
        }

        form {
            position: relative;
            width: 200px;
            height: 200px;
        }

        input[name="pic"] {
            position: absolute;
            left: 0;
            top: 0;
            height: 200px;
            /*opacity: 0，表示完全透明 设置后 丑陋的"浏览..." 文字就看不到了！*/
            opacity: 0;

            /*cursor: pointer
            是指当鼠标移动到元素上时,鼠标指针的形状会发生变化,变成一只手,表示该元素可以被点击。*/
            cursor: pointer;
        }
        input[name="pic2"] {
            position: absolute;
            left: 0;
            top: 244px;
            height: 200px;
            opacity: 0;
            cursor: pointer;
        }
    </style>

    <script type="text/javascript">
        function prev(event) {
            //获取展示图片的区域
            var img = document.getElementById("prevView");
            //获取文件对象
            var file = event.files[0];
            //获取文件阅读器： Js的一个类，直接使用即可
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                //给img的src设置图片url
                img.setAttribute("src", this.result);
            }
        }
        function prev2(event) {
            //获取展示图片的区域
            var img2 = document.getElementById("prevView2");
            //获取文件对象
            var file2 = event.files[0];
            //获取文件阅读器： Js的一个类，直接使用即可
            var reader2 = new FileReader();
            reader2.readAsDataURL(file2);
            reader2.onload = function () {
                //给img的src设置图片url
                img2.setAttribute("src", this.result);
            }
        }
    </script>

</head>
<body>
<!-- 表单的enctype属性要设置为multipart/form-data
    enctype="multipart/form-data" 表示提交的数据是多个部分构造，有文件和文本
 -->

<form action="fileUploadServlet" method="post"  enctype="multipart/form-data">
    家居图1: <img src="2.jpg" alt="" width="200" height="200" id="prevView">
    家居图2: <img src="2.jpg" alt="" width="200" height="200" id="prevView2">
<%--    小伙伴愿意完成自己测试--%>
    <input type="file"/><br>

    <input type="file" name="pic" id="" value="pic_value" onchange="prev(this)"/>
    <input type="file" name="pic2" id="" value="" onchange="prev2(this)"/>

    家居名: <input type="text" name="name"><br/>
    家居名: <input type="text" name="name"><br/>

    <input type="submit" value="上传"/>
</form>
</body>
</html>
