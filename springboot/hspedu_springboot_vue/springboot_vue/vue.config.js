const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})
module.exports = {
    devServer: {
        port: 10000, // 启动端口
        //解读: 如果我们代理的地址 /api/save => 代理到'http://localhost:8080/ssm/save'
        proxy: { //设置代理，必须填
            '/api': { //设置拦截器 拦截器格式 斜杠+拦截器名字，名字可以 自己定
                target: 'http://localhost:9090', // 代理的目标地址,就是/api代替http://localhost:9090
                changeOrigin: true, //这里非常关键,是否设置同源，输入是的 这个是给浏览器看的
                // ,设置为true,就是允许访问代理目标target:'http://localhost:8080/ssm'
                // , 这时浏览器就允许跨域
                pathRewrite: { //路径重写
                    '/api': '' //选择忽略拦截器里面的单词
                }
            }
        }
    }
}