<template>
  <!--  <div class="home">-->
  <!--去掉class="home"-->
  <div>
    <!--    <img alt="Vue logo" src="../assets/logo.png">-->
    <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->

    <!--新增按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button>其它</el-button>
    </div>
    <!-- 搜索-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder=" 请 输 入 关 键 字 " style="width:30%"></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="list">查询</el-button>
    </div>

    <!--修改为furn相关的el-table   Table 表格
       tableData 指向了 furnList , 根据查看el-plus官方文档 后,这种对应关系是正确的
       furnList是一个List集合,通过注解@ResponseBody 返回的json格式 为 [{},{},{}] ,数组包含n个对象的形式
       正好可以对应上
       -->
    <!-- 去掉字段的 width, 让其自适应 -->
    <el-table :data="tableData" stripe style="width: 90%">
      <el-table-column
          prop="id"
          label="ID" sortable
      >
      </el-table-column>
      <el-table-column
          prop="name"
          label="家居名">
      </el-table-column>
      <el-table-column
          prop="maker"
          label="厂家">
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格">
      </el-table-column>
      <el-table-column
          prop="sales"
          label="销量">
      </el-table-column>
      <el-table-column
          prop="stock"
          label="库存">
      </el-table-column>

      <!--表格的固定列 fixed:固定的-->
      <el-table-column fixed="right" label="操作" width="100">
        <template #default="scope">
          <!--#default="scope",scope.row 插槽机制，获取到当前行的各个列的信息-->
          <!--          <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>-->
          <el-button @click="handleEdit2(scope.row)" type="text">编辑</el-button>
          <!--          <el-button @click="delayedRequest(scope.row)" type="text">编辑</el-button>-->
          <!-- 增加 popcomfirm 控件，确认删除 -->
          <el-popconfirm
              title="确定删除吗？" @confirm="handleDel(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="text">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--引入分页组件，可以根据自己的需要定制-->
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,2]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <!-- 添加家居的弹窗
    说明:
    1. el-dialog ：v-model="dialogVisible" 表示对话框, 和 dialogVisible 变量双向
    绑定,控制是否显示对话框
    2. el-form :model="form" 表示表单数据和 form 数据变量单向绑定，但是在内部el-form-item 和 el-input 使用 v-model 实现双向绑定
    3. el-input v-model="form.name" 表示表单的 input 控件，名字为 name 需要和
    后台 Javabean[Furn] 属性一致，这样到后端才会进行数据封装
    -->

    <el-dialog title="提示" v-model="dialogVisible" width="30%">
<!--      <el-form :model="form" rules="rules" ref="form" label-width="120px">-->
      <!--注意这里需要单向绑定数据池中的数据rules  :rules="rules"-->
      <el-form :model="form" :rules="rules" ref="form" label-width="120px">
        <!--prop="name" 这里的name 对应的是数据池中验证规则rules.name -->
        <el-form-item label="家居名" prop="name">
          <el-input v-model="form.name" style="width: 50%"></el-input>
          <!--显示返回的后端校验信息，使用Vue的插值表达式 这里就可以不要this.validMsg.name 直接写就行-->
          <!-- 插值表达式就是从model的data数据池来设置
               当我们的代码执行时，会到data{} 数据池中去匹配数据, 如果匹配上, 就进行替换
               , 如果没有匹配上, 就是输出空-->
          {{validMsg.name}}
        </el-form-item>
        <el-form-item label="厂商" prop="maker">
          <el-input v-model="form.maker" style="width: 50%"></el-input>
          {{validMsg.maker}}
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" style="width: 50%"></el-input>
          {{validMsg.price}}
        </el-form-item>
        <el-form-item label="销量" prop="sales">
          <el-input v-model="form.sales" style="width: 50%"></el-input>
          {{validMsg.sales}}
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input v-model="form.stock" style="width: 50%"></el-input>
          {{validMsg.stock}}
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>
<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

//引入request组件/对象
import request from "@/utils/request";


