package com.hspedu.furn.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.bean.Msg;
import com.hspedu.furn.service.FurnService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangda
 * @create 2023-11-14-13:04
 * @description: 处理家居 CRUD 请求
 */
@Controller
public class FurnController {

    // 属性 自动装配
    @Resource
    private FurnService furnService;


    // 处理添加请求 并给浏览器返回json数据

    /**
     * 响应客户端的添加请求
     * 1. @RequestBody Furn furn 在形参指定了 @RequestBody
     * 2. springmvc就会将提交的json字符串数据填充给指定Javabean
     *
     * @param furn
     * @return
     * @ResponseBody 这个注解通常使用在控制层（controller）的方法上，
     * 其作用是将方法的返回值以特定的格式写入到response的body区域，
     * 进而将数据返回给客户端。当方法上面没有写ResponseBody,
     * 底层会将方法的返回值封装为ModelAndView对象。如下面会返回 HTTP Status 404 - /ssm/WEB-INF/views/save.html
     * <p>
     * 如果用postman 发送json 注意在 Headers 中增加字段 Content-Type:application/json
     * 否则报错 415状态码 415Unsupported Media Type - 不支持媒体类型
     * 在Body体 使用原始模式 raw JSON 来发送数据
     * <p>
     * 如果前端发送的是json数据 而目标方法形参上没有写 @RequestBody 会报错 500状态码
     */
    @PostMapping("/save")
    //当方法直接返回一个JavaBean对象,要给前端返回的数据类型是json类型时,
    // 若方法上面没有写@ResponseBody 则报错 返回 HTTP Status 404
    @ResponseBody  //该注解表示 服务器以json格式返回数据给客户端(底层是按照http协议进行协商)
    // public Msg save(Furn furn) { // //形参上没有写 @RequestBody 会报错 500状态码
    public Msg save(@Validated @RequestBody Furn furn, Errors errors) { // //形参上没有写 @RequestBody 会报错 500状态码

        HashMap<String, Object> map = new HashMap<>();

        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            //fieldError.getDefaultMessage() 就是在Furn中配置的错误信息  @NotNull(message = "请输入数字")
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        if (map.isEmpty()){

            System.out.println("FurnitureController save() 接收到的furn= " + furn);

            // 如果没有写 @RequestBody 结果如下,并且前端会报500错误
            // FurnitureController save() 接收到的furn= Furn{id=null, name='null', maker='null', price=null, sales=null, stock=null, imgPath='assets/images/product-image/1.jpg'}

            // System.out.println("只使用@RequestBody注解 没有引入jackson时, 查看前端使用json提交的数据是否会被服务器端接收并封装到形参furn中 " +
            //         "furn= " + furn);
            // 经过测试 结果: 就算没有引入jackson,只要在形参上标注了 @RequestBody
            // 前端如果使用发送过来的是json格式的数据 就会被封装到形参furn中
            // 即@RequestBody 封装json数据到形参furn 不需要用到jackson
            // 但是 @ResponseBody 注解 需要和jackson配合使用 否则返回一个对象的形式 会报错
            // 想要返回一个对象的形式 使用 @ResponseBody 以json格式返回数据给客户端
            // 但是必须引入jackson

            furnService.save(furn);
            // 添加失败会报错 先不处理 就当作都成功了

            // 给浏览器 返回信息
            // Msg msg1 = msg.add("furn", furn);
            // msg.add("furn", furn);

            // 这里返回的是一个对象 , 而不是视图 需要在pom.xml 引入 Jackson 否则会报错
            /**
             * 当return的是一个实体类，对象，集合的时候，
             * 就不能普通的return，那样回报解析不了的错误，这里使用jackson来进行类型转换
             *
             * 如果不引入 jackson依赖 即使加了@ResponseBody 这里解析也会失败 HTTP Status 415
             * The server refused this request because the request entity is in a format not supported by the requested resource for the requested method.
             * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.logException Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/json;charset=UTF-8' not supported]
             * 需要引入  jackson 使springmvc支持json 格式的请求数据
             *
             * 如果没有写注解@ResponseBody 会按照下面这种 去找视图
             * HTTP Status 404 - /ssm/WEB-INF/views/save.html
             */
            return Msg.success();
        }else { //有错误信息 验证失败了
            //校验失败 就把错误信息封装到Msg对象，并返回
                return Msg.fail().add("errorMsg",map);
        }



    }


    @RequestMapping("/furns")
    @ResponseBody
    public Msg listFurns() {
        List<Furn> furnList = furnService.findAll();

        // Msg msg = Msg.success();
        // msg.add("furnList",furnList);

        // return msg;
        return Msg.success().add("furnList", furnList);
    }


