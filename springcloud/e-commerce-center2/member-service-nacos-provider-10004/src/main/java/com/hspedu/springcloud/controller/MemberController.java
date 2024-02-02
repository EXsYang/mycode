package com.hspedu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hspedu.springcloud.entity.Member;
import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.handler.CustomGlobalBlockHandler;
import com.hspedu.springcloud.handler.CustomGlobalFallbackHandler;
import com.hspedu.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yangda
 * @create 2023-12-29-14:25
 * @description:
 *
 *
 * @SentinelResource 注解
 * 注意：注解方式埋点不支持 private 方法。
 * @SentinelResource
 * @SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：
 *
 * value：资源名称，必需项（不能为空）
 * entryType：entry 类型，可选项（默认为 EntryType.OUT）
 * blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
 * fallback / fallbackClass：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
 * 返回值类型必须与原函数返回值类型一致；
 * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
 * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
 * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
 * 返回值类型必须与原函数返回值类型一致；
 * 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
 * defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
 * exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
 * 1.8.0 版本开始，defaultFallback 支持在类级别进行配置。
 *
 * 注：1.6.0 之前的版本 fallback 函数只针对降级异常（DegradeException）进行处理，不能针对业务异常进行处理。
 *
 * 特别地，若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出（若方法本身未定义 throws BlockException 则会被 JVM 包装一层 UndeclaredThrowableException）。
 */
@Slf4j
@RestController
public class MemberController {


    //执行的计数器-static静态的
    private static int num = 0;
    //装配memberService
    @Resource
    private MemberService memberService;

    /**
     * value = "t6" 表示sentinel限流资源的名称
     * blockHandlerClass = CustomGlobalBlockHandler.class :当出现限流时，要使用哪一个类来处理 全局限流处理类
     * blockHandler = "handlerMethod1": 指定使用全局限流处理类的哪个方法来处理限流信息
     * blockHandler只针对sentinel控制台配置违规 进行的限流
     *
     * fallbackClass = CustomGlobalFallbackHandler.class :当目标方法出现java异常时，要使用哪一个类来处理 全局fallback处理类
     * fallback负责对目标方法中出现的Java异常/业务异常 指定专门由全局fallback处理类哪个方法来 处理java异常/业务异常
     *
     * exceptionsToIgnore = {RuntimeException.class}: 表示如果t6抛出RuntimeException，就使用系统默认方式处理
     * ，而不再使用fallback指定的方法进行处理
     *
     * 因为NullPointerException是RuntimeException的子类
     * ，如果exceptionsToIgnore = {RuntimeException.class} 访问次数是5或者6的倍数时都会忽略
     */
    //这里我们使用全局限流处理类　显示限流信息，而不再使用原来的`Blocked by Sentinel (flow limiting)`
    //在Sentinel配置流控规则进行测试
    @GetMapping("/t6")
    @SentinelResource(value = "t6",
            blockHandlerClass = CustomGlobalBlockHandler.class,
            blockHandler = "handlerMethod1",
            fallbackClass = CustomGlobalFallbackHandler.class,
            fallback = "fallbackHandlerMethod1",
            exceptionsToIgnore = {NullPointerException.class})
    public Result t6() {
    // 如果这个方法改成了私有的，该方法也可以被访问到，只是就相当于@SentinelResource注解失效了
    // ，sentinel可以抓取到 /t6 ,
    // 但是sentinel控制台监控不到 @SentinelResource(value = "t6" 中定义的 "t6" 这个资源了
    // private Result t6() {


        //假定 访问t6资源的次数是5的倍数时，就出现一个Java异常/业务异常
        if (++num % 5 == 0) {

            throw new NullPointerException("访问次数是5的倍数 NullPointerException空指针异常 num= " + num);
        }

        if (num % 6 == 0) {
            throw new RuntimeException("访问次数是6的倍数 RuntimeException num的值异常 num= " + num);
        }


        log.info("执行t6() 线程id={}", Thread.currentThread().getId());

        return Result.success("200", "t6()执行OK！！！");
    }


