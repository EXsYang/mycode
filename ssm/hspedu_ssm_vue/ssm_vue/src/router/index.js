import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

// 自己新增的
import Home from "@/views/Home";

//1. router/index.js 是用于配置路由
//2. path:'/' , 路径 当访问  http://localhost:10000/ 就路由到 HomeView 组件
//3. 就会把 HomeView 组件的内容，返回给 <router-view/>
//4. HomeView 对应的文件就是 '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    // 这里 路由到的组件 需要 导入 再使用
    // 使用ctrl+b 快捷键 可以直接进入到对应的组件文件页面
    component: HomeView
    //component: Home
  },
  // 同一个组件可以配置多个路由path,但是需要注意name不能相同，否则前一个会被覆盖
  {
    path: '/home',
    //name: 'home', //这里和前一个name相同了，发生覆盖，只有前端/home路径时才会被正确的路由到
    //如果这里的name名称也为 'home' 会产生覆盖问题，应该为每个路由指定一个唯一的名称
    //导致访问http://localhost:10000/时没有路由到HomeView组件
    // ,而访问http://localhost:10000/home时路由到HomeView组件
    name: 'home2',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
