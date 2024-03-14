<template>
  <div>
    <!--引用组件 头部-->
    <Header/>
    <!--主体-->
    <div style="display: flex">
    <!--侧边栏-->
    <Aside/>
    <!--内容区域，这个部分是从HomeView.vue组件来的(通过路由机制)-->

    <router-view style="flex: 1"/>

    </div>


  </div>
</template>
<style>
</style>
<script>
// 这里是在讲解 前端技术栈-es6 模块化编程时讲过

// 先导入再导出，才可以在本类App.vue中进行使用 Header组件
// 在<script>标签内导入组件，在export default { components: {} }
// 的components中导出组件 就可以在本页面中使用该组件(此处是Header和Aside)
// @ 就是 src ，注意 @/ @后面有斜杠！！
// 导入

import Header from "@/components/Header";

import Aside from "@/components/Aside";

export default {
  name: "Layout",
  components: {
    Header,
    Aside
  }
}
</script>