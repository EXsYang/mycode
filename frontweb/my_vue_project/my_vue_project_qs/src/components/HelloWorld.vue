<template>
  <div class="hello">
    <img src="@/assets/logo.png" alt="">
    <h1>{{ msg }}</h1>
    <h2>网站的地址</h2>
    <a href="http://www.baidu.com">点击进入百度</a>

  </div>
<!--  <a href=""></a>-->
</template>

<script>
export default { //默认导出组件
  name: 'HelloWorld',
  data () {//数据池
    return {
      msg: '网站首页-韩顺平教育'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
