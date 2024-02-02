<template>
  <!--
  说明：
  1. App.vue 页面可以用于布局页面
  2. <router-view/> 就是路由指令
  3. 会把路由到的页面内容，展示/替换到 <router-view/> 位置
  4. Vue2 讲过路由 router
  5. 比如 http://localhost:10000/ 最后是一个斜杠 那么路由的 path 就是 /
     , 路由到的 "/" 应该显示那个组件的内容 就要去路由表(src/router/index.js-routes)
      里 找配置的 路由组件 component 是哪一个
 6. 比如 http://localhost:10000/about 最后是一个斜杠 那么路由的 path 就是 /about

  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/>
  -->

  <div>
    <!--引入 Header 页面/组件 头部 -->
    <Header/>

    <!--页面主体 侧边栏+主体内容-->
    <div style="display: flex">
      <!--侧边栏-->
      <Aside/>
      <!--内容区域 这个部分通过路由展示，默认路由到HomeView.vue
      http://localhost:10000/ => HomeView.vue
      http://localhost:10000/about => AboutView.vue
      -->
      <router-view style="flex: 1"/>

    </div>


  </div>


</template>

<script>

// @ 就是 src
// 导入
import Header from "@/components/Header";
import Aside from "@/components/Aside";

// 在前端技术栈 es6模块化编程 讲过 js的导入和导出
export default {
  // Layout: 布局
  name: "Layout",
  components:{
    //这里再导出 就可以在本文件的 <template/> 中当作一个组件使用了
    Header,
    Aside
  }
}

</script>

<style>

</style>
