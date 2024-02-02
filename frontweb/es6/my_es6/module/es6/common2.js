//定义对象,变量，常量, 函数

//定义sum函数时，就直接导出
//老韩说明：如果在定义时，导出的数据， 在导入时，要保持名字一致
export const sum = function (a, b) {
    return parseInt(a) + parseInt(b);
}
// 这种定义函数的方式也可以
export function sub(a, b) {
    return parseInt(a) - parseInt(b);
}

//在函数定义时的导出方式 导出对象
export const utils = {
    f1(a,b){
        return a+b;
    },
    f2(a,b){
        return a-b;
    }
}


let name = "韩顺平教育";

const PI = 3.14;

const monster = {
    name: "牛魔王",
    age: 500,
    hi() {
        console.log("hi 你好 牛魔王");
    }
}
