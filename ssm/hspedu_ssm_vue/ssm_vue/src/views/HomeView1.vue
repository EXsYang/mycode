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

    <!--在该文件中使用 HelloWorld 组件 -->
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
// 导入 HelloWorld 组件， 可以在 HomeView文件中使用
import HelloWorld from '@/components/HelloWorld.vue'

//导出组件 可以让其他文件使用导出的组件 components-HelloWorld
export default {
  //name: 'HomeView',
  name: 'Home',
  components: {
    // 这里使用的是 上面导入的组件
    //'HelloWorld':HelloWorld
    HelloWorld
  }
  //,
  // HelloWorld
}
</script>
