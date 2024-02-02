import Vue from 'vue'
import Router from 'vue-router'
//@ 符号代表项目的src目录
import HelloWorld from '@/components/HelloWorld'
import HelloMary from '@/components/HelloMary'
import Hsp from '@/components/Hsp'
//import Hsp1 from "../components/Hsp1";
import Hsp1 from "@/components/Hsp1"

Vue.use(Router)

//Router对象是哪来的呢？是在安装项目时提示我们要不要安装vue-router
// 引入的 位置在package.json 如果不安装vue-router 是没法用路由器的，
// 一定要装上
//"dependencies": {
//  "vue": "^2.5.2",
//    "vue-router": "^3.0.1" //这里的router
//}
export default new Router({
  routes: [ // 路由表 可以指定多个路由(就是访问路径)
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    { //配置的一组路由
      path: '/hello',
      name: 'HelloMary', //这里无所谓

      //这里不能写错 要和上面引入的 保持一致
      //import HelloMary from '@/components/HelloMary'
      component: HelloMary
    },

    { //配置的一组路由
      path: '/hsp',
      name: 'Hsp', //这里建议最好与组件的名称保持一致Hsp.vue 文件名保持一致

      //这里不能写错 要和上面引入的 保持一致
      //import Hsp from '@/components/Hsp'
      component: Hsp
    },
    { //配置的一组路由
      path: '/hsp1',
      name: 'Hsp1', //这里无所谓 最好与

      //这里不能写错 要和上面引入的 保持一致

      component: Hsp1
    }

  ]
})
