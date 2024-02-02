import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  }
  ,
  {
    path: '/home',
    //如果这里的name名称也为 'home' 会产生覆盖问题，应该为每个路由指定一个唯一的名称
    //导致访问http://localhost:10000/时没有路由到HomeView组件
    // ,而访问http://localhost:10000/home时路由到HomeView组件
    //name: 'home',
    name: 'home2',

    component: HomeView
  }
  ,
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
