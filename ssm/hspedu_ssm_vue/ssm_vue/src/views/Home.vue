<template>
  <!--该文件 是对项目基本知识的说明-->

  <div class="home">

    <img alt="Vue logo" src="../assets/logo.png">

    <!--
    1. 在 HomeView.vue 文件中，引用/使用 HelloWorld 组件
    2. 因此这里就会显示 HelloWorld 组件的内容
    3. HelloWorld 组件在 '@/components/HelloWorld.vue'
    4. @ 就是 src的一种写法
    5. msg="Welcome to Your Vue.js App" 表示给 HelloWorld 组件设置的一个值 该值msg会被
       HelloWorld.vue 文件中的 <h1>{{ msg }}</h1> 使用到
    -->

    <!--下面导出正确 在这里才可以正常显示-->
    <!--在该文件中使用 HelloWorld 组件 -->
    <HelloWorld msg="Welcome to Home Vue.js App"/>

    <!--这里使用的是 下面按照格式导出的组件 HelloWorld1 -->
    <HelloWorld1 msg="Welcome to ‘Home -- HelloWorld1’ Vue.js App"/>
  </div>
</template>



<script>

/*
* js 模块化编程 只是导入的话 只可以在该 <script> 中 / .js 文件中使用 HelloWorld
* 在 上面的   <template><div> 中 因为是HTML代码 所以还是不可用的
* 需要当作一个组件 来导出 ，只有把 HelloWorld 但做一个组件导出后 才可以 在本文件 Home.vue 文件
* 中 使用 <HelloWorld/> ,   可以参考 vue 的组件化编程 全局组件/局部组件 组件的使用形式/方法
* */
import HelloWorld from '@/components/HelloWorld.vue'
import HelloWorld1 from '@/components/HelloWorld.vue'

//导出组件 可以让其他文件使用导出的组件 components-HelloWorld
export default {
  //name: 'HomeView',
  name: 'Home',
  components: {
    // 这里使用的是 上面导入的组件. 先导入再导出 就可以在该文件中使用 这个组件了
    //'HelloWorld':HelloWorld
    HelloWorld  // 这里使用的是上面的导入语句中的 HelloWorld
    //HelloWorld1  // 这里使用的是上面的导入语句中的 HelloWorld1
  }

  // 测试 只写 下面的 HelloWorld 不行 会显示不出来 需要按照格式导出组件 components: {HelloWorld}
  //HelloWorld
}
</script>

<style scoped>

</style>