package com.atguigu.jvm;

/**
 * 类加载器：启动类加载器、扩展类加载器、应用类加载器
 *
 *
 *
 * 类加载器分为四种：前三种为虚拟机自带的加载器。
 *
 * 启动类加载器（Bootstrap）C++
 * 负责加载$JAVA_HOME中jre/lib/**rt.jar**里所有的class，由C++实现，不是ClassLoader子类
 *
 * 扩展类加载器（Extension）Java
 * 负责加载java平台中扩展功能的一些jar包，包括$JAVA_HOME中jre/lib/*.jar或-Djava.ext.dirs指定目录下的jar包
 *
 * 应用程序类加载器（AppClassLoader）Java
 * 也叫系统类加载器，负责加载**classpath**中指定的jar包及目录中class
 *
 * 用户自定义加载器  Java.lang.ClassLoader的子类，用户可以定制类的加载方式
 */
public class ClassLoaderTest {
    public ClassLoaderTest() {
        super();
    }

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdb.Driver");

//        C:\Java\jdk8u371\bin\java.exe -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:55016,suspend=y,server=n -javaagent:D:\Server\ideaIU-2022.2.4.win\plugins\java\lib\rt\debugger- agent.jar -Dfile.encoding=UTF-8 -classpath C:\Java\jdk8u371\jre\lib\charsets.jar;C:\Java\jdk8u371\jre\lib\deploy.jar;C:\Java\jdk8u371\jre\lib\ext\access-bridge-64.
//        jar;C:\Java\jdk8u371\jre\lib\ext\cldrdata.jar;C:\Java\jdk8u371\jre\lib\ext\dnsns.jar;C:\Java\jdk8u371\jre\lib\ext\jaccess.jar;C:\Java\jdk8u371\jre\lib\ext\jfxrt.jar;C:\Java\jdk8u371\jre\lib\ext\localedata.jar;C:\Java\jdk8u371\jre\lib\ext\nashorn.jar;C:\Java\jdk8u371\jre\lib\ext\sunec.jar;C:\Java\jdk8u371\jre\lib\ext\sunjce_provider.jar;C:\Java\jdk8u371\jre\lib\ext\sunmscapi.jar;C:\Java\jdk8u371\jre\lib\ext\sunpkcs11.jar;C:\Java\jdk8u371\jre\lib\ext\zipfs.jar;C:\Java\jdk8u371\jre\lib\javaws.jar;C:\Java\jdk8u371\jre\lib\jce.jar;C:\Java\jdk8u371\jre\lib\jfr.jar;C:\Java\jdk8u371\jre\lib\jfxswt.jar;C:\Java\jdk8u371\jre\lib\jsse.jar;C:\Java\jdk8u371\jre\lib\management-agent.jar;C:\Java\jdk8u371\jre\lib\plugin.jar;C:\Java\jdk8u371\jre\lib\resources.jar;C:\Java\jdk8u371\jre\lib\rt.jar;D:\workspace0529\jvm2023\target\test-classes;D:\workspace0529\jvm2023\target\classes;D:\Server\ideaIU-2022.2.4.win\lib\idea_rt.jar com.atguigu.test.TestList
//        Connected to the target VM, address: '127.0.0.1:55016', transport: 'socket'
//        Exception in thread "main" java.lang.ClassNotFoundException: com.mysql.jdb.Driver
//        at java.net.URLClassLoader.findClass(URLClassLoader.java:387)
//        at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
//        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:355)
//        at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
//        at java.lang.Class.forName0(Native Method)
//        at java.lang.Class.forName(Class.java:264)
//        at com.atguigu.test.TestList.main(TestList.java:8)

        Object obj = new Object();
        String s = new String();
        ClassLoaderTest demo = new ClassLoaderTest();
        System.out.println(obj.getClass().getClassLoader()); //Object类在核心类库rt.jar中，启动类加载器获取不到， C++
        System.out.println(s.getClass().getClassLoader());   //String类在核心类库rt.jar中，启动类加载器获取不到， C++
        System.out.println(demo.getClass().getClassLoader().getParent().getParent()); ////启动类加载器获取不到， C++
        System.out.println(demo.getClass().getClassLoader().getParent()); //扩展类加载器
        System.out.println(demo.getClass().getClassLoader()); //应用类加载器
//        null
//        null
//        null
//        sun.misc.Launcher$ExtClassLoader@16f65612    //扩展类加载器
//        sun.misc.Launcher$AppClassLoader@18b4aac2    //应用类加载器
    }

}
