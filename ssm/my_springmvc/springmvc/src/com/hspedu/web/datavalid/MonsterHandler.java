package com.hspedu.web.datavalid;

import com.hspedu.web.datavalid.entity.Furn;
import com.hspedu.web.datavalid.entity.Monster;
import com.hspedu.web.datavalid.entity.Msg;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-10-10-11:41
 * @description: MonsterHandler 处理器响应用户提交数据
 * @Scope(value = "prototype") 表示每次请求MonsterHandler会生成一个新的对象
 */
@Controller
@Scope(value = "prototype")
public class MonsterHandler {


    //显示添加 monster 的界面
    /**
     * 显示添加monster的界面
     * 1. 这里Map<String, Object> map
     * 2. 当我们向map添加的数据时，会默认存放到request域
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/addMonsterUI")
    public String addMonsterUI(Map<String, Object> map) {
/**老韩解读:
 1. 这里的表单，我们使用 springMVC 的标签来完成
 2. SpringMVC 表单标签在显示之前必须在 request 中有一个 bean, 该 bean
 的属性和表单标签的字段要对应!
 request 中的 key 为: form 标签的 modelAttrite 属性值， 比如这里的
 monsters
 3. SpringMVC 的 form:form 标签的 action 属性值中的 / 不代表 WEB 应用
 的根目录.
 4. <form:form action="monster" method="POST" modelAttribute="monster">
    这 里 需 要 给 request 增 加 一 个 monster ， 因 为 jsp 页 面 的
 modelAttribute="monster"需要
    这时是 springMVC 的内部的检测机制 即使是一个空的也需要，否则报错.

    老韩再次说明，如果你跳转的页面使用springmvc标签
    就需要准备一个对象，放入request域中，这个对象的属性名 monster, 对于
 */
        map.put("monster", new Monster());
        return "datavalid/monster_addUI";
    }

    /**
     * 编写方法,处理添加妖怪
     * 1. springmvc可以将提交的数据，按照参数名和对象的属性名匹配
     * 2. 直接封装到对象中->老师前面讲解模型数据时，讲过
     * String => Integer
     * 3. @Valid Monster monster :表示对monster接收的数据进行校验
     * 4. Errors errors 表示如果校验出现错误，将校验的错误信息保存 errors
     * 5. Map<String, Object> map  表示如果校验出现错误, 将校验的错误信息保存 map 同时保存monster对象
     * 6. 校验发生的时机： 在springmvc底层，反射调用目标方法时，会接收到http请求的数据，然后根据注解来进行验证
     * , 在验证过程中，如果出现了错误，就把错误信息填充errors 和 map
     *
     *
     * @param monster
     * @return
     */
    @RequestMapping(value = "/save")
    // 使用验证注解 @Valid时, 通常在形参加两个参数: Errors errors,Map<String,Object> map
    public String save(@Valid Monster monster ,Errors errors,Map<String,Object> map){
        System.out.println("=======monster======= " + monster);
        //我们为了看到验证的情况,我们输出map 和 errors
        System.out.println("===== map ======");
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            System.out.println("entry-key:"+entry.getKey() + " entry-value:" + entry.getValue());
        }
        System.out.println("===== errors ======");
        if (errors.hasErrors()){
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println("error:"+error);
            }
            return "datavalid/monster_addUI";
        }


        return "datavalid/success";
    }


    /**
     * 该方法出自于D:\Java_developer_tools\ssm\my_ssm\furn-ssm\src\main\java\com\hspedu\furn\controller\FurnController.java.save()
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
    /**
     *
     在 Spring Framework 中，当您在控制器（Controller）的方法上使用 @ResponseBody 注解时，
     它告诉 Spring 框架，您希望返回的数据直接写入 HTTP 响应体（Response Body），
     而不是解析为跳转路径或模板名称。这是 RESTful Web 服务的一个常见实践，
     用于返回 JSON 或 XML 等格式的数据。
     当您的方法返回一个 List<Monster> 并使用了 @ResponseBody 注解时，
     Spring 会自动使用内置的消息转换器（通常是 Jackson）将该列表转换为 JSON 格式。
     这个过程发生在以下几个步骤中：
     *返回数据类型识别：您的方法返回一个 List<Monster> 类型的数据。
     *使用 @ResponseBody：这个注解告诉 Spring，返回的对象需要被转换为响应体，而不是视图名称。
     *消息转换：Spring 使用消息转换器（默认是 Jackson JSON 转换器）将返回的 List<Monster>
     对象转换为 JSON 格式的字符串。
     *响应设置：转换后的 JSON 字符串被写入 HTTP 响应体，并且通常情况下，
     * 响应的 Content-Type 被设置为 application/json，表明响应的内容类型是 JSON。

     通过使用 @ResponseBody 注解和 Spring 的消息转换机制，
     返回的数据会被自动转换为相应格式的 JSON 字符串，
     然后发送给客户端。这使得创建返回 JSON 数据的 RESTful API 变得非常简洁和直接。

     注意: 后端使用 @RequestBody Long[] ids 接收数据，前端也需要使用数组传递数据！如 [1, 2, 3] 的JSON数组
     ===========================================================

     @Valid 和 @Validated 这两个有什么区别？
     GPT
      * 1. @Validated 注解的作用就是启用 BrandEntity 字段上添加的校验
      *    注解@Validated 是由Spring框架提供的，
      *    作为@Valid的一个增强版，支持分组功能！！！ @Valid 注解不支持验证分组功能
     * /* @RequestMapping("/save")
     *    public R save(@Validated(SaveGroup.class) @RequestBody BrandEntity brand) {
     *        // 业务逻辑
     *    }
     *
     @Valid 和 @Validated 都是用于验证 Java Bean 对象的注解，它们触发了 Spring 的验证机制。这两个注解在使用上有相似之处，但也有一些关键的区别：

     @Valid
     @Valid 注解是由 JSR 303 规范提供的，随后在 JSR 349 和 JSR 380 中得到了扩展。
     它可以被用于方法参数上，以确保方法被调用前参数满足约束条件。
     @Valid 注解会触发参数对象的验证，如果对象内部包含其他对象引用，并且也用 @Valid 进行注解，将会递归验证嵌套对象。
     @Valid 不支持分组验证，即不能根据不同的场景（例如创建时、更新时）进行不同的验证。
     @Validated
     @Validated 是 Spring 提供的注解，是 Spring 的特定实现。
     它扩展了 @Valid 的功能，支持分组验证，可以指定验证组进行验证，适用于需要在不同场景下应用不同验证规则的情况。
     @Validated 可以用于类型级别和方法参数级别。当应用于类型级别（比如在一个控制器类上）时，它表示验证应该在该类型的 Bean 上进行。
     @Validated 通常与 Spring 的 @Controller 或 @Service 注解一起使用。
     在您的 MonsterHandler 类中，save 方法中的 @Valid 注示 Spring 在将请求参数绑定到 Monster 对象之前验证这个对象。如果验证失败，错误将会被传递给紧随其后的 Errors 对象。

     在 save 方法的另一个重载中，使用了 @Validated 来验证 Furn 对象。这允许您根据需要指定验证组，但在这个特定的例子中，没有指定分组，所以它的行为与 @Valid 类似。
@
     总结来说，当您需要分组验证或者使用 Spring 特有的特性时，您应该使用 @Validated。如果您只需要基础的 Bean 验证，那么 @Valid 就足够了。在大多数情况下，两者是互换的，除非您需要 @Validated 提供的额外功能。


     注意事项：使用@Valid注解的参数顺序要求
     当使用Spring MVC进行数据验证时，确实有一个特定的顺序要求，即BindingResult参数必须紧跟在使用@Valid注解的参数之后。这是因为BindingResult对象用于存储@Valid注解的参数验证后的结果（如错误信息）。如果BindingResult不紧跟在@Valid注解的参数之后，Spring将无法正确地将验证错误关联到相应的模型对象。
     以下是一个示例：

     java
     Copy code
     import org.springframework.stereotype.Controller;
     import org.springframework.web.bind.annotation.PostMapping;
     import org.springframework.web.bind.annotation.RequestMapping;
     import org.springframework.web.bind.annotation.ModelAttribute;
     import org.springframework.validation.BindingResult;
     import javax.validation.Valid;

     @Controller
     @RequestMapping("/user")
     public class UserController {

     @PostMapping("/register")
     public String registerUser(@ModelAttribute @Valid User user, BindingResult result) {
     if (result.hasErrors()) {
     // 处理验证错误
     return "registerForm";
     }

     // 正常的业务逻辑
     // ...

     return "registrationSuccess";
     }
     }
     在这个例子中，registerUser方法处理用户注册的POST请求。方法接收一个User对象和一个BindingResult对象作为参数。User对象使用了@Valid注解，意味着当有注册请求时，Spring将对User对象进行验证。BindingResult参数紧跟在User参数之后，用于捕获和访问任何验证错误。

     如果BindingResult对象不在@Valid参数之后，Spring MVC将无法将验证错误关联到正确的模型对象，这可能导致应用程序抛出异常或验证错误无法正确显示。因此，遵循这种参数顺序是非常重要的。

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

            // furnService.save(furn);
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
            /**
             *
             在 Spring Framework 中，当您在控制器（Controller）的方法上使用 @ResponseBody 注解时，
             它告诉 Spring 框架，您希望返回的数据直接写入 HTTP 响应体（Response Body），
             而不是解析为跳转路径或模板名称。这是 RESTful Web 服务的一个常见实践，
             用于返回 JSON 或 XML 等格式的数据。

             当您的方法返回一个 List<Monster> 并使用了 @ResponseBody 注解时，
             Spring 会自动使用内置的消息转换器（通常是 Jackson）将该列表转换为 JSON 格式。
             这个过程发生在以下几个步骤中：

             *返回数据类型识别：您的方法返回一个 List<Monster> 类型的数据。
             *使用 @ResponseBody：这个注解告诉 Spring，返回的对象需要被转换为响应体，而不是视图名称。
             *消息转换：Spring 使用消息转换器（默认是 Jackson JSON 转换器）将返回的 List<Monster>
             对象转换为 JSON 格式的字符串。
             *响应设置：转换后的 JSON 字符串被写入 HTTP 响应体，并且通常情况下，
             * 响应的 Content-Type 被设置为 application/json，表明响应的内容类型是 JSON。

             通过使用 @ResponseBody 注解和 Spring 的消息转换机制，
             返回的数据会被自动转换为相应格式的 JSON 字符串，
             然后发送给客户端。这使得创建返回 JSON 数据的 RESTful API 变得非常简洁和直接。
             */
            return Msg.success();
        }else { //有错误信息 验证失败了
            //校验失败 就把错误信息封装到Msg对象，并返回
            return Msg.fail().add("errorMsg",map);
        }



    }

    /**
     * 方法名由程序员指定 没有必要一定要叫initBinder
     * @param webDataBinder
     */
    //取消绑定 monster的name表单提交的值给monster.name属性
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        /**
         在默认情况下，表单提交的数据都会和 pojo 类型的 javabean 属性绑定，如果
         程序员在开发中，希望取消某个属性的绑定，也就是说，不希望接收到某个表单对应的属
         性的值，则可以通过 @InitBinder 注解取消绑定.
         1. 编写一个方法, 使用@InitBinder 标识的该方法，可以对 WebDataBinder 对象进行初始
         化。WebDataBinder 是 DataBinder 的子类，用于完成由表单字段到 JavaBean 属性的绑
         定
         2. @InitBinder 方法不能有返回值，它必须声明为 void。
         3. @InitBinder 方法的参数通常是是 WebDataBinder

         * 老师解读
         * 1. 方法上需要标注 @InitBinder  springmvc底层会初始化 WebDataBinder
         * 2. 调用 webDataBinder.setDisallowedFields("name") 表示取消指定属性的绑定
         *    即：当表单提交字段为 name时， 就不在把接收到的name值，填充到model数据monster的name属性
         * 3. 机制：springmvc 在底层通过反射调用目标方法时, 接收到http请求的参数和值,使用反射+注解技术
         *    取消对指定属性的填充
         * 4. setDisallowedFields支持可变参数，可以填写多个字段
         * 5. 如果我们取消某个属性绑定,验证就没有意义了,应当把验证的注解去掉, name属性会使用默认值null
         *  //@NotEmpty
         *  private String name;
         *
         */
        //webDataBinder.setDisallowedFields("name");
    }





}