    //完成对热点key限流的测试
    //http://localhost:10004/news?id=x&type=x

    /**
     * 1. @SentinelResource :指定sentinel限流资源
     * 2. value = "news" 表示限流资源的名称, 由程序员指定 不需要加斜杠！
     * 3. blockHandler = "newsBlockHandler", 当出现限流时，由newsBlockHandler方法进行处理
     * <p>
     * 参数id位于参数列表的第一个位置，对应设置sentinel热点规则时参数列表为0，热点规则参数列表从0开始
     * ，对应的后端String id 在 方法形参处于第一个参数的位置
     * <p>
     * 触发热点限流规则后走的是自定义的处理方法，而不再是原来的在页面显示 `Blocked by Sentinel (flow limiting)`
     * 因为后端给前端返回一个自定义的信息即可，比如状态码等，要显示什么界面由前端根据后端返回的状态码来决定即可
     */
    @GetMapping("/news")
    @SentinelResource(value = "news", blockHandler = "newsBlockHandler")
    public Result queryNews(@RequestParam(value = "id", required = false) String id,
                            @RequestParam(value = "type", required = false) String type) {

        // 在实际开发中，要到DB或者缓存中获取，这里模拟数据
        log.info("到 DB　查询新闻");
        return Result.success("返回id=" + id + " 新闻 from DB");
    }


    //热点key限制/限流异常处理方法
    //如果访问 http://localhost:10004/news?id=x&type=x 超出了规定的 QPS
    // , 触发热点限流机制, 调用自定义的方法newsBlockHandler()，给出提示信息.
    public Result newsBlockHandler(String id, String type, BlockException blockException) {

        return Result.success("查询id=" + id + " 新闻 触发热点key限流保护 sorry");
    }


    /**
     * 熔断策略：Sentinel 提供多种熔断策略，如慢调用比例、异常比例和异常数，每种策略都有其特定的参数和触发条件。
     * <p>
     * 熔断后的半开状态： 在熔断时间结束后，熔断器进入半开状态。当熔断器触发后，它会进入一个“开”状态，暂时阻止请求流通过，以避免对不稳定的服务施加进一步的压力。熔断时间结束后，熔断器进入“半开”状态。
     * <p>
     * 半开状态的作用：在半开状态下，Sentinel 允许有限数量的请求通过。这些请求的表现（例如响应时间、是否出现错误等）用于评估服务的当前稳定性。
     * <p>
     * 恢复或再次熔断：如果在半开状态下的请求表现良好，熔断器会关闭，恢复到正常状态，允许流量正常通过。如果这些请求表现不佳，熔断器将再次进入开启状态，继续阻止流量，直到下一个评估周期。
     * <p>
     * 这一过程是微服务架构中常用的一种故障恢复机制，旨在在服务不稳定或出现问题时，快速响应以保护系统的整体稳定性，并在问题解决后安全地恢复正常运行。
     */
    //测试熔断降级-异常数
    @GetMapping("/t5")
    public Result t5() {

        // 出现十次异常，这里需要设计异常数大于6，需要加入簇点链路和留出几次做测试
        if (++num <= 10) {
            //制造一个异常
            System.out.println(3 / 0);
        }

        log.info("熔断降级测试[异常比例] 执行t5() 线程id={}", Thread.currentThread().getId());

        return Result.success("t5() 执行...");
    }


    //测试熔断降级-异常比例
    @GetMapping("/t4")
    public Result t4() {
        //设计异常比例达到50%
        if (++num % 2 == 0) {
            //制造一个异常
            System.out.println(3 / 0);
        }

        log.info("熔断降级测试[异常比例] 执行t4() 线程id={}", Thread.currentThread().getId());

        return Result.success("t4() 执行...");
    }


    //测试流量控制规则-关联 当关联的资源达到阈值时，就限流自己
    // 实例-关联 当调用 member-service-nacos-provider-10004 的 /t2 API 接口时，如果 QPS 超过 1，这
    // 时调用 /t1 API 接口 直接接失败，抛异常. 老师梳理 /t2 是关联的资源 , 限流的资源是 /t1
    // localhost:10004/t1
    @GetMapping("/t1")
    public Result t1() {
        return Result.success("t1()执行...");
    }

    //http://localhost:10004/t2
    @GetMapping("/t2")
    public Result t2() {
        //排队等待∶匀速排队，让请求以匀速的速度通过，阈值类型必须设置为 QPS，否则无效
        // 1调用 member-service-nacos-provider-10004 的 /t2 API 接口，将 QPS 设置为 1
        // 2. 当调用 /t2 的 QPS 超过 1 时，不拒绝请求，而是排队等待, 依次执行
        // 3. 当等待时间超过 10 秒，则为等待超时
        //让线程休眠一秒钟，模拟执行时间为一秒 =》当多个请求就会造成超时
        //流量控制-排队 当设置超时时间为10000毫秒，第十个请求就会被限流
        //因为这里休眠就用了一秒钟，第九个请求走完肯定就大于九秒种了
        //当执行第十个请求就会被限流
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("执行t2() 线程id={} 当前时间={}", Thread.currentThread().getId(), new Date());
        return Result.success("t2()执行...");
    }

    //http://localhost:10004/t3

    /**
     * 请参考https://sentinelguard.io/zh-cn/docs/circuit-breaking.html说明，截图[D:\TyporaPhoto\image-20240106210224201.png]中的比例阈值是什么意思？
     * [GPT]WebPilot
     * <p>
     * Talked to gpts.webpilot.ai
     * <p>
     * 在 Sentinel 的熔断降级规则中，"慢调用比例阈值"（slowRatioThreshold）是指在慢调用比例（SLOW_REQUEST_RATIO）模式下生效的一个参数。这个阈值是用来定义何时触发熔断的条件。具体来说：
     * <p>
     * - 当请求的响应时间大于设定的慢调用响应时间（RT）时，该请求会被统计为慢调用。
     * - 如果在一个统计时间窗口内（由 `statIntervalMs` 参数定义），请求总数超过了设定的最小请求量（由 `minRequestAmount` 参数定义），并且慢调用的比例超过了设定的慢调用比例阈值，则会触发熔断。
     * - 一旦熔断被触发，系统会在接下来的熔断时间窗口内（由 `timeWindow` 参数定义）自动拒绝所有请求，以防止不稳定的服务导致系统资源耗尽。
     * <p>
     * 例如，如果慢调用比例阈值设定为0.5（或者50%），那么在统计时间内，如果慢调用的请求超过了50%的请求总数，熔断器将会触发。
     * <p>
     * 在您提供的截图中，"比例阈值"的输入框被设置为0，这个值在实际配置中需要根据实际情况进行调整，以便正确地触发熔断保护机制。
     * <p>
     * You
     * <p>
     * "比例阈值"设置为0是什么意思？
     * <p>
     * [GPT]WebPilot
     * <p>
     * 在Sentinel的熔断规则中设置"比例阈值"为0意味着，只要有请求的响应时间超过了设定的慢调用响应时间（RT），不管这样的请求占比多少，即便是一个单一的请求，也会触发熔断机制。这是一个非常严格的配置，它设置了一个非常低的容错率，因为任何慢请求都会导致熔断。这种设置可能适用于对响应时间极其敏感的场景，但在大多数情况下，可能会由于过于敏感而导致不必要的熔断，因此通常需要根据具体的业务需求和性能指标来更合理地设置这个阈值。
     */
    // 测试熔断降级-慢调用比例
    @GetMapping("/t3")
    public Result t3() {

        //让线程休眠300毫秒，模拟执行时间
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Result.success("t3()执行...");
    }


    /**
     * 老师解读
     *
     * @RequestBody作用
     * @RequestBody的作用 第1个功能
     * 1. @RequestBody User user 在形参指定了 @RequestBody
     * 2. springmvc就会将提交的json字符串数据填充给指定Javabean
     * @RequestBody注解,用来接收json格式数据 1.我们的前端如果是以json格式来发送添加信息furn, 那么我们后端需要使用@RequestBody注解
     * ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
     * 2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data
     * @RequestBody第2个功能 是整体取出 Post 请求内容！ 即获取post请求体
     * 注意:1.如果前端使用`Postman的Post方式提交数据，同时指定params提交`
     * ，在不使用@RequestBody的情况下，即形参位置没有使用任何注解，
     * 也可以将params正常的封装到下面方法形参的User对象中
     * --下面这种方式接收参数是接收的表单提交的数据,完成自动封装
     * 在springbootweb搜‘自定义参数封装’ 接收参数相关注解 自定义对象参数-自动封装时讲过
     * 下面这种方式接收参数 2.如果前端是在`地址栏的参数位置`提交的数据,如:Post请求 http://localhost:8080/save?userName=jack&age=22
     * 也可以完成封装--
     * 即：如果前端是以表单形式/或者是以parameters提交了,则不需要@RequestBody,才会进行对象封装，
     * 同时保证http的请求头的content-type是对应的
     * <p>
     * 如果前端是以json格式提交的数据，但是目标方法形参没有使用@RequestBody注解，在这个项目中也可以添加成功
     * ，只是表除了自增长的字段之外的各个字段的值为null [ MemberController save() 形参member=Member(id=null, name=null, pwd=null, mobile=null, email=null, gender=null) ]
     * 原因是创建数据库表`member`时，除了id字段都可以为null值，这里又指定了，id为自增长的，所以可以添加成功，
     * 即使在这里member对象的属性id=null,也可以添加成功, 因为设置了
     * {
     * "name": "老鼠精",
     * "pwd": "22",
     * "mobile": "122222222",
     * "email": "lsj@sohu.com",
     * "gender": 0
     * }
     * //@RequestBody注解,用来接收json格式数据
     * //1.我们的前端如果是以json格式来发送添加信息furn,那么我们后端需要使用@RequestBody注解
     * //  ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
     * //2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data
     */
    //添加会员方法/接口
    @PostMapping("member/save")
    public Result save(@RequestBody Member member) {
        log.info("service-provider member={}", member);
        // System.out.println("MemberController save() 形参member=" + member);
        int affected = memberService.save(member);

        if (affected > 0) {//添加会员成功
            System.out.println("member-service-nacos-provider-10004 添加会员成功后，member对象主键id的值= " + member.getId());
            // 添加会员成功后，member对象主键id的值= 13
            return Result.success("添加会员成功 member-service-nacos-provider-10004", affected);
        } else {
            return Result.error("401", "添加会员失败");
        }

    }

    //查询会员方法/接口
    //这里使用url占位符+@PathVariable
    @GetMapping("/member/get/{id}")
    // public Result getMemberById(@PathVariable("id") Long id, HttpServletRequest request) {
    public Result getMemberById(@PathVariable("id") Long id) {
        // @GetMapping("/member/get")
        // public Result getMemberById(Long id, HttpServletRequest request) {
        // @GetMapping(value = "/member/get",params = "id")
        // public Result getMemberById(Long id, HttpServletRequest request) {

        // String color = request.getParameter("color");
        // String address = request.getParameter("address");

        //模拟超时,1s
        // try {
        //     // TimeUnit.MILLISECONDS.sleep(1000);
        //
        //     //10004 服务对应的 API 执行时间很长(比如休眠 2 秒), 10006不休眠 这
        //     // 时 openfeign 去调用会怎么样? openfeign 会总是优先调用10006微服务的方法
        //     TimeUnit.MILLISECONDS.sleep(2000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }


        //输出线程情况
        log.info("enter 10004 getMemberById 当前线程id={} 时间={}"
                , Thread.currentThread().getId(), new Date());

        log.info("service-provider id={}", id);
        Member member = memberService.queryMemberById(id);

        //使用Result将查询的结果返回
        if (member != null) {
            // 获取member成功
            // return Result.success("通过id获取member成功 member-service-nacos-provider-10004 " + color + " " + address, member);
            return Result.success("通过id获取member成功 member-service-nacos-provider-10004", member);
        } else {
            return Result.error("402", "通过id=" + id + "获取member失败");
        }

    }
}
