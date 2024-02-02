<!--模板：表示页面视图 用户看到的页面 在这里写的 html-->
<template>

  <div>
    <h1>{{ msg }}</h1><br/>

    <!--    使用elementUI的组件-按钮-->
    <el-row>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>
    <el-input-number v-model="num" style="width: 300px" @change="handleChange" :min="1" :max="5" label="描述文字"></el-input-number>

    <!--添加斑马纹表格-->
    <!--stripe属性可以创建带斑马纹的表格。它接受一个Boolean，默认为false，设置为true即为启用。-->
    <!-- 注意： stripe="false" 不好使 直接写一个stripe 代表的是使用斑马纹
        要这样写:stripe="false" 或者直接拿掉这个属性
     -->
<!--    <el-table-->
<!--      :data="tableData"-->
<!--      stripe-->
<!--      style="width: 100%">-->
    <el-table
      :data="tableData"
      :stripe="false"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>

    <!--添加树形控件-->
    <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>


    <table>
      <tr>
        <td colspan="3">第1行第1列</td>
      </tr>
      <tr>
        <td rowspan="2">第2行第1列</td>
        <td>第2行第2列</td>
        <td>第2行第3列</td>
      </tr>
      <tr>
        <td>第3行第2列</td>
        <td>第3行第3列</td>
      </tr>
      <tr>
        <td rowspan="2">第4行第1列</td>
        <td>第4行第2列</td>
        <td>第4行第3列</td>
      </tr>
      <tr>
        <td>第5行第2列红烧肉
          <img src="@/assets/2.png" alt="红烧肉" width="100px">
        </td>
        <td>第5行第3列</td>
      </tr>
    </table>
  </div>

  <!--  Component template should contain exactly one root element. If you are using v-if on multiple elements, use v-else-if to chain them instead.-->
  <!--  组件模板应该只包含一个根元素。如果在多个元素上使用v-If，请使用v-else-If将它们链接起来。-->
  <!--  模板里只可以有一个根元素 因此需要使用div 把要写的内容包起来 可以存放多种元素
  但是不能直接在template标签体内 定义多个根元素 所有下面再写一个table元素就会报错
  因为上面已经有了一个div 根元素了！！！
  -->
  <!--  <table></table>-->

</template>

<!--定义数据和操作方法，组件核心。 默认导出 导出的就是一个组件-->
<script>
export default {
  // 这里的name属性"Hsp" 和该文件的文件名保持一致 生成文件时默认生成的就是name: "Hsp"
  name: "Hsp",
  data() {
    return {
      msg: "Welcome to hspedu",
      num: 1,
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],

      data: [{
        label: '家用电器',
        children: [{
          label: '电视',
          children: [{
            label: '全面屏电视'
          },{
            label: '教育电视'
          },{
            label: '动漫电视'
          }]
        }]
      }, {
        label: '电子数码',
        children: [{
          label: '电脑',
          children: [{
            label: '联想电脑'
          },{
            label: '苹果电脑'
          },{
            label: '雷神电脑'
          }]
        }]
      }]


    }
  },
  methods:{
    handleChange(value) {
      console.log(value);
    },
    handleNodeClick(data) {
      console.log(data);
    }
  }
}
</script>

<!--样式,css, 可以去修饰页面视图
前端的要求不高，但是也要会一点
margin: 0 auto; 0 表示上下的边距, auto表示左右居中
-->
<style scoped>

/*这里的优先级高于 App.vue 中的 #app{text-align: center;  居中} 所以这里变得不局中了 只需要在这里加一个
   margin: 0 auto; 0 表示上下边距 auto表示左右居中
*/
div {
  width: 900px;
  background-color: aliceblue;
  margin: 0 auto;
}

table, tr, td {
  border: 1px solid red;
  width: 600px;
  height: 60px;
  margin: 0 auto;
  border-collapse: collapse;
}

h1 {
  color: red;
}

</style>
