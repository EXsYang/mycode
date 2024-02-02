//测试访问范围
//测试在这个文件中
//是否可以引用到
//直接在项目下创建的html文件 id="app" 的div元素


window.onload = function () {
    //1. 获取 dom对象
    // 可以自动提示出"app123" 即可以访问到 app1.html 中 id="app123" 的 div元素
    var app123 = document.getElementById("app123");

}