export default {
  name: 'HomeView',
  components: {
    //HelloWorld
  },
  data() { //数据
    // 注意 需要最好返回一个对象 使用{} 包起来
    return {
      validMsg: {}, // 关联后端校验错误信息
      currentPage: 1, //当前页 默认为1
      pageSize: 5, //每页显示几条记录 默认为5
      total: 10, // 总记录数，通过请求获取到表中的记录数
      form: {}, //表单数据
      dialogVisible: false, //控制添加对话框是否显示，默认为false不显示
      search: '', // 注意这里使用的数据的双向绑定 搜索框变化这里也会变化
      tableData: [],

      rules: { //提交表单的验证规则
        //trigger: 触发
        name: [{required: true, message: '请输入称家居名', trigger: 'blur'}],
        maker: [{required: true, message: '请输入称制造商', trigger: 'blur'}],
        price: [
          {required: true, message: '请输入价格', trigger: 'blur'},
          {
            pattern: /^(([1-9]\d*)|(0))(\.\d+)?$/,
            message: '请输入数字',
            trigger: 'blur'
          }],
        sales: [
          {required: true, message: '请输入销量', trigger: 'blur'},
          {
            pattern: /^(([1-9]\d*)|(0))$/,
            message: '请输入数字',
            trigger: 'blur'
          }],
        stock: [
          {required: true, message: '请输入库存', trigger: 'blur'}, {
            pattern: /^(([1-9]\d*)|(0))$/,
            message: '请输入数字',
            trigger: 'blur'
          }
        ]
      }

    }
  },
  created() { //在此方法中，调用list()，完成表格数据显示
    //vue生命周期的钩子方法 在这里发出ajax请求最为合适
    console.log("vue生命周期的钩子方法created()被调用")
    this.list();
  },
  methods: { //方法池
    handlePageSizeChange(pageSize) {
      //每页显示的数量pageSize被改变
      //根据Vue的机制 这里会被传进来
      //alert(pageSize)
      //this.pageSize 前面的this一定要加，表示当前数据池中的数据pageSize
      this.pageSize = pageSize;

      this.list(); //刷新页面

    },
    handleCurrentChange(pageNum) {
      //pageNum:当前页的页码
      //alert(pageNum)
      this.currentPage = pageNum;

      this.list(); //刷新页面
    },
    list() { //显示家具信息
      //下面的逻辑是不分页的 显示所有家居信息
      //request.get("/api/furns").then(
      //    //下面这个res是通过request.js中的拦截器处理后的res
      //    res => {
      //      console.log("list() res=", res)
      //      //将返回的数据和tableData绑定
      //      //this.tableData = res.data.data
      //      this.tableData = res.data
      //    }
      //)

      //下面是走分页的逻辑(不带条件的) 注意这里请求furnByPage时，携带的参数有哪些，注意下面这种写法
      //类似于 /furnByPage?pageNum=xx&pageSize=xx
      //其中params中的参数不是乱取名的，是根据后端的furnByPage()
      // ，形参参数 @RequestParam(defaultValue = "1") Integer pageNum 来确定
      //request.get("/api/furnByPage",{
      //  params: {
      //    pageNum: this.currentPage,
      //    pageSize: this.pageSize
      //  }
      //}).then(
      //    //下面这个res是通过request.js中的拦截器处理后的res
      //    res => {
      //      console.log("分页list() res=", res)
      //      //将返回的数据和tableData绑定
      //      //this.tableData = res.data.data
      //      console.log(res)
      //      //records:记录 绑定数据
      //      this.tableData = res.data.records
      //
      //      //通过请求获取到表中的记录数 并绑定到数据池中的数据 this.total
      //      this.total = res.data.total
      //
      //    }
      //)

      //下面是走分页的逻辑(带条件的) 注意这里请求furnByPage时，携带的参数有哪些，注意下面这种写法
      // request.get("/api/furnsBySearchPage", {
      request.get("/api/furnsBySearchPage2", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(
          //下面这个res是通过request.js中的拦截器处理后的res
          res => {
            console.log("带条件的分页list() res=", res)
            //将返回的数据和tableData绑定
            //this.tableData = res.data.data
            console.log(res)
            //records:记录 绑定数据
            this.tableData = res.data.records

            //通过请求获取到表中的记录数 并绑定到数据池中的数据 this.total
            this.total = res.data.total

          }
      )

    },
    save() { //真正的添加furn
      // 下面是新增数据/保存数据的代码
      //1. url: http://localhost:9090/save
      //2. this.form : 携带的数据
      //下面直接请求会遇到跨域问题,使用配置代理解决
      //已拦截跨源请求：同源策略禁止读取位于 http://localhost:9090/save 的远程资源。（原因：CORS 头缺少 'Access-Control-Allow-Origin'）。状态码：403。
      //已拦截跨源请求：同源策略禁止读取位于 http://localhost:9090/save 的远程资源。（原因：CORS 请求未能成功）。状态码：(null)。
      //在vue.config.js配置允许跨域, /api 被替换成http://localhost:9090

      /**
       *
       *  1) 一定要确定 request.post("/api/save") 被代理后的 url , 是项目后台服务对应提供的 API
       接口 url, 否则报 404 ，即后端得有这个映射路径才行，不能瞎写一个
       2) 当跨域执行时请求，浏览器还是提示 http://localhost:10000/api/save , 所以不要认为是
       api 没有替换你的配置. http://localhost:10000/api/save 的请求打到vue
       去掉http://localhost:10000,然后 /api 替换为http://localhost:9090 ,然后被代理为http://localhost:9090/save
       */

      //request.post("http://localhost:9090/save", this.form).then(

      //根据当前的form是否有id判断是添加还是修改操作
      if (!this.form.id) {
        //是添加
        //添加时和表单验证关联，如果前端表单验证没有通过就不提交
        this.$refs['form'].validate((valid) => {
          //alert(valid)

          //为了配合后端校验 先把前端校验valid设置为true让前端校验通过 之后再改回来
          // valid = true;

          if (valid){ //前端校验通过，向后端发送表单数据 进行保存操作
            request.post("/api/save", this.form).then(
                res => { //这里是箭头函数-前端技术栈讲过
                  if (res.code === "200"){
                    //res就是后端程序返回给前端的结果
                    console.log("save() 走的添加功能 res=", res)
                    //关闭添加家居对话框
                    this.dialogVisible = false;
                    //刷新家居列表
                    this.list();
                  // 因为错误代码可能会有多个，这里使用else if 而不是 else
                  }else if (res.code === "400"){ //后端校验失败

                    //取出返回来的错误信息
                    // 动态的生成validMsg对象的属性是可以的
                    this.validMsg.name = res.data.name;
                    this.validMsg.maker = res.data.maker;
                    this.validMsg.price = res.data.price;
                    this.validMsg.sales = res.data.sales;
                    this.validMsg.stock = res.data.stock;

                  }

                })
          }else {


            //需要提示-校验失败的消息框
            this.$message({
              type: "error",
              message: "前端校验失败，请重新提交表单数据"
            })
            //老韩说 这里返回false 才不会提交，但是自己测试不写也不会提交
            /*
            * 所以，如果您的表单提交是绑定在一个按钮点击事件（而不是表单的 submit 事件）上，并且使用了 AJAX 请求，则无需担心是否返回 false，因为默认的表单提交行为（页面刷新提交）本来就不会发生。
            * */
            return false;
          }
        })

      } else {
        //是修改
        request.put("/api/update", this.form).then(
            res => {
              console.log("save() 走的修改功能 res=", res)

              if (res.code === "200") { //修改成功
                //需要提示-成功的消息框
                this.$message({
                  type: "success",
                  message: "更新成功"
                })
              } else {
                //需要提示-失败的消息框
                this.$message({
                  type: "error",
                  message: "更新失败"
                  //message: res.msg
                })
              }

              //关闭添加/修改家居对话框
              this.dialogVisible = false;
              //刷新页面
              this.list();

            }
        )

      }

      //不可以直接拿到外面来执行，因为request.get() post/put/delete...都是 ajax异步请求
      ////关闭添加/修改家居对话框
      //this.dialogVisible = false;
      ////刷新页面
      //this.list();
    },
    handleEdit(row) {
      console.log("handleEdit() 传进来的row=", row)
      //handleEdit() 传进来的row= Proxy { <target>: {…}, <handler>: {…} } 是个代理对象
      //直接传进来的对象不能直接当作数据发送给后端，需要转成json对象，才可以发给后端

      //转为json字符串
      console.log("JSON.stringify(row)=", JSON.stringify(row));
      //JSON.stringify(row)= {"id":1,"name":"北欧风格小桌子","maker":"熊猫家居","price":180,"sales":666,"stock":7}
      //转为JSON对象
      console.log("JSON.parse(JSON.stringify(row))=", JSON.parse(JSON.stringify(row)));
      //JSON.parse(JSON.stringify(row))=Object { id: 1, name: "北欧风格小桌子", maker: "熊猫家居", price: 180, sales: 666, stock: 7 }

      //第1种方式 直接把当前行的数据绑定给form 数据可能不是最新的
      // 这里JSON.parse(JSON.stringify(row))是对当前行数据进行深拷贝
      // 这样点击的表格当前行的数据和弹出框的数据是独立的 修改显示出来的表单中的数据，再点取消 不会对原来的数据row有影响
      // 不会影响原来这一行的数据row, 把当前行的数据赋给form 进行绑定
      this.form = JSON.parse(JSON.stringify(row));

      //第2种方式 根据id去数据库拿数据(作业)

      //显示添加对话框(和修改是共用一个)
      this.dialogVisible = true;


    },
    handleEdit2(row) {
      console.log("handleEdit2() 传进来的row=", row)
      //handleEdit() 传进来的row= Proxy { <target>: {…}, <handler>: {…} } 是个代理对象
      //直接传进来的对象不能直接当作数据发送给后端，需要转成json对象，才可以发给后端

      //转为json字符串
      console.log("JSON.stringify(row)2=", JSON.stringify(row));
      //JSON.stringify(row)= {"id":1,"name":"北欧风格小桌子","maker":"熊猫家居","price":180,"sales":666,"stock":7}
      //转为JSON对象
      console.log("JSON.parse(JSON.stringify(row))2=", JSON.parse(JSON.stringify(row)));
      //JSON.parse(JSON.stringify(row))=Object { id: 1, name: "北欧风格小桌子", maker: "熊猫家居", price: 180, sales: 666, stock: 7 }

      //第1种方式 直接把当前行的数据绑定给form 数据可能不是最新的
      // 这里JSON.parse(JSON.stringify(row))是对当前行数据进行深拷贝
      // 这样点击的表格当前行的数据和弹出框的数据是独立的 修改显示出来的表单中的数据，再点取消 不会对原来的数据row有影响
      // 不会影响原来这一行的数据row, 把当前行的数据赋给form 进行绑定
      //this.form = JSON.parse(JSON.stringify(row));

      //第2种方式 根据id去数据库拿数据(作业)
      //发出ajax请求到后端获取furn
      request.get("/api/find/" + row.id).then(
          res => {
            if (res.code === "200") { //成功拿到数据
              //alert("在数据库拿到id=【"+row.id+"】的数据")
              //取出数据绑定到this.form
              this.form = res.data;
            } else {
              //没有拿到数据
              this.$message({
                type: "error",
                message: res.msg //给出错误信息提示
              })


              //alert("没有在数据库拿到id=【"+row.id+"】的数据")
            }

          }
      )

      //显示添加对话框(和修改是共用一个)
      this.dialogVisible = true;


    },
    //delayedRequest(row) {
    //  // 使用setTimeout来实现延迟
    //  setTimeout(() => {
    //    // 在setTimeout的回调函数中发送Ajax请求
    //    request.get("/api/find/" + row.id).then(
    //        res => {
    //          if (res.code === "200") {
    //            // 成功拿到数据
    //            this.form = res.data;
    //          } else {
    //            // 没有拿到数据
    //            alert("没有在数据库拿到id=【"+row.id+"】的数据")
    //          }
    //        }
    //    );
    //
    //    // 在Ajax请求后设置dialogVisible
    //    this.dialogVisible = true;
    //  }, 2000); // 设置延迟时间为2000毫秒（即2秒）
    //},
    handleDel(id) {
      request.delete("/api/del/" + id).then(res => {

        if (res.code === "200") {
          this.$message({
            type: "success",
            message: "删除成功"
          })
          this.list() // 刷新列表 放在这里执行更加合理
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
          this.list() // 刷新列表
        }
      })

      this.list() // 刷新列表 放在这里有时会没有立刻刷新，因为和ajax请求反回来之前，这里就被执行了

    },
    add() {//显示对话框
      this.dialogVisible = true; //分号可带可不带
      //当调用add()函数时,清空form表单 上一次添加的数据,就不再显示了
      //如果不清空根据 vue数据的双向绑定规则 会在数据池动态的
      // 创建并生成form.name/form.maker等属性 再次点开会显示这些数据
      this.form = {}
      //清空上一次的前端校验 验证信息
      //this.$refs['form'].resetFields;
      /**
       * 在Vue中，this.$refs 是用来访问组件实例内部的引用(refs)的。ref 是一个特殊的 attribute，当它出现在 HTML 元素或子组件上时，Vue 会将对应的 DOM 元素或组件实例赋值给 $refs 对象。

       在这段代码中，this.$refs['form'] 是指向模板中定义的 <el-form> 组件的一个引用。在 <el-form> 标签上定义了 ref="form"，Vue 通过这个 ref 名称把表单的 DOM 元素或组件实例关联到了 this.$refs 对象上。

       html
       Copy code
       <el-form :model="form" :rules="rules" ref="form" label-width="120px">
       <!-- 表单内容 -->
       </el-form>
       在JavaScript部分通过 this.$refs['form']，你可以直接访问到这个 <el-form> 组件实例，从而可以调用组件内部的方法或访问其属性。例如，在这段代码中用到了 resetFields() 方法，这个方法通常是Element UI <el-form> 组件提供的，用于重置表单域的值到初始状态，清除校验：

       javascript
       Copy code
       this.$refs['form'].resetFields();
       这里通过 this.$refs['form'] 访问到表单组件实例后，调用了其 resetFields() 方法来重置表单域。这样做常见于在打开表单对话框之前清除旧数据，确保用户看到的是未填写的表单。
       */
      //console.log("this.$refs['form']=",this.$refs['form'])
      //注意resetFields()是函数 下面这样写当第一次点击新增按钮触发add()方法时会报错
      /**
       * 当第一次点击新增按钮触发 add() 方法时，您遇到 this.$refs['form'].resetFields(); 报错的原因可以总结如下：
         异步渲染问题：在 Vue 中，DOM 的更新是异步的。当您的 add() 方法被触发时，如果 this.dialogVisible 刚被设置为 true 以显示对话框，内部的 <el-form> 可能还没有被实际渲染到 DOM 中。因此，this.$refs['form'] 在这个时点可能还是 undefined，导致无法调用 resetFields() 方法。
         第一次渲染行为：在第一次点击新增按钮时，Vue 需要将 <el-form> 和其他相关元素从未渲染的状态转变为渲染状态。这个过程需要时间，而在这个过程完成之前，<el-form> 的引用 this.$refs['form'] 还不存在。
         生命周期和渲染时机：由于 this.$refs['form'] 的存在依赖于 <el-form> 的渲染，因此在组件的生命周期中，特别是在对话框首次渲染时，this.$refs['form'] 可能尚未可用。
         为了解决这个问题，您可以在修改 dialogVisible 后，使用 this.$nextTick 确保 DOM 更新完成，然后再访问 this.$refs['form']。例如：
             javascript
             Copy code
             add() {
                this.dialogVisible = true;
                this.$nextTick(() => {
                  if (this.$refs['form']) {
                    this.$refs['form'].resetFields();
                  }
                });
              }
         这种方式确保了在尝试访问 <el-form> 的引用时，该元素已经被渲染到 DOM 中，从而避免了 undefined 的问题。
       */
      // this.$refs['form'].resetFields();


      this.dialogVisible = true;
      this.$nextTick(() => {
        if (this.$refs['form']) {
          this.$refs['form'].resetFields();
        }
      });

      //清空上一次的后端校验 验证信息
      this.validMsg = {};

    }
  }
}
</script>
