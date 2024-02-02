
const cat = {
    name:"田园猫",
    age:2,
    cry(){
        console.log("小猫喵喵叫！");
    }
}
const dog = {
    name:"小黑狗",
    age:1,
    hi(){
        console.log("hi");
    }
}

// 1. 使用批量导出
//export {
//    cat,
//    dog
//}

// 2. 创建时导出

//export const dog = {
//    name:"小黑狗",
//    age:1,
//    hi(){
//        console.log("hi");
//    }
//}

// 3. 默认方式导出

export default{
    dog:{
        name:"小黑狗",
        age:1,
        hi(){
            console.log("hi");
        }
    }
}




