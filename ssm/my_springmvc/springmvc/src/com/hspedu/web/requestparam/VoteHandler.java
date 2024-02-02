package com.hspedu.web.requestparam;

import com.hspedu.web.requestparam.entity.Master;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.spec.PSSParameterSpec;
import java.util.Map;

/**
 * @author yangda
 * @description:
 * @create 2023-09-27-18:47
 */
@RequestMapping(value = "/vote")
@Controller
public class VoteHandler {

    /**
     * http请求的url上,有在@RequestParam注解 value属性上指定的 名字的参数名
     * 老韩解读 @RequestParam(value="name", required=false)
     * 1. 获取到超链接传递的数据 请求 http://localhost:8080/springmvc/vote/vote01?name=xx
     * 2. @RequestParam 表示会接收提交的参数
     * 3. value="name" 表示提交的参数名是name
     * 4. required=false 表示该参数可以没有, 默认是true,表示必须有这个参数
     * 5. 当我们使用了@RequestParam(value="name", required=false)后就请求的参数名和方法的形参名可以不一致
     *
     *   defaultValue = "1" :表示 如果传入的有值，就使用传入的值，如果没有传入值，则使用defaultValue = "1" 作为该形参的值
     *
     *   注意:如果没指定value 只使用了 @RequestParam 注解后 url中的参数名和方法形参的参数名[String username]保持一致,
     *   也获取不到参数，获取到的是null
     *
     * 可以接收的参数类型
     * 1.表单提交 (form-data)
     * 2.URL请求参数
     *-------------------------
     * 与自定义类型参数封装(方法上不使用任何注解)的对比
     * Spring 框架中的 @RequestParam 注解和不使用任何注解的方法形参都可以用来接收 URL 参数和 application/x-www-form-urlencoded 格式的 form 表单提交的参数。但这两种方式在功能和使用场景上有所区别：
     *
     * 使用 @RequestParam 注解：
     *
     * 明确指定：你可以明确指定要获取的参数名称。例如，@RequestParam("name") String userName 会获取名为 name 的参数并将其值绑定到 userName 变量。
     * 可选和默认值：@RequestParam 允许你设置参数为可选（required=false），并为参数指定默认值（defaultValue="someValue"）。
     * 更多控制权：@RequestParam 提供了更多的控制权，比如处理多值参数（如同一个参数名出现多次）。
     * 不使用注解的方法形参：
     *
     * 隐式名称匹配：如果方法的参数名和请求参数名相同，Spring 会自动将请求参数的值绑定到对应的方法参数。例如，方法参数为 String name 会自动匹配请求中的 name 参数。
     * 简洁：对于简单的情况，不使用注解可以使代码更简洁。
     * 灵活性较低：不使用注解意味着你不能指定默认值，也不能处理可选参数的情况。
     * 补充说明：
     *
     * 在使用不带注解的形参接收数据时，Spring 依赖于参数名。但是，如果你的项目经过了混淆或使用了不保存参数名的编译器设置，这可能会导致问题。为了确保准确性，建议使用 @RequestParam。
     * @RequestParam 适用于处理简单类型的数据（如 String, int, Enum 等），而对于复杂类型（如自定义的 Java Bean），通常推荐使用 @ModelAttribute 来处理。
     * 在选择使用哪种方式时，应该考虑你的具体需求。如果需要更多的控制或处理更复杂的情况，@RequestParam 是更好的选择。如果只是简单地接收数据，直接使用方法参数可能更方便。
     *
     *------------------------
     * GPT
     * @RequestParam 注解在 Spring 框架中被用于处理 HTTP 请求中的查询参数（即 URL 参数）。它可以将请求参数绑定到你的控制器方法的参数上。
     *
     * 举个例子，如果你有一个 URL 如 http://example.com/api?name=Tom，你可以使用 @RequestParam 来获取 name 参数的值。
     *
     * 关于是否可以接收 form 表单提交的数据，答案也是肯定的。当 form 表单的内容类型（Content-Type）设置为 application/x-www-form-urlencoded 时，@RequestParam 同样可以处理这些表单数据。在这种情况下，form 表单中的字段被视为请求参数，和 URL 中的查询参数类似。
     *
     * 总结一下，@RequestParam 既可以处理 URL 中的查询参数，也可以处理以 application/x-www-form-urlencoded 格式提交的 form 表单数据。
     */
    @RequestMapping(value = "/vote01")
    //public String test01(String username){
    public String test01(@RequestParam(value = "name",required = false/*,defaultValue = "1"*/) String username){
    //public String test01(@RequestParam(value = "name") String username){
        //required属性默认是 true ,即不填就是默认"required = true" ,表示前端必须传递一个参数名为name的参数
        //否则会报错:Required request parameter 'name' for method parameter type String is not present
        System.out.println("得到的username= " + username);
        //返回到一个结果
        return "success";
    }


    /**
     * 需求: 获取http请求头信息, 获取到Accept-Encoding 和 Host
     * 1. 这里涉及到前面讲过的http协议,小伙伴可以进行回顾
     *
     * @RequestHeader("Http请求头字段")
     */
    @RequestMapping(value = "/vote02")
    public String test02(@RequestHeader("Accept-Encoding") String ae,
                         @RequestHeader("Host") String host){
        System.out.println("Accept-Encoding= " + ae);
        System.out.println("Host= " + host);
        //返回到一个结果
        return "success";
    }


    /**
     * 演示如果获取到提交数据->封装成java对象
     *
     * @return 老师说明
     * 1. 方法的形参用对应的类型来指定即可, SpringMVC会自动的进行封装
     * 2. 如果自动的完成封装, 要求提交的数据，参数名和对象的字段名保持一致
     * 3. 如果属性是对象，这里就是仍然是通过 字段名.字段名 比如Master [pet]
     * , 即提交的数据 参数名 是 pet.id pet.name， 这就是级联操作
     * 4. 如果提交的数据 的参数名和对象的字段名不匹配，则对象的属性值就是null
     * 5. 小伙伴疑惑，怎么就封装成功[底层仍然是反射+注解..]
     *
     * --下面这种方式接收参数是接收的表单提交的数据,完成自动封装
     * 在springboot 接收参数相关注解 自定义对象参数-自动封装时讲过
     * 下面这种方式接收参数 如果前端是在地址栏的参数位置提交的数据,如:Post请求 http://localhost:9090/save?id=101&name=喜喜台灯&maker=xx之家&price=30.3&sales=3&stock=200
     * 也可以完成封装--
     *
     * 注意事项：
     * 1. 支持级联数据获取
     * 2. 表单的控件名称name需要和javabean对象字段对应，否则就是空数据
     */
    @RequestMapping(value = "/vote03")
    public String test03(Master master) {

        System.out.println("master=" + master);
        //返回结果
        return "success";
    }

    /**
     * 使用servlet api, 来获取提交的数据
     *
     * @return
     */
    @RequestMapping(value = "/vote04")
    public String test04(HttpServletRequest request, HttpServletResponse response,HttpSession hs) {
        //获取到session
        //servlet原生的方式
        HttpSession session = request.getSession();
        System.out.println("session=" + session);
        //注意:通过参数传入的 hs 和 通request.getSession() 得到的对象是
        //同一个
        System.out.println("hs= " + hs);

        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        System.out.println("username= " + username);
        System.out.println("password= " + password);

        //返回结果
        return "success";
    }


    /**
     * 老韩解读
     * 1. 演示将提交的数据->springmvc封装到java对象->springmvc 会自动的将其放入到request域
     * 2. 这样我们就可以在跳转到的页面取出数据.
     *
     * @return
     */
    @RequestMapping(value = "/vote05")
    public String test05(Master master, HttpServletRequest request) {

        //老韩解读
        //1. springmvc会自动把获取的model模型，放入到request域中，名字就是master
        //2. 也可以手动将master放入到request[一会在讲]
        request.setAttribute("address", "beijing");
        //3. 如果我们希望修改master的属性值 , 因为存放的是引用 所以request域 中的对象属性也会相应的修改
        master.setName("nono");
        //4. 分析一下springmvc默认存放对象到request域中,属性名是
        //   request域 ("master", master) 属性名(key)是类名/类型名 首字母小写 Master => "master"
        //返回到一个结果
        return "vote_ok";
    }


    /**
     * 演示通过Map<String,Object> 设置数据到request域
     *
     * @return
     */
    @RequestMapping(value = "/vote06")
    public String test06(Master master, Map<String, Object> map) {
        System.out.println("------test06-----");
        //老韩解读
        //1. 需求是通过map对象，添加属性到request中
        //2. 原理分析：springmvc会遍历map，然后将map的k-v, 存放到request域
        map.put("address", "beijing...");

        //map.put("master", null);  // 这里会替换上面通过形参传入的request域中放入的master对象
        //返回到一个结果
        return "vote_ok";
    }

    /**
     * 演示通过返回ModelAndView对象，将数据放入到request域
     * 1) 从本质看，请求响应的方法 return "xx", 是返回了一个字符串，其实本质是返回了一个
     *    ModelAndView 对象，只是默认被封装起来的.
     * 2) ModelAndView 即可以包含 model 数据，也可以包含视图信息
     * 3) ModelAndView 对象的 addObject 方法可以添加 key-val 数据，默认在 request 域中
     * 4) ModelAndView 对象 setView 方法可以指定视图名称
     */
    @RequestMapping(value = "/vote07")
    public ModelAndView test07(Master master) {

        System.out.println("----test07----");
        ModelAndView modelAndView = new ModelAndView();
        //放入属性到modelAndView对象 默认在 request 域中
        modelAndView.addObject("address", "shanghai~~");
        //modelAndView.addObject("master", null);
        //可以把从数据库得到的数据->对象-》放入modelAndView[Service-dao-db]
        //这里指定跳转的视图名称
        modelAndView.setViewName("vote_ok");
        //返回结果
        return modelAndView;
    }


    /**
     * 演示如何将数据设置到session域中
     * @return
     */
    @RequestMapping(value = "/vote08")
    public String test08(Master master, HttpSession httpSession) {
        System.out.println("----test08----");
        //master对象是默认放在request域
        //我们将master对象放入到session域
        httpSession.setAttribute("master", master);
        httpSession.setAttribute("address", "guangzhou");

        return "vote_ok";//请求转发
    }


    /**
     * 老师解读
     * 1. 当Handler的方法被标识 @ModelAttribute,就视为一个前置方法
     * 2. 当调用该Handler的其它的方法时，都会先执行该前置方法
     * 3. 类似我们前面讲解Spring时，AOP的前置通知[底层是AOP机制]
     * 4. prepareModel前置方法，会切入到其它方法前执行..
     */
    @ModelAttribute
    public void prepareModel(){
        System.out.println("prepareModel()-----完成准备工作-----");
    }







}
