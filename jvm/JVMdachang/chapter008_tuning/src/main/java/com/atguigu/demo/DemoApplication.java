package com.atguigu.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 性能优化案例3：合理配置堆内存
 *
 * Java整个堆大小设置，Xmx 和 Xms设置为老年代存活对象的3-4倍，即FullGC之后的老年代内存占用的3-4倍。
 * 方法区（永久代 PermSize和MaxPermSize 或 元空间 MetaspaceSize 和 MaxMetaspaceSize）设置为老年代存活对象的1.2-1.5倍。
 * 年轻代Xmn的设置为老年代存活对象的1-1.5倍。
 * 老年代的内存大小设置为老年代存活对象的2-3倍。
 *
 * 
 * 现在我们通过idea启动springboot工程，我们将内存初始化为1024M。
 * 我们这里就从1024M的内存开始分析我们的GC日志，根据我们上面的一些知识来进行一个合理的内存设置。
 *
 * JVM设置如下：
 * -XX:+PrintGCDetails -XX:MetaspaceSize=64m -Xss512K -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap/heapdump3.hprof  -XX:SurvivorRatio=8  -XX:+PrintGCDateStamps  -Xms1024M  -Xmx1024M -Xloggc:log/gc-oom3.log
 *
 */
@SpringBootApplication
@MapperScan("com.atguigu.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
