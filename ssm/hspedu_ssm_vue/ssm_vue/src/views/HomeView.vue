<template>
  <div>


    <!--
    1. 在 HomeView.vue 文件中，引用/使用 HelloWorld 组件
    2. 因此这里就会显示 HelloWorld 组件的内容
    3. HelloWorld 组件在 '@/components/HelloWorld.vue'
    4. @ 就是 src的一种写法
    5. msg="Welcome to Your Vue.js App" 表示给 HelloWorld 组件设置的一个值 该值msg会被
       HelloWorld.vue 文件中的 <h1>{{ msg }}</h1> 使用到

       在该文件中使用 HelloWorld 组件
       <HelloWorld msg="Welcome to Your Vue.js App"/>
    -->

    <!--增加按钮和搜索输入框-->
    <div style="margin: 10px 5px">
      <!--      <el-button type="primary">新增</el-button>-->


      <!--
      1. @click="add" 点击新增按钮,就会触发 add() 函数

      -->
      <el-button type="primary" @click="add">新增</el-button>

      <!-- 添加家居的弹窗
          说明:
          1. el-dialog ：v-model="dialogVisible" 表示对话框, 和 dialogVisible 变量双向
             绑定,控制是否显示对话框
          2. el-form :model="form" 表示表单 ,数据和 form 数据变量双向绑定
          3. el-input v-model="form.name" 表示表单的 input 控件，名字为 name 需要和
             后台 Javabean[Furn] 属性一致
          4. 在前端中,对象的属性是可以动态生成的,直接使用form.name 就可以生成form对象的name属性

          -->
      <el-dialog title="提示" v-model="dialogVisible" width="30%">

        <!--        <el-form v-bind:model="form" label-width="120px">-->
        <!-- :model 这里是单项绑定-->
        <el-form :model="form" :rules="rules" ref="form" label-width="120px">
          <el-form-item prop="name" label="家居名">
            <!--v-model="form.name" 直接动态的添加即可 只需要定义一个 空着的form
            v-model="form.name" 这里是双向绑定 这里的数据修改 数据池form中的数据也会动态修改

            -->
            <el-input v-model="form.name" style="width: 50%"></el-input>
            <!--使用插值表达式 取出后端校验的错误信息 这里不用this.serverValidErrors.name
             ,因为不是在方法池使用, 如果数据池有数据 就展示出来，如果没有数据 就显示为空串,
             因为vue底层处理过，不做展示，并不会影响前端布局-->
            {{serverValidErrors.name}}
          </el-form-item>
          <el-form-item prop="maker" label="厂商">
            <el-input v-model="form.maker" style="width: 50%"></el-input>
            {{serverValidErrors.maker}}
          </el-form-item>
          <el-form-item prop="price" label="价格">
            <el-input v-model="form.price" style="width: 50%"></el-input>
            {{serverValidErrors.price}}
          </el-form-item>
          <el-form-item prop="sales" label="销量">
            <el-input v-model="form.sales" style="width: 50%"></el-input>
            {{serverValidErrors.sales}}
          </el-form-item>
          <el-form-item prop="stock" label="库存">
            <el-input v-model="form.stock" style="width: 50%"></el-input>
            {{serverValidErrors.stock}}
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <!--dialogVisible = false 按理说应该单独写一个方法
            但是 这里只有一句话 可以在这里直接写
            -->
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </template>
      </el-dialog>


      <el-button>其他</el-button>
    </div>

    <!--输入框-->
    <div>
      <!--search:搜索-->
      <el-input v-model="search" style="width: 30%" placeholder="请输入关键字"/>
      <el-button type="primary" style="margin-left: 10px" @click="list">检索</el-button>
    </div>


    <!--    <el-table :data="tableData" stripe style="width: 90%">-->
    <!--加入 sortable 属性，使表格支持日期排序-->
    <!--      <el-table-column sortable prop="date" label="日期"/>-->
    <!--      <el-table-column prop="name" label="名字"/>-->
    <!--      <el-table-column prop="address" label="地址"/>-->

    <!--修改为furn相关的el-table   Table 表格
    tableData 指向了 furnList , 根据查看el-plus官方文档 后,这种对应关系是正确的
    furnList是一个List集合,通过注解@ResponseBody 返回的json格式 为 [{},{},{}] ,数组包含n个对象的形式
    正好可以对应上
    -->
    <el-table :data="tableData" stripe style="width: 95%">
      <!--猜测 这里的属性 prop="id" 可能也是双向绑定 进行测试/查找资料-->
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
          label="制造商">
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格">
      </el-table-column>
      <el-table-column
          prop="销量"
          label="四级次数">
      </el-table-column>
      <el-table-column
          prop="stock"
          label="库存">
      </el-table-column>
      <!--操作菜单-->
      <el-table-column fixed="right" label="操作" width="120">

        <!--
        #: 井号是对v-solt的缩写
        default:在这里是内容的意思
        涉及到vue的插槽机制 属于前端的一点知识
        1. 这里通过 handleEdit(scope.row)
        2. 可以将当前行数据传递给handleEdit
        -->
        <template #default="scope">

          <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
          <!--          <el-button type="text">删除</el-button>-->
          <!--
          el-button除了默认的大小，按钮组件还提供了几种额外的尺寸可供选择，以便适配不同的场景。
          使用 size 属性额外配置尺寸，可使用 large和small两种值。
          -->

          <!--
          el-popconfirm @confirm 解读
          点击删除后弹出的对话框
          点击'确定'触发 @confirm 里的handleDel()
          点击'取消' 不会调用函数 handleDel()
          -->
          <el-popconfirm title="你确定删除吗?" @confirm="handleDel(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>


    <!--添加分页导航
    margin: 10px 0  表示 上下边距为10px 左右边距为0 ,只填一个值代表的时左右边距
    -->
    <div style="margin: 10px 0">
      <!--    <div style="margin: 10px">-->
      <el-pagination
          @size-change="handlePageSizeChange" @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[2,5,10,15]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>


<script>
// @ is an alias to /src
// 导入 HelloWorld 组件， 可以在 HomeView文件中使用

//导入 request对象
import request from "@/utils/request";

//引入ElMessage
import {ElMessage} from 'element-plus'

//导出组件 可以让其他文件使用导出的组件 components-HelloWorld
export default {
  //name: 'HomeView',
  name: 'Home',
  components: {},
  /*Data property should be a function  否则报错 => dataOptions.call is not a function*/
  //data:  [
  //  {
  //    date: '2016-05-03',
  //    name: 'Tom',
  //    address: 'No. 189, Grove St, Los Angeles',
  //  }
  //],
  //注意 不可以在下面这里写数据 而是要将数据 放到 data() 函数中
  //search: "",
  created() {
    //vue生命周期的钩子方法 在这里发出ajax请求最为合适
    this.list()//调用list

  },
  data() {
    // 注意 需要最好返回一个对象 使用{} 包起来
    return {
      //存放后端校验错误信息
      serverValidErrors:{},
      //新增分页导航控件需要的数据
      currentPage: 1,//当前的页数
      pageSize: 5,//每页的记录数
      total: 10,//总共有多少条记录

      tableData: [],
      search: '',//检索条件，可以再次进行分页时，保留检索条件，因为上面使用了 v-model="search" 双向绑定
      dialogVisible: false,
      form: {}, //定义一个空表单
      //定义添加表单的校验规则
      rules: {
        name: [
          // 这里我们可以写多个针对name属性的校验规则 trigger:触发
          {required: true, message: "请输入家居名", trigger: "blur"}
        ],
        maker: [
          // 这里我们可以写多个针对maker属性的校验规则
          {required: true, message: "请输入制造商名", trigger: "blur"}
        ],
        price: [
          // 这里我们可以写多个针对price属性的校验规则
          {required: true, message: "请输入价格", trigger: "blur"},
          // 使用正则表达式对输入的数据进行校验
          {pattern: /^([1-9]\d*|0)(\.\d+)?$/, message: "请输入数字", trigger: "blur"}
        ],
        sales: [
          // 这里我们可以写多个针对sales属性的校验规则
          {required: true, message: "请输入销量", trigger: "blur"},
          {pattern: /^([1-9]\d*|0)$/, message: "请输入正整数或0", trigger: "blur"}
        ],
        stock: [
          // 这里我们可以写多个针对stock属性的校验规则
          {required: true, message: "请输入库存", trigger: "blur"},
          {pattern: /^([1-9]\d*|0)$/, message: "请输入正整数或0", trigger: "blur"}
        ],

      }
    }
  },
  methods: { //方法池
    add() {
      //显示对话框
      this.dialogVisible = true; //分号可带可不带
      //当调用add()函数时,清空form表单 上一次添加的数据,就不再显示了
      //如果不清空根据 vue数据的双向绑定规则 会在数据池动态的
      // 创建并生成form.name/form.maker等属性 再次点开会显示这些数据
      this.form = {}

      //清空上次前端校验的信息 $refs是vue里的
      // !== 不全等 类型和值都不等
      if (this.$refs.form !== undefined){
        console.log("this.$refs.form !== undefined 清空上次前端校验的信息")
        this.$refs['form'].resetFields()
      }

      //清空上次后端校验的信息
      this.serverValidErrors = {}

    },
    save() {//将填写的表单数据,发送给后端

      // 添加和更新走的是同一个方法
      // 点击form表单的确定按钮后触发 然后根据 数据池form 中是否有数据判断 要走的逻辑是更新还是新增
      if (this.form.id) {
        console.log("save() 走的是更新数据 this.form.id= ", this.form.id)

        // 如果form中有form.id 的数据 , 就说明是更新操作
        // 这里不可以根据form表单是否为空来判断是更新还是新增 因为 如果是新增操作
        // ,在新增时填写了一些需要新增的furn的数据后,该from表单就不为空了
        // 但是没有办法指定填写form.id 因此可以根据 form.id是否存在 来判断是否为更新操作
        // 如果是更新操作,因为在函数handleEdit(row)中 设置了this.form = row; 又因为 在list()函数中
        // 返回的furnList 中有id属性 因此, 如果是更新操作 this.form.id 是有值的,进行条件判断是为true
        // 如果不存在form.id 即为 undefined , 条件判断为false

        // 更新操作
        // 本质是发出ajax请求-异步处理
        request.put("/api/update", this.form).then(res => {
          console.log("save() update res= ", res);


          //if (res.code === '200' ) {
          // 注意 这里的 res.code Msg 中的属性是 int 返回的 是int 类型的,到了前端 转换为了json对象返回的
          // 此时 res.code 的类型为 number！ 所以不可以写 res.code === '200' 而是写 res.code === 200
          console.log("typeof res.code= ", typeof res.code)
          //typeof res.code=  number

          if (res.code === 200) {
            //老韩写的这种 Message 消息反馈组件 , 不需要写导入语句，就可以直接使用,更加简洁
            this.$message({
              type: "success",
              message: res.msg
            })

            //下面这种 Message 消息反馈组件 是el-plus 官方文档中的
            // 要使用 ElMessage 需要显示的引入el-plus组件 写上 import { ElMessage } from 'element-plus'
            // 和上面简写 为this.$message 效果相同！
            //ElMessage({
            //  message: '更新成功',
            //  //message: res.msg,
            //  type: 'success',
            //})
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

          // 更新之后,关闭 弹出的form表单输入框
          this.dialogVisible = false;

          // 刷新 显示数据
          this.list();
        })


      } else {
        console.log("save() 走的是新增数据 this.form.id= ", this.form.id)

        //console.log("save() this.form.id= " , this.form.id)

        //var sss = {}
        //if (sss){
        //  console.log("即使一个对象sss定义为=> {} 也表示true") // 这句话是正确的
        //}


        // 下面是新增数据/保存数据的代码
        //1. url: http://localhost:8080/ssm/save
        //2. this.form : 携带的数据
        //request.post("http://localhost:8080/ssm/save",this.form).then(res => {

        // 测试 如果携带的是一个json字符串 判断后端能否处理? 结果:可以处理
        //var strJsonForm = JSON.stringify(this.form);
        //console.log("strJsonForm= ",strJsonForm)
        // 下面这种 传递的是json字符串 后端也可以正常完成添加操作
        //request.post("/api/save", strJsonForm).then(res => {


        //增加表单前端验证 如果通过才会提交表单 否则返回false 不提交表单
        this.$refs['form'].validate((valid)=>{

          //这里我们先让前端校验放行 用来测试后端校验是否生效 后面再改回来
          //valid = true;

          //valid就是表单校验后返回的结果
          if (valid){//如果校验通过

            request.post("/api/save", this.form).then(res => {
              console.log("save() res= ", res)
              if (res.code === 200){
                this.dialogVisible = false
                //新增数据后 再次调用list()函数 刷新显示数据
                //list(); //直接写 list(), 会报错 ERROR list is not defined
                this.list();// 必须加this.
              }else if (res.code === 400){ //后端校验失败
                //取出校验失败的信息赋给serverValidErrors
                this.serverValidErrors.name = res.extend.errorMsg.name;
                this.serverValidErrors.maker = res.extend.errorMsg.maker;
                this.serverValidErrors.price = res.extend.errorMsg.price;
                this.serverValidErrors.sales = res.extend.errorMsg.sales;
                this.serverValidErrors.stock = res.extend.errorMsg.stock;

              }


            })

          }else { //校验没有通过

            this.$message({
              type: "error",
              message: "前端校验失败,不提交"
            })

            return false; //放弃提交
          }
          //return false; //放弃提交

        })



      }
      //因为 上面的 if(this.form.id){}else{} 中都有
      // this.dialogVisible = false; this.list();
      // 而把 this.dialogVisible = false; this.list(); 提出来放到 if-else 的后面 可以吗?
      // 不可以!! 直接在下面写,会出问题 看起来好像  this.list(); 失效了一样
      // 因为 ajax 是异步请求, 在回调函数 res=>{} 执行完之前, 就直接走到了在下面写的 this.dialogVisible = false; this.list();
      // 因为回调函数还没执行完就走到了 this.list(); 返回的是旧数据
      // 所以看起来好像  this.list(); 失效了一样
      // 所以, 应该在回调执行完之后 再加入 this.list(); 这样拿到的才是最新数据!

      // 下面
      //this.dialogVisible = false
      //this.list();
    },
    // list() 方法: 向后端发出ajax请求,返回所有的家居信息/furn数据
    // list()方法，应该是自动调用的, 放在created()方法中调用最合适
    list() {
      ////在request.js 文件中通过 response拦截器处理的res 传给下面的 res
      //request.get("/api/furns").then(res => {
      //  console.log("list() res=", res)
      //
      //  //为什么是 res.data.extend.furnList; 可以根据console输出的数据结构分析出来
      //  // 调用时 这个this不能省略
      //  //this.tableData = res.data.extend.furnList;
      //
      //  // 在request.js 文件中 对返回的 response 结果
      //  // ,进行了统一拦截处理 let res = response.data;
      //  // 使res指向response.data;
      //  // 所以下面 可以简写成 res.extend.furnList;
      //  this.tableData = res.extend.furnList;
      //})

      //走请求分页的接口 而不请求所有数据
      request.get("/api/furnsByConditionPage", {
        //params属性可以携带请求参数,在该对象内部指定
        //params是axios内建对象
        params: {
          //这里的json对象属性pageNum ,
          // 将来对应后端目标方法形参的 Integer pageNum ,要对应好
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => { //处理返回的分页信息 带条件的
        console.log("list() furnsByConditionPage res= ", res);

        this.tableData = res.extend.pageInfo.list
        this.total = res.extend.pageInfo.total


      })


    },
    handleEdit(row) {
      // 该方法功能是 点击编辑, 回显数据

      //console.log("row= ",row)

      //console.log("row.id= ",row.id)
      //console.log("row.name= ",row.name)
      //console.log("row.maker= ",row.maker)
      //console.log("row.target= ",row.target)
      //console.log("row.handler= ",row.handler)

      //输出结果 如下,结合 转为json字符串之后的 strJson_row 输出结果 可见
      //row 对象的结构 , 可以使用 row直接 点 下面对应的属性 id/name/maker...
      //row.id=  1
      //row.name=  hh桌子1
      //row.maker=  tt家居1
      //row.target=  undefined
      //row.handler=  undefined

      //将行数据row转成json字符串输出
      //console.log("strJson_row= ",JSON.stringify(row))
      //strJson_row=  {"id":1,"name":"hh桌子1","maker":"tt家居1","price":180,"sales":666,"stock":7,"imgPath":"assets/images/product-image/1.jpg"}

      // 点击编辑, 回显数据 将当前行的家居信息绑定到弹出对话框的form
      // 将被点击的数据值 赋给this.form, 使其可以在表单中显示出改行的信息
      // 这里是直接从对应的行上获取的数据，并将其显示到form表单的输入框中
      // 方式一: 在前端获取数据 并填入
      // 因为返回的数据 row 不是JSON格式 所以需要先转为JSON对象 然后绑定到 this.form
      // 深拷贝是指拷贝的对象是独立的，不影响原来的值，就称之为深拷贝 下面两种都是深拷贝
      // JSON.stringify(row) : 将row转为json字符串
      // JSON.parse(JSON.stringify(row)) : 将json字符串转成json对象
      this.form = JSON.parse(JSON.stringify(row));

      // 经过测试 不转为json格式直接绑定 也可以,但还是转一下更保险
      // 如果使用的是下面这个 修改form弹出框中的数据 斑马纹表格中的数据也会动态的变化
      // 原因是双向绑定还是单向绑定? row数据池中的数据变化了 动态的 其他也变化
      //this.form = row;

      // 如果根据这一行的id 去数据库中获取数据,并填入到 form表单中显示出来 如何做
      // 后端提供一个根据id 返回furn数据的接口 在这里发出ajax 根据返回来的数据 res 将其赋给form
      //row.id
      // 方式二: 常规方式 可以通过row.id 去后端数据库DB 获取对应的家居信息
      // ,返回后将其绑定到 this.form ,绑定后 其数据会显示到form表单的输入框中
      request.get("/api/findFurn/" + row.id).then(res => {

        console.log("handleEdit(row) axios get findFurnById res= ", res);

        this.form = res.extend.furn;


      })


      //显示表单
      this.dialogVisible = true


    },
    handleDel(id) {

      console.log("handleDel(id) 要删除的furn id= ", id)

      request.delete("/api/del/" + id).then(res => {
        // 这里是 发出ajax请求后的回调函数
        console.log("handleDel(id) res= ", res);

        if (res.code === 200) {
          //老韩写的这种 Message 消息反馈组件 , 不需要写导入语句，就可以直接使用,更加简洁
          this.$message({
            type: "success",
            message: res.msg
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

        //刷新显示页面
        this.list();

      })
    },

    handleCurrentChange(pageNum) {//处理分页请求
      //  当 导航条页码/分页超链接 被点击时触发该方法
      //  ,同时会携带被点击的pageNum
      console.log("pageNum= ", pageNum)
      this.currentPage = pageNum;

      //发出分页请求
      this.list();
    },
    handlePageSizeChange(pageSize) {
      //  当导航条每页显示的数据条数被更改时触发该方法,同时携带页码导航条设置的pageSize过来
      console.log("pageSize= ", pageSize)

      this.pageSize = pageSize;

      //发出分页请求
      this.list();

    },

  }

}
</script>
