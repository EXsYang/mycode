<!--该文件是Vue的主题单页-->

<template>
  <div id="app">
<!--    <img src="./assets/logo.png">-->
<!--    将路由后的页面/视图引入到这里-->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*居中*/
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
