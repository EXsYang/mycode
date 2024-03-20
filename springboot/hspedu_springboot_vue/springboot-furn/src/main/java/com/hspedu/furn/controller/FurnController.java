package com.hspedu.furn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.service.FurnService;
import com.hspedu.furn.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangda
 * @create 2023-12-22-21:11
 * @description 1.因为当前项目是前后端分离的，在默认情况下，前端发出请求
 * 2.后端 返回json数据，为了方便，我们就在类上使用@RestController
 */
@RestController
@Slf4j
public class FurnController {

    //装配Service
    @Resource
    private FurnService furnService;

    //编写方法完成添加
    //@RequestBody注解,用来接收json格式数据
    //1.我们的前端如果是以json格式来发送添加信息furn,那么我们后端需要使用@RequestBody注解
    //  ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
    //2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data

    /**
     * 加入 hibernate-validator 后端验证 ,使用@Validated标注形参 进行验证
     * 如果出现错误 springboot底层会把校验的错误信息会放入到errors
     */
    @PostMapping("/save")
    // public Result save(@RequestBody Furn furn) {
    public Result save(@Validated @RequestBody Furn furn, Errors errors) {
        // 下面这种方式接收参数是接收的表单提交的数据,完成自动封装
        // 在springboot 接收参数相关注解 自定义对象参数-自动封装时讲过
        // 下面这种方式接收参数 如果前端是在地址栏的参数位置提交的数据,如:Post请求 http://localhost:9090/save?id=101&name=喜喜台灯&maker=xx之家&price=30.3&sales=3&stock=200
        // 也可以完成封装
        // public Result save(Furn furn){

        // log.info("furn={}", furn);
        // // 注意下面的方法如果 前端传过来的 furn=Furn(id=null, name=null, maker=null, price=2.0, sales=2, stock=2)
        // // 其中id=null没关系因为在Furn类中使用了@TableId: 表主键标识
        // // 但是name=null就会报错，因为这个字段如果为空，相当于底层在处理sql语句的自动生成时
        // // 没有生成属性为空字段的条件, 在对应的没有指定值也不行，创建表时规定了除了主键之外的字段有约束，同时没有指定默认值 Field 'name' doesn't have a default value
        // // ,因此插入数据时name必须指定
        //
        // furnService.save(furn);
        // return Result.success();//返回成功信息,不需要携带数据

        //---------------------加入后端验证后端代码如下------------------------
        //定义map,准备把errors中的校验错误放入到map,如果有错误
        //就不真正添加，并且将错误信息通过map返回给客户端-客户端就可以取出显示
        HashMap<String, Object> map = new HashMap<>();

        List<FieldError> fieldErrors = errors.getFieldErrors();
        //遍历将错误信息放入到 map ,可能有也可能没有错误
        for (FieldError fieldError : fieldErrors) {
            //fieldError.getDefaultMessage() 就是在Furn中配置的错误信息  @NotNull(message = "请输入数字")
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        if (map.isEmpty()){ //说明没有后端校验错误
            //进行真正的添加家居
            log.info("furn={}", furn);
            furnService.save(furn);
            return Result.success();//返回成功信息,不需要携带数据
        }else{

            return Result.error("400","后端校验未通过",map);
        }


    }

    //返回所有的家居信息
    @GetMapping("/furns")
    public Result listFurns() {
        List<Furn> furns = furnService.list();
        return Result.success(furns);
    }


    //修改家居

    /**
     * 1.@PutMapping 使用Rest风格，因为这里是修改的请求，使用put请求
     * 2.@RequestBody: 表示前端/客户端 发送的数据是以json格式来发送的
     */
    @PutMapping("/update")
    public Result updateFurn(@RequestBody Furn furn) {
        //这个updateById是mybatis-plus提供的
        furnService.updateById(furn);
        //如果不报错，说明更新/修改成功了 返回
        return Result.success();

    }

    /**
     * 处理删除
     * 使用url占位符+@PathVariable路径变量 配合使用 => springmvc讲过
     * 使用rest风格 del方式发出数据
     */
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Integer id) {

        //说明 removeById() 是Mybatis-Plus提供的
        furnService.removeById(id);

        return Result.success();


    }

