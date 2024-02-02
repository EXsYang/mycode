//模块化编程

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

//es6的导出模块/数据
/**
 * 老师解读
 * 1. export 就是导出模块/数据
 * 2. 可以全部导出，也可以部分导出
 */
    export { // 注意es6这里没有等号=
        sum,
        sub,
        PI
    }