    /**
     * 更新家居 更新操作一般使用 Put 发出请求
     * 因为这里前端可以直接使用put请求 而不是只能发出get/post 所以在这不需要配置
     * 支持rest风格请求 的过滤器/转换器( HiddenHttpMethodFilter 过滤器可以对以Post方式提交的delete,put,patch进行转换 )
     *
     * @param furn
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public Msg update(@RequestBody Furn furn) {

        Msg msg = null;
        int affected = furnService.update(furn);
        if (affected == 1) {
            msg = Msg.success();
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
            msg = Msg.fail();
        }

        return msg;

    }


    /**
     * 路径变量 @PathVariable 用法
     * 1、若方法参数名称和需要绑定的url中变量名称一致时,可以简写:
     * @RequestMapping("/getUser/{name}")
     *     public User getUser(@PathVariable String name){
     *         return userService.selectUser(name);
     *     }
     * 2、若方法参数名称和需要绑定的url中变量名称不一致时，写成:
     * @RequestMapping("/getUserById/{name}")
     *     public User getUser(@PathVariable("name") String userName){
     *         return userService.selectUser(userName);
     *     }
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    @ResponseBody
    public Msg del(@PathVariable Integer id) {
        Msg msg = null;
        int affected = furnService.del(id);

        if (affected > 0) {
            msg = Msg.success();
            System.out.println("删除成功");
        }else {
            msg = Msg.fail();
            System.out.println("删除失败");
        }
        return msg;
    }


    //提供接口根据id返回对应的furn对象-封装成Msg返回
    @GetMapping("findFurn/{id}")
    @ResponseBody
    public Msg findFurnById(@PathVariable Integer id) {

        Msg msg = null;

        Furn furn = furnService.findFurnById(id);

        if (furn != null){
            msg = Msg.success();
            msg.add("furn",furn);
        }else {
            msg = Msg.fail();
        }

        return msg;
    }


    /**
     * 分页请求接口
     *
     * @param pageNum: 要显示第几页 默认为 1
     * @param pageSize: 每页要显示几条记录，默认为 5
     * @return
     */
    @ResponseBody
    @RequestMapping("/furnsByPage")
    public Msg listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {

        //设置分页参数
        //解读
        //1.调用findAll是完成查询，底层会进行物理分页，而不是逻辑分页
        //2.会根据分页参数来计算 limit ?,?  , 在发出SQL语句时,会带limit
        //3.抓取SQL 可见 子句发出时带有limit
        // 经过下面代码的处理 这里总共发出了两次SQL
        // ==>  Preparing: SELECT count(0) FROM furn
        // ==>  Preparing: select id, name, maker, price, sales, stock, img_path from furn LIMIT ?

        PageHelper.startPage(pageNum, pageSize);

        List<Furn> furnList = furnService.findAll();

        //将分页查询的结果，封装到PageInfo
        //PageInfo 对象包含了分页的各个信息，比如当前页面的pageNum,共有多少记录...

        PageInfo pageInfo = new PageInfo(furnList, pageSize);

        //将pageInfo封装到Msg对象 返回

        return Msg.success().add("pageInfo",pageInfo);


    }

    /**处理带条件分页查询
     * 根据名字 返回分页查询的furn数据
     * 注意该方法是按照params 方式提交参数的，不接收json格式数据提交的参数数据
     * 接收的方式是 http://localhost:8080/ssm/furnsByConditionPage?pageNum=1&pageSize=2&search=台灯
     * 或者如下这种形式
     *
     *   request.get("/api/furnsByConditionPage", {
     *         //params属性可以携带请求参数,在该对象内部指定
     *         //params是axios内建对象
     *         params: {
     *           //这里的json对象属性pageNum ,
     *           // 将来对应后端目标方法形参的 Integer pageNum ,要对应好
     *           pageNum: this.currentPage,
     *           pageSize: this.pageSize,
     *           search: this.search
     *         }
     *       }).then(res => { }
     *
     * @return
     */
    @GetMapping("/furnsByConditionPage")
    @ResponseBody
    public Msg listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        @RequestParam(defaultValue = "") String search){

        //设置分页参数
        //解读
        //1.调用findAll是完成查询，底层会进行物理分页，而不是逻辑分页
        //2.会根据分页参数来计算 limit ?,?  , 在发出SQL语句时,会带limit
        //3.抓取SQL 可见 子句发出时带有limit
        // 经过下面代码的处理 这里总共发出了两次SQL
        // ==>  Preparing: SELECT count(0) FROM furn
        // ==>  Preparing: select id, name, maker, price, sales, stock, img_path from furn LIMIT ?
        PageHelper.startPage(pageNum,pageSize);

        //返回根据条件查询的结果 这里将按照条件查询的结果的整体 作为物理分页的依据
        List<Furn> furnList = furnService.findByCondition(search);

        //将分页查询的结果，封装到PageInfo
        //PageInfo 对象包含了分页的各个信息，比如当前页面的pageNum,共有多少记录...

        PageInfo pageInfo = new PageInfo(furnList, pageSize);

        /**
         * 带条件的查询 这里也发出了两次SQL 第一次把满足该条件的 数据的总条数返回来
         * 第二次返回来的是对应页数(pageNum)的 pageSize条数据
         * ==>  Preparing: SELECT count(0) FROM furn WHERE (name LIKE ?)
         * ==> Parameters: %台灯%(String)
         * <==    Columns: count(0)
         * <==        Row: 10
         * <==      Total: 1
         * ==>  Preparing: select id, name, maker, price, sales, stock, img_path from furn WHERE ( name like ? ) LIMIT ?
         * ==> Parameters: %台灯%(String), 2(Integer)
         * <==    Columns: id, name, maker, price, sales, stock, img_path
         * <==        Row: 18, 小台灯, 京东家居, 30.00, 3, 100, assets/images/product-image/1.jpg
         * <==        Row: 19, 小台灯, 京东家居, 30.00, 3, 100, assets/images/product-image/1.jpg
         * <==      Total: 2
         */


        //将pageInfo封装到Msg对象 返回
        return Msg.success().add("pageInfo",pageInfo);
    }

}
