//
//可以导入模块/数据
import {sum}  from "./common2";
import {sub}  from "./common2";
//没有导出的数据, 是不可以导入
//import {sub} from "./common2.js"; //Element is not exported

import {utils} from "./common2";

console.log(sum(20,30));
console.log(sub(20,30));

console.log(utils.f1(2,3));
