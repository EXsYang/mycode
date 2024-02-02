// 1. 请编写一个文件hspcommon.js , 该文件有对象cat (属性, name, age, cry()) dog(属性: name, age, hi())
// 1) 使用批量导出
// 2) 创建时, 直接导出
// 3) 默认方式导出

//默认导出
//这里注意 写法有一些变化, 当我们的两个对象，当做{} 的属性即可

export default {

    cat: {
        name: "小花猫",
        age: 100,
        cry() {
            console.log("小花猫喵喵叫...");
        }
    },

    dog: {
        name: "小花狗",
        age: 2,
        hi() {
            console.log("小花狗说 hi...");
        }
    }

}

