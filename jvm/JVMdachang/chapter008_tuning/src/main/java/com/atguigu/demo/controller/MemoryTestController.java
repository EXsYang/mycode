package com.atguigu.demo.controller;

import com.atguigu.demo.bean.People;
import com.atguigu.demo.service.PeopleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *    @author  : shkstart
 *    email   : shkstart@126.com
 *    time    : 08:54
 *    desc    : 内存测试
 *    version : v1.0
 * </pre>
 */
@RestController
public class MemoryTestController {
    @Autowired
    private PeopleSevice peopleSevice;

    /**
     * 案例1：模拟线上环境OOM
     */
    @RequestMapping("/add")
    public void addObject(){
        System.err.println("add"+peopleSevice);
        ArrayList<People> people = new ArrayList<>();
        while (true){
            people.add(new People());
        }
    }


    /**
     * 案例2:模拟元空间OOM溢出
     */
    @RequestMapping("/metaSpaceOom")
    public void metaSpaceOom(){
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(People.class);
//            enhancer.setUseCache(false);
            enhancer.setUseCache(true);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println("我是加强类，输出print之前的加强方法");
                return methodProxy.invokeSuper(o,objects);
            });
            People people = (People)enhancer.create();
            people.print();
            System.out.println(people.getClass());
            System.out.println("totalClass:" + classLoadingMXBean.getTotalLoadedClassCount());
            System.out.println("activeClass:" + classLoadingMXBean.getLoadedClassCount());
            System.out.println("unloadedClass:" + classLoadingMXBean.getUnloadedClassCount());
        }
    }

    /**
     * 性能优化案例3：合理配置堆内存
     *
     * 需要使用到的测试指令：
     * jps
     * jstat -gc PID 1000 5      说明--> 1000ms打印5次gc统计信息
     * jmap -histo:live <pid>    打印每个class的实例数目,内存占用,类全名信息.live子参数加上后,只统计活的对象数量. 此时会触发FullGC
     * jmap -heap PID            输出整个堆空间的详细信息，包括GC的使用、堆配置信息，以及内存的使用信息等
     *
     *
     * 优化方案如下：
     * 默认是给了1024M的堆区大小，即 -Xms1024M -Xmx1024M
     * 使用 `jmap -histo:live <pid>`指令来强制触发Full GC 比如执行3次该指令。
     * 然后使用 `jmap -heap PID`指令，来观察Old区的使用情况，
     * 根据used的内存的大小进行调整整个堆区的大小为80M。
     *
     * 再执行5w次压测接口后(即模拟线上正常运行是的数据情况,总不能是空数据/初始数据吧),
     * 内存中生成的对象, 再强制执行3次Full GC【模拟线程出现Full GC的情况】后在Old区存活的对象如下:
     * 关于执行执行3次Full GC的说明：
     * 作用是用来计算老年代存活对象，为了快速看数据，我们使用了方式2，通过命令 jmap -histo:live pid 产生几次FullGC，FullGC之后，使用的jmap -heap 来看的当前的堆内存情况。
     * 当然最稳妥的方式是：JVM参数中添加GC日志，GC日志中会记录每次FullGC之后各代的内存大小，观察老年代GC之后的空间大小。可观察一段时间内（比如2天）的FullGC之后的内存情况，根据多次的FullGC之后的老年代的空间大小数据来预估FullGC之后老年代的存活对象大小（可根据多次FullGC之后的内存大小取平均值）。
     * 即求多次Full GC，Old区存活对象(used的空间的大小)的平均值。 每次FullGC后都会有一个used的Old区大小，求平均值。
     * PS Old Generation
     *    capacity = 716177408 (683.0MB)
     *    used     = 11623256 (11.084800720214844MB)
     *
     *
     * PS Old Generation
     *    capacity = 716177408 (683.0MB)
     *    used     = 22099456 (21.0848MB)
     *    free     = 704554152 (671.9151992797852MB)
     *    1.6229576457122759% used
     *
     *
     * 根据 used     = 11623256 (21.084800720214844MB) ,
     * 来分配堆区的总大小为该数值的3~4倍,即60M~80M
     *
     *
     *
     *
     *
     *
     *
     *
     */
    @RequestMapping("/getData")
    public List<People> getProduct(){
        List<People> peopleList = peopleSevice.getPeopleList();
        return peopleList;
    }
}
