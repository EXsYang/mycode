const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})
//增加下面的代码，可以修改 vue项目默认启动的8080端口
//    - Local:   http://localhost:8080/
//    - Network: http://192.168.0.117:8080/

//增加下面的代码后
//    - Local:   http://localhost:10000/
//    - Network: http://192.168.0.117:10000/


module.exports = {
    devServer: {
        port: 10000, // 启动端口
        //解读: 如果我们代理的地址 /api/save => 代理到'http://localhost:8080/ssm/save'
        proxy: { //设置代理，必须填
            '/api': { //设置拦截器 拦截器格式 斜杠+拦截器名字，名字可以 自己定
                target: 'http://localhost:8080/ssm', // 代理的目标地址,就是/api代替http://localhost:10001/
                changeOrigin: true, //这里非常关键,是否设置同源，输入是的 这个是给浏览器看的
                // ,设置为true,就是允许访问代理目标target:'http://localhost:8080/ssm'
                // , 这是浏览器就允许跨域
                pathRewrite: { //路径重写
                    '/api': '' //选择忽略拦截器里面的单词
                }
            }
        }
    }
}