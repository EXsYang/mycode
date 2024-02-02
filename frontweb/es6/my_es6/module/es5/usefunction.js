// 现在的问题是 在一个js文件中要使用另外一个js文件的数据 就需要使用到模块化编程

//es5 导入
//老韩解读
//1. 在es5中, 我们通过 require 就包 对应.js中的
//数据/对象，引入
//2. 我们使用的时候，通过m.属性 就可以使用
//3. 如果我们导入时，不需要所有的，可以导入部分数据
// 批量导入
const m = require("./function.js");
//let m = require("./function.js");
//const m = require("./function");  // 这里后缀默认是.js 可以省略不写
// 导入部分数据 // 相当于对象解构 只取出了一个数据 sub
const {sub} = require("./function.js");

//使用
//老韩说明: 只要这里idea可以识别变量/函数/对象, 说明OK
// 如果是正常的 可以使用的 ieda 鼠标放上去 是可以识别的弹出提示框显示具体的信息 且 在使用时显示a:, b:
// 通过{} 对象解构的方式 单独的获取sub这一个数据
console.log(m.sub("100","200"));
console.log(m.sum(10,90));
console.log(m.name)
console.log(m.PI);
console.log(sub(19,8));










