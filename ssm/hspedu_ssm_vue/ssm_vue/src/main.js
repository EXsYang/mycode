import { createApp } from 'vue'
import App from './App.vue'

//说明 该项目ssm-vue和my-ssm/furn-ssm后端项目相关联

// 这里只要导入了 './App1.vue' 这个文件, 那么这个文件('./App1.vue')中的 css样式
// id选择器 #app 中css样式代码  就会影响 public/index.xml 的布局 所以这里将其注销了
//import App1 from './App1.vue'

import router from './router'
import store from './store'

// 在这里引入css文件 才会生效
import '@/assets/css/global.css'

// 引入 ElementPlus 后重新启动 项目 会报错 按照老师视频进行操作
// 引入时 鼠标悬浮在 'element-plus' 点击=> install element-plus 使用内网环境更快
import ElementPlus from 'element-plus'
// 引入ElementPlus使用到的样式
import 'element-plus/dist/index.css'
//app.use(ElementPlus)

//处理中文 引入element-plus国际化 将默认显示的英文变成中文
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
//使用下面这个处理国际化也一样
//import zhCn from 'element-plus/es/locale/lang/zh-cn'

createApp(App).use(store).use(router).use(ElementPlus,{locale:zhCn,}).mount('#app')

// createApp(App1) 中的 App1 就是导入的 './App1.vue'
//createApp(App1).use(store).use(router).mount('#app')
