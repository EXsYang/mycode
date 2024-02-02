package com.hspedu.web.json;

import com.hspedu.web.json.entity.Dog;
import com.hspedu.web.json.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-12-10:55
 * @description:
 * 当return的是一个实体类，对象，集合的时候，
 * 就不能普通的return，那样回报解析不了的错误，这里使用jackson来进行类型转换
 *
 */
//@Controller
//@ResponseBody  //直接在类上标注该注解 相当于类中所有的目标方法 都标注了@ResponseBody注解

//@ResponseBody + @Controller 可以直接写成 @RestController , 我们看一下@RestController源码!
@RestController
public class JsonHandler {

    /**
     * 老师解读
     * 1. 目标方法 @ResponseBody，表返回的数据是json格式
     *    , 将JavaBean转为json字符串 底层使用的是JavaBean中的getter()方法
     * 2. springmvc底层根据目标方法@ResponseBody, 返回指定格式, 根据的http请求来进行处理
     * 3. 底层原理我们在前面自定义@ResponseBody讲过, 这里原生的springmvc使用转换器
     * 4. HttpMessageConverter [一会老师debug]
     *
     * @return
     */
    @RequestMapping(value = "/json/dog")
    //@ResponseBody
    public Dog getJson() {

        //返回对象
        //springmvc会根据你的设置，转成json格式数据返回
        Dog dog = new Dog();
        dog.setName("大黄狗");
        dog.setAddress("小新的家");

        // 这里返回的是一个对象的形式
        // 标注@ResponseBody注解后
        // 将JavaBean转为json字符串
        // 底层使用的是JavaBean中的getter()方法
        return dog;

    }

    //编写方法以json格式返回多个数据
    @RequestMapping(value = "/json/dogs")
    //@ResponseBody
    public List<Dog> getJsons() {

        System.out.println("/json/dogs 请求后端getJsons()");
        //返回对象
        //springmvc会根据你的设置，转成json格式数据返回
        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("大黄狗","北京"));
        dogs.add(new Dog("大黑狗","上海"));
        dogs.add(new Dog("小黑狗","河北"));
        dogs.add(new Dog("中黑狗","河南"));

        return dogs;

    }

/**
 * 老师解读
 * @RequestBody作用
 *  @RequestBody的作用 第1个功能
 * 1. @RequestBody User user 在形参指定了 @RequestBody
 * 2. springmvc就会将提交的json字符串数据填充给指定Javabean
 *
 *  @RequestBody注解,用来接收json格式数据
 *  1.我们的前端如果是以json格式来发送添加信息furn,那么我们后端需要使用@RequestBody注解
 *  ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
 *  2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data

 *
 *  @RequestBody第2个功能 是整体取出 Post 请求内容！ 即获取post请求体
 *  注意:1.如果前端使用`Postman的Post方式提交数据，同时指定params提交`
 *  ，在不使用@RequestBody的情况下，即形参位置没有使用任何注解，
 *  也可以将params正常的封装到下面方法形参的User对象中
 * --下面这种方式接收参数是接收的表单提交的数据,完成自动封装
 * 在springbootweb搜‘自定义参数封装’ 接收参数相关注解 自定义对象参数-自动封装时讲过
 * 下面这种方式接收参数 2.如果前端是在`地址栏的参数位置`提交的数据,如:Post请求 http://localhost:8080/save?userName=jack&age=22
 * 也可以完成封装--
 *  即：如果前端是以表单形式/或者是以parameters提交了,则不需要@RequestBody,才会进行对象封装，
 *  同时保证http的请求头的content-type是对应的
 *
 *
 *
 *
 * @ResponseBody 这个注解通常使用在控制层（controller）的方法上，
 * 其作用是将方法的返回值以特定的格式写入到response的body区域，
 * 进而将数据返回给客户端。当方法上面没有写ResponseBody,
 * 底层会将方法的返回值封装为ModelAndView对象。
 * 当方法上面没有写@ResponseBody 返回 HTTP Status 404 - /ssm/WEB-INF/views/save.html
 *
 * 如果用postman 发送json 注意在 Headers 中增加字段 Content-Type:application/json
 * 否则报错 415状态码  415Unsupported Media Type - 不支持媒体类型
 * 在Body体 使用原始模式 raw JSON 来发送数据
 *
 * * 如果前端发送的是json数据 而目标方法形参上没有写 @RequestBody 会报错 500状态码
 *
 * @ResponseBody 和 jackson 配合使用, 否则会报错, 没法解析
 *
 *
 */
    @RequestMapping(value = "/json/save2")
    //当方法直接返回一个JavaBean对象,要给前端返回的数据类型是json类型时,
    // 若方法上面没有写@ResponseBody 则报错 返回 HTTP Status 404
    //@ResponseBody //该注解表示 以json格式返回数据给客户端(底层是按照http协议进行协商)
    // public User save2(User user) { //这里形参上没有写 @RequestBody 不会会报 500状态码
    public User save2(@RequestBody User user) {
        //@RequestBody 注解将前台传过来的json格式的数据 封装到形参user中
        // 如果不写注解 @RequestBody 前端使用json格式提交过来的数据 服务器端接收到的user
        // ,各个属性的值就是null,同时前端会报500错误
        // 以 json 的格式相应回浏览器
        System.out.println("user~= " + user);

        // 这里返回的是一个对象 , 而不是视图 需要在pom.xml 引入 Jackson 否则会报错
        // 经过测试 结果: 就算没有引入jackson,只要在形参上标注了 @RequestBody
        // 前端如果使用发送过来的是json格式的数据 就会被封装到形参furn中
        // 即@RequestBody 封装json数据到形参furn 不需要用到jackson
        // 但是 @ResponseBody 注解 需要和jackson配合使用 否则返回一个对象的形式 会报错
        // 想要返回一个对象的形式 使用 @ResponseBody 以json格式返回数据给客户端
        // 但是必须引入jackson
        return user;
    }

    //响应用户下载文件的请求
    @RequestMapping(value = "/downFile")
    public ResponseEntity<byte[]> downFile(HttpSession httpSession) throws Exception{

        System.out.println("/downFile 请求目标方法downFile()");

        //1. 先获取到下载文件的inputStream
        //下面这个servletContext.getResourceAsStream() 第一个斜杠表示
        // 在和src同级的web目录下读取文件
        InputStream inputStream =
                httpSession.getServletContext().getResourceAsStream("/img/1.jpg");

        //2. 开辟一个存放文件的byte数组, 这里老师使用byte[] 是可以支持二进制数据(图片，视频。)
        //available:可获得的
        //available() 返回从该输入流中可以读取（或跳过）的字节数的估计值
        byte[] bytes = new byte[inputStream.available()];


        //3. 将下载文件的数据，读入到byte[]
        //read(bytes):从输入流读取一些字节数，并将它们存储到缓冲区bytes 。
        inputStream.read(bytes);

        //public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {}
        //4. 创建返回的HttpStatus
        HttpStatus httpStatus = HttpStatus.OK;
        //5. 创建 headers
        HttpHeaders httpHeaders = new HttpHeaders();
        //指定返回的数据，客户端应当以附件形式处理
        // filename=1.jpg 指定的是客户端下载时的文件名 这里写的是什么 下载时文件名就是什么
        httpHeaders.add("Content-Disposition","attachment;filename=1.jpg");

        //构建一个ResponseEntity 对象1. 的http响应头headers 2. http响应状态 3. 下载的文件数据
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, httpStatus);
        //如果出现找不到文件，解决方法 rebuild project -> 重启tomcat



        return responseEntity;
    }


}
