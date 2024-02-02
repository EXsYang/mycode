<template>
  <!--该文件 是对项目基本知识的说明-->
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
  -->

  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <h1>app1.vue</h1>
  <router-view/>
</template>

<!--这里的样式 会影响到public/index.html 中的布局 因此注销掉下面这段#app样式代码
或者不在main.js中引入 App1.vue文件即可-->
<style>
/*具体来说是 下面这个#app 中的样式会影响到 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
