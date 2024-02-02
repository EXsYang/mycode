//引入axios
import axios from "axios";

//通过axios创建对象-request对象 用于发送请求到后端
const request = axios.create({
    // 设置超时时间 5秒
    timeout: 5000
})

//request拦截器的处理
//1. 可以对请求做统一的处理
//2. 比如统一的加入token, Content-Type 等
//3. axios对象 可以在发送请求之前 做一些统一的处理
// interceptors : 就是拦截器
request.interceptors.request.use(config => {
        // vue 项目 的浏览器 发送数据到后端的 格式 默认不是 json格式
        // 在这里 设置一下 按照json格式 发送数据
        config.headers['Content-Type'] = 'application/json;charset=utf-8';
        return config;
    },error => {
        // 如果请求的过程中出现了错误,就返回 下面这个 reject 就拒绝了 不再继续往下走了
        return Promise.reject(error)
    }

)

//response拦截器
//可以在调用接口响应后，统一处理返回结果
request.interceptors.response.use(response => {
        //这里的response 就是后端返回的结果
        console.log("response拦截器拦截到的请求response-",response)
        //这里将 response的data属性赋给res
        //那么我们在请求的方法中,得到的结果就是response.data
        let res = response.data;
        //如果返回的是文件,就返回
        if (response.config.responseType === 'blob'){
            return res;
        }
        //如果是string,就转成json对象
        if (typeof res === 'string'){
            //如果res 不为null 或者 '' 或者 0 或者 undefined 就转换成JSON对象
            // 即 三元运算符 的 条件表达式 res 的位置 表达式返回true
            res = res ? JSON.parse(res) : res;
        }

        return res;
    },error => {
        console.log("err",error)
        // 失败的话就拒绝，不去处理了
        return Promise.reject(error);
    }

)

//导出request对象,这样在其他文件 导入即可使用
export default request