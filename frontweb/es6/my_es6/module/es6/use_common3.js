//导入默认导出模块/数据
//好处是 m 名称是可以自己指定的.
//以为m 名字, 程序员可以自己指定，因此我们就可以解决名称冲突问题
import m from "./common3";
//import {sum,sub} from "./common3"; //Cannot resolve symbol 'sub'  默认导出 不可以使用对象解构的方式导入！！！
//import m2 from "./common3";

// m.xxx
console.log(m.sub(2,3));
//console.log(sub(2,3));
//console.log(m2.sub(2,3));


//import a1 from "./common3"
//console.log(a1)





