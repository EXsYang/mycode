let dog = {
    name:"小黑狗",
    age:1,
    //hi(){ 两个文件中有相同的对象dog 对象中又有相同的方法hi() 只会提示一个hi() 方法 就近原则
    //    console.log("a hi");
    //}
    // 两个文件中有相同的对象dog 对象中有两个不同的方法b里的dog.hi();a里的dog.say() 两个方法 都会提示出来
    // 此时的dog对象 会产生混淆 idea 不知道到底此处写的dog对象 是哪一个文件里的dog对象 区分不开
    say(){
        console.log("a hi");
    }
}

//console.log(dog.) // 没有加过任何条件时，
// 同时在a.js和b.js 中定义 dog对象 但是dog对象中有不同的两个方法hi() 和say()
// 此时的dog对象 会产生混淆 idea 不知道到底此处写的dog对象 是哪一个文件里的dog对象 区分不开



//console.log(dog.) // 这里的dog识别为a.js 中的dog对象
import {dog} from "./b";
console.log(dog)// 就近原则！！！ 这里的dog识别为b.js 中的dog对象 不能灵活的使用两个文件中各自的dog对象



import m from "./b.js";
console.log(m.dog);// 这里的dog识别为b.js 中的dog对象
console.log(dog.name);// 这里的dog识别为a.js 中的dog对象
