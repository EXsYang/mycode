//定义对象,变量，常量, 函数
// 这种函数定义方式也可以
function sum(a, b) {
    return parseInt(a) + parseInt(b);
}

//function sub(a, b) {
//    return parseInt(a) - parseInt(b);
//}

const sub = function(a, b) {
    return parseInt(a) - parseInt(b);
}

let name = "hsp";

const PI = 3.14;

const monster = {
    name: "牛魔王",
    age: 300,
    skill() {
        console.log("蛮牛撞击")
    }
}

//es5 导出
/*
老韩解读
1. module.exports 导出模块
2. 把你需要导出的数据, 写入到 {}中即可
3. 可以全部导出，也可以部分导出
4. 理解：相当于把我们导出的数据，当做一个对象
5. 如果属性名和函数/变量/对象..名字相同，可以简写
6. 有些前端， 简写 module.exports={} 成 exports={}
 */

//module.exports = {
//    //sum: sum,
//    sub: sub,
//    myname: name,
//    PI:PI,
//    monster: monster
//}

// es5 导出 简写
//module.exports = {
//    //sum: sum,
//    sub,
//    name,
//    PI,
//    monster
//}

// es5 导出 再次简写
exports = { //注意 有等号=！！！
    sum,
    sub,
    name,
    PI,
    monster
}