    /**
     * 根据id返回furn对象
     * 使用url占位符+@PathVariable路径变量 配合使用 => springmvc讲过
     * 使用rest风格 del方式发出数据
     */
    @GetMapping("/find/{id}")
    public Result findFurnById(@PathVariable Integer id) {

        //说明 removeById() 是Mybatis-Plus提供的
        Furn furn = furnService.getById(id);

        //让当前线程休眠2秒
        // Thread.sleep(2000);

        return Result.success(furn);
    }


    //分页查询的接口/方法 -不带条件的分页
    /**
     * @param pageNum  显示第几页 默认为1
     * @param pageSize 每页显示几条记录 默认为5
     * @return
     */
    @GetMapping("/furnByPage")
    public Result listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize) {

        System.out.println("pageNum=" + pageNum);
        System.out.println("pageSize=" + pageSize);

        //这里通过page方法,返回page对象,对象中就封装了分页数据
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        /*
        -- 分页查询!!!
        # LIMIT begin,size; begin从0开始 0对应第一条数据 size 每页的数据条数
        # 如果只写了一个参数 那么等价于 LIMIT０,？; 写的这一个参数？代表的是第二个参数size的值，第一个参数默认是０
         */
        //这里底层是真的进行了分页查询的，而不是取出所有的数据 进行截取的
        // SELECT COUNT(*) FROM furn
        // SELECT id,name,maker,price,sales,stock FROM furn LIMIT ?

        //这里要注意观察，返回的page数据结构是怎样的，这样才能知道在前端如何绑定数据
        //records: 记录
        return Result.success(page);

    }


    //分页查询的接口/方法 -带条件的分页
    //Condition:条件
    @GetMapping("/furnsBySearchPage")
    public Result listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam(defaultValue = "") String search){

        /*
        `QueryWrapper` 类是 MyBatis Plus 框架中的一个功能类，它并不是直接与分页插件 `PaginationInnerInterceptor` 关联的。`QueryWrapper` 主要用于构建数据库查询条件，它提供了一种链式的方式来构建查询语句，使得操作数据库时的代码更加简洁易读。
        `PaginationInnerInterceptor` 是 MyBatis Plus 中用于分页的拦截器。它的主要作用是拦截查询操作，自动解析分页参数，并对 SQL 语句进行修改，以实现分页功能。虽然 `QueryWrapper` 可以与 `PaginationInnerInterceptor` 一起使用来实现分页查询的需求，但它们属于 MyBatis Plus 中不同的功能模块。
        简而言之，`QueryWrapper` 不是 `PaginationInnerInterceptor` 带的，而是作为构建查询条件的工具，它可以单独使用或与分页拦截器等其他组件一起使用以满足不同的业务需求。
         */

        //先创建QueryWrapper，可以将我们的检索条件封装到QueryWrapper对象
        QueryWrapper<Furn> queryWrapper = Wrappers.query();
        //判断search是否有内容
        if (StringUtils.hasText(search)){
            //queryWrapper.like("name",search);形参第一个位置"name" 是和数据库表的字段匹配 第二个位置是检索条件
            // 使用 queryWrapper.like 构建一个模糊查询条件。这里的 "name" 是数据库表中的字段名，
            // 它应该与表中实际的列名相匹配。"search" 是用户输入的检索条件，用于匹配 "name" 字段中的内容。
            // 例如，如果 "search" 的值为 "Chair"，则此查询将寻找所有 "name" 字段包含 "Chair" 的记录。
            queryWrapper.like("name",search);
            /*
                第一个参数 "name" 是用于指定数据库中用于匹配的字段，
                而第二个参数 search 是用户提供的用于模糊匹配的文本值。
                这行代码将生成一个 SQL WHERE 子句，类似于 WHERE name LIKE '%search%'，
                用于在数据库查询中执行模糊匹配。
             */
        }


        //如果带条件就按照条件进行分页，如果不带条件就是正常的分页查询
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);

        return Result.success(page);


    }

    //分页查询的接口/方法 -带条件的分页
    //Condition:条件
    //编写方法 使用LambdaQueryWrapper封装查询条件完成检索
    @GetMapping("/furnsBySearchPage2")
    public Result listFurnsByConditionPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam(defaultValue = "") String search){

        //说明：关于lambda表达式，我们这里使用的是 类名::实例方法    通过类名引用的实例方法，当然也可以引用静态方法
        //是Lambda方法引用中一个不太容易理解的知识点
        //后面我们使用到每个Lambda表达式的时候，老韩就会有针对性进行讲解这样理解的就非常深刻
        // 1老韩的心得体会：多用几次，就熟悉了，不用背
        //1. https://baijiahao.baidu.com/s?id=1652786021461159890&wfr=spider&for=pc
        //2. https://blog.csdn.net/hjl21/article/details/102702934
        //老师解读
        //1、Furn::getName 是通过 Lambda 表达式 引用实例方法 getName
        //   这里就是把 Furn::getName 赋给了 SFunction<T, R> 函数式接口
        //2. SFunction<Furn, Object> 是一个函数式接口, 什么是函数式接口,老师一会说明.
        //3. 这个函数式接口的源码是这样的
        /**
         * @FunctionalInterface
         * public interface SFunction<T, R> extends Function<T, R>, Serializable {
         * }
         *
         * 父接口
         * @FunctionalInterface
         * public interface Function<T, R> {
         * R apply(T t); //这个抽象方法表示根据类型 T 的参数获取类型 R 的结果
         * //另外就是一些默认实现的方法... * }
         *
         * 4. 传入 Furn::getName 后, 就相当于实现了 SFunction<T, R> 的 apply 方法
         * 5. 底层会通过你传入的 Furn::getName 去得到该方法对应的属性映射的表字段，可以更加灵活，
         *  ,比如表的字段修改了 namex，只需要修改xml文件中的映射column="namex"的值，而不用修改Java代码
         *  queryWrapper.like("namex",search); 等处的代码，因为Java代码牵一发而动全身
         *  ，需要改许多地方 涉及到重新发布重新编译的问题，而改xml文件代价小得多
         * 也就是不再是前面的硬编码来指定 queryWrapper.like("name", search);
         * 请小伙伴回忆一下 mybatis 我们讲过的 ResultMap 会体现Bean属性和表的字段映射关系
         * , 找一个 Xxx.mapper 文件就非常清楚了
         * <resultMap id="resultHusbandMap" type="Husband">
         *         <id property="id" column="id"/> 找到property="id" 再根据映射关系获取 column="id" 就简单了
         *         <result property="name" column="name"/>
         *
         */

        /**
         * 在使用 MyBatis Plus 时，`@TableName` 和 `@TableField` 注解用于指定实体类与数据库中的表及字段的对应关系。不过，这些注解并不是强制性的。如果不使用这些注解，MyBatis Plus 会采取一些默认的规则来映射实体类与数据库表及其字段：
         *
         * 1. **默认的表名映射**：如果不使用 `@TableName` 注解，MyBatis Plus 默认将实体类的名称按照驼峰命名法转换成下划线命名法来与数据库表名进行匹配。例如，实体类名 `Furn` 默认对应于数据库中的 `furn` 表。如果你的数据库表名确实是 `furn`，那么即使不使用 `@TableName` 注解，映射也可以正常工作。
         *
         * 2. **默认的字段映射**：类似地，如果不使用 `@TableField` 注解，MyBatis Plus 会将实体类属性按照驼峰命名法转换为下划线命名法来匹配数据库中的字段名。例如，实体类属性 `userName` 默认映射到数据库字段 `user_name`。因此，如果你的实体类属性和数据库字段正好符合这种命名规则，那么即使不使用 `@TableField` 注解，字段映射也能正确进行。
         *
         * 由于你提供的 `Furn` 类的属性名（如 `id`, `name`, `maker`, `price`, `sales`, `stock`）看起来是符合通常的命名习惯的，并且如果数据库表 `furn` 的字段也遵循相同的命名规则（如 `id`, `name`, `maker`, `price`, `sales`, `stock`），那么即使没有明确使用 `@TableName` 或 `@TableField` 注解，MyBatis Plus 也能够正确地映射实体类和数据库表及其字段。
         *
         * 这就是为什么在某些情况下，即使没有写 `@TableName`、`@TableField` 等注解，代码仍然可以正常工作的原因。不过，如果数据库的表名或字段名与实体类的名称或属性不完全匹配，那么就需要使用这些注解来明确指定映射关系了。
         */

        /**
         * 当你在使用 MyBatis 或 MyBatis Plus 时，确实有不同的方式来映射 Java 对象的属性和数据库表的字段：
         *
         * 1. **在 MyBatis 中**，`ResultMap` 是常用来定义 Java 对象属性和数据库表字段之间映射关系的配置。在你的 `XxxMapper.xml` 文件中，通过 `<resultMap>` 元素可以详细指定哪个字段映射到哪个属性。如果你在 XML 映射文件中定义了这样的映射，即使在 Java 实体类中没有使用 `@TableField` 或 `@TableName` 注解，映射也仍然可以工作，因为 MyBatis 根据 XML 文件中的配置进行操作。
         *
         * 2. **在 MyBatis Plus 中**，情况稍有不同。虽然 MyBatis Plus 支持和继承了 MyBatis 的很多特性，但它更推荐使用注解（如 `@TableName` 和 `@TableField`）来声明映射关系。尽管如此，如果你不使用这些注解，MyBatis Plus 会默认使用实体类名和属性名（通过驼峰转下划线的规则）来匹配数据库中的表名和字段名。但是，MyBatis Plus 不使用 XML 文件中的 `<resultMap>` 配置来定义映射关系。它主要通过实体类和其注解来处理映射。
         *
         *    对于表达式 `Furn::getName` 和 `lambdaQueryWrapper.like(sf, search)` 的情况，MyBatis Plus 使用的是 Java 8 的类型引用（也称为方法引用）和 Lambda 表达式。这里，`Furn::getName` 被解析为一个属性到字段的映射（通过内部机制，不通过 XML 映射），它依赖于 MyBatis Plus 能够理解的实体类属性名。如果你没有使用 `@TableField` 来指定字段名，那么就假定属性名转换规则（驼峰转下划线）适用。
         *
         * 总的来说，如果在使用 MyBatis Plus 且希望通过 `lambdaQueryWrapper.like(sf, search);` 这样的逻辑进行操作，你通常不需要在 Mapper XML 文件中配置 `ResultMap`。而是直接通过实体类和相关的注解来控制映射关系。如果字段名和实体属性名符合默认的命名转换规则（或者通过注解指定），这种方式可以正常工作。如果你已经习惯了 MyBatis 的 `ResultMap` 并且在使用纯 MyBatis，那么 XML 映射文件中的配置是必要的。但对于 MyBatis Plus，推荐使用注解来定义映射关系，以充分利用它提供的功能和简化开发流程。
         */


        //先创建LambdaQueryWrapper，封装检索条件
        LambdaQueryWrapper<Furn> lambdaQueryWrapper = Wrappers.<Furn>lambdaQuery();

        //判断search是否有内容
        if (StringUtils.hasText(search)){

            // lambdaQueryWrapper.like(Furn::getName,search);
            //6. 改写上面的方式,可能小伙伴会清楚一些.
            // 说明: 因为 SFunction<Furn, Object> 有一个抽象方法
            // R apply(T t); //这个方法表示根据类型 T 的参数获取类型 R 的结果 , 而
            /**
             * 在 MyBatis Plus 中，表达式 `SFunction<Furn, Object> sf = Furn::getName;` 实际上是使用 Java 8 的方法引用特性来引用 `Furn` 类的 `getName` 方法，而不是直接调用该方法。这种语法不是获取 `Furn` 对象的 `name` 属性的值，而是用于指代 `Furn` 类的 `name` 属性本身。
             *
             * 当这个表达式用于 `lambdaQueryWrapper.like(sf, search);` 中时，MyBatis Plus 内部机制会将这个方法引用转换为对应的数据库字段名。这个转换是基于你的实体类和数据库之间的映射关系，通常是实体类属性名到数据库字段的映射（按照约定，如驼峰命名到下划线命名的转换），而不是获取属性的实际值。
             *
             * 所以，这里的 `sf` 代表的是一个函数式接口，它指向 `Furn` 类的 `getName` 属性名，用于构造查询条件。这使得查询条件的构建变得类型安全，因为如果 `Furn::getName` 引用的属性名在编译时被重命名，代码会产生编译错误，这样可以避免运行时的错误。
             *
             * 总结来说，`SFunction<Furn, Object> sf = Furn::getName; lambdaQueryWrapper.like(sf, search);` 这段代码是利用 `Furn` 类的 `getName` 方法引用来构建一个对应于 `name` 字段的模糊查询条件，而不是获取某个具体 `Furn` 实例的 `name` 属性的值。
             */
            SFunction<Furn, Object> sf = Furn::getName;
            lambdaQueryWrapper.like(sf, search);
        }

        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);

        //page.getRecords() 可以拿到分页数据
        log.info("page={}",page.getRecords());

        return Result.success(page);


    }
}
