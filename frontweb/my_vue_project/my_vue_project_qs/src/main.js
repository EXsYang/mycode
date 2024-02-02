// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//import Vue from 'vue'
//import App from './App'
//import router from './router'


// 将上面的引入写完整
import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js' // 如果写到/router，
// 相当于把router目录下所有文件都引入(这里默认找的index.js文件)

//引入 element-ui 组件库/样式， 并使用
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
// 在Vue中只要是使用插件 通常会加一句Vue.use这个指令!
// 像Router在router/index.js中也用到了 Vue.use(Router)
//使用ElementUI插件
Vue.use(ElementUI);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',//这里的#app 是挂到 index.html 的 <div id="app"></div>
  //router,//完整写法是 router: router, 第二个 router 是 import router[这里] from './router'
  //components: { App },//完整写法是 components: { 'App':App } 因为名字相同可以省略 'App'
  //template: '<App/>'//这里的 '<App/>' 的 App 就是上面 components 引入的组件的名字

  // 下面的代码是写完整后 router是一个属性 就像el 不能乱写！
  router:router,
  //下面的白色的App 来自于上面 import App from './App'
  components: { "HSPApp":App },
  //下面的黄色的HSPApp 来自于components里的"HSPApp"
  template: '<HSPApp/>'
})
