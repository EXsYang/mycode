import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//引入css 在这里引入css文件 才会生效
import '@/assets/css/global.css'

//引入ElementPlus 下面两行引入代码不能少
import ElementPlus from 'element-plus'
// 引入ElementPlus使用到的样式
import 'element-plus/dist/index.css'
//处理中文 引入element-plus国际化 将默认显示的英文变成中文
//import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
//使用下面这个处理国际化也一样
import zhCn from 'element-plus/es/locale/lang/zh-cn'

createApp(App).use(store).use(router).use(ElementPlus,{locale:zhCn,}).mount('#app')
