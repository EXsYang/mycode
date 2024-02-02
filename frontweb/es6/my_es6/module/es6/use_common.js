//es6 导入
/**
 * 老韩解读
 * 1. 我可以{} 来接收导出的数据
 * 2. 可以全部接收，也可以选择的接收
 * 3. 细节： 这时要求导入的名称和导出的名称一致
 */
    import {sum,sub,PI} from "./common.js";
    import {sum} from "./common.js"; // 也可以部分导出
    //import {sum,subx,PI} from "./common.js"; //Cannot resolve symbol 'subx'

    console.log(sum(2,3));
    console.log(sub(2,3));
    //console.log(subx(2,3));
    console.log(PI);

















