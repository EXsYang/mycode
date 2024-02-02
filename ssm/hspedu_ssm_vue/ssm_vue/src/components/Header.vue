<template>

  <!--
  1. 这是页面的头部 Header
  2. 分成三个部分 div
  3. flex: 1 弹性布局 , 弹性布局说明=>  https://www.cnblogs.com/LangZ-/p/12703858.html
  -->
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bold; color:dodgerblue">后台管理</div>
    <div style="flex: 1"></div>

    <!--下拉框-->
    <div style="width: 100px">
      <el-dropdown>
    <span class="el-dropdown-link">
      tom
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>

</style>