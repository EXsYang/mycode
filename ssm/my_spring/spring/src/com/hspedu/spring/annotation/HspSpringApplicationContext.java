package com.hspedu.spring.annotation;

import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.hspapplicationcontext.HspApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: HspSpringApplicationContext 类的作用类似Spring原生ioc容器
 * @create 2023-09-03-19:20
 */
public class HspSpringApplicationContext {

    private final ConcurrentHashMap<String, Object> ioc = new ConcurrentHashMap<>();
    // 用于接收 传进来的配置文件HspSpringConfig的class对象 便于使用反射 解析到它的注解
    // 一个类底层只要是做反射 通常都要拿到这个类的Class类型 如果不拿到这个类的Class类型无法做反射！！
    //ioc我存放的就是通过反射创建的对象(基于注解方式)
    private Class configClass;

    //构造器
    public HspSpringApplicationContext(Class configClass) {
        // 便于跨方法使用configClass对象
        this.configClass = configClass;
        //System.out.println("this.configClass= " + this.configClass);

        //获取要扫描的包
        //1. 先得到HspSpringConfig配置的的@ComponentScan(value = "com.hspedu.spring.component")
        ComponentScan componentScan = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);

        //2. 通过componentScan的value=> 即要扫描的包
        // 这个value() 就是注解中定义的 String value() default "";
        String path = componentScan.value();
        System.out.println("要扫描的包=" + path);//要扫描的包=com.hspedu.spring.component

        // 得到要扫描的包下的所有的资源(类 .class文件)
        // 1.得到类的加载器(App 类加载器) 使用任何一个类的.class 都可以得到类的加载器
        ClassLoader classLoader = HspApplicationContext.class.getClassLoader();

        //在 path 中，将 所有的 . 替换成/
        // 老韩解读: s1.replace() 方法执行后，返回的结果才是替换过的. // 注意对 s1 没有任何影响
        //String replacePath = path.replace(".", "/");
        path = path.replace(".", "/");

        // 2.通过类的加载器获取到要扫描的包的资源 url
        // classLoader.getResource("com/hspedu/spring/component"); 默认是按照斜杠 / 来间隔各级文件目录的
        // 但是我们这里是按照点 . 来间隔的 因此需要替换一下 将.替换为/

        // 这里为什么要用类的加载器的.getResource(path); 而不是直接使用Class.getResource(path);
        // Class.getResource(path); src 对应  out下的spring  目录直接加载的
        // 配置@ComponentScan(value = "com.hspedu.spring.component")时 是按照
        // "com.hspedu.spring.component" 来配置的 使用classLoader.getResource() 不需要在
        // 最前面补充一个斜杠/
        // 使用Class.getResource(path)前面需要带斜杠！
        // 使用classLoader.getResource(path)前面不能带斜杠！


        //URL resource = classLoader.getResource("com/hspedu/spring/component");
        URL resource = classLoader.getResource(path);
        /*
         * 下面返回为null 在这里 即不可以写绝对路径 也不可写/   在手写hsptomcat 时 写的是
         * String path = HspTomcatV3.class.getResource("/").getPath();// 得到的是工作目录，而不是源码目录
         * System.out.println("path= " + path);
         * 这里的区别是
         * 手写tomcat时 是用 class对象..getResource() 返回的是String字符串对象
         * 而这里使用的是 类加载器.getResource() 返回的是URL对象
         * */
        //URL resource2 = classLoader.getResource("/");
        System.out.println("resource= " + resource);
        //System.out.println("resource2= " + resource2);
        //url= file:/D:/Java_developer_tools/ssm/my_spring/spring/out/production/spring/com/hspedu/spring/component

        // 3.将要加载的资源(.class) 路径下的文件进行遍历=> io
       /* resource.getPath()说明：
            public String getPath()
            返回值：此 URL 的路径部分，如果没有路径，则返回一个空字符串

        */
        if (resource != null) {
            File file = new File(resource.getPath());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    System.out.println("============");
                    //System.out.println(f.getAbsolutePath());
                    //D:\Java_developer_tools\ssm\my_spring\spring\out\production\spring\com\hspedu\spring\component\UserDao.class

                    // 4.获取全类名 com.hspedu.spring.component.UserDao
                    // 反射时 需要放入 到Class.forName("com.hspedu.spring.component.UserDao")
                    // 注意forName 里的路径格式 是用点 . 间隔 同时最后是一个文件名 没有后缀.java 或 .class

                    // 获取到遍历的文件的绝对路径fileAbsolutePath
                    String fileAbsolutePath = f.getAbsolutePath();

                    // 判断 该文件是否以.class 结尾 不能保证该文件夹下的所有文件都是.class文件！因此需要进行判断
                    if (fileAbsolutePath.endsWith(".class")) {
                        // 1) 获取到类名 UserDao
                        String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
                        //System.out.println("类名= " + className);
                        // 2) 将/ 替换成 . 获取类的完整的路径(全类名)
                        path = path.replace("/", ".");
                        // 3) 将包名和类名拼接成全类名
                        //老师解读 path.replace("/",".") => com.hspedu.spring.component
                        String fullClassPath = path + "." + className;
                        //System.out.println("fullClassPath= "+fullClassPath);

                        // 5. 将含有注解的类 使用反射生成对象 放入ioc容器中
                        try {
                            //这时，我们就得到老该类的Class对象
                            //Class clazz = Class.forName(classFullName)
                            //老师说一下
                            //1. Class clazz = Class.forName(classFullName) 可以反射加载类
                            //2. classLoader.loadClass(classFullName); 可以反射类的Class
                            //3. 区别是 : 上面方式后调用来类的静态方法, 下面方法不会
                            //4. aClass.isAnnotationPresent(Component.class) 判断该类是否有 @Component

                            // 1) 使用类的加载器 得到正在遍历的类的 Class对象
                            Class<?> aClass = classLoader.loadClass(fullClassPath);

                            // 判断该类是不是需要注入容器,  判断该类中是否有注解 Repository/Service/Controller/Component中的任意一个
                            // 因为可能会有普通类 如 AA类 中没有写任何注解！
                            //isAnnotationPresent() :如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。
                            if (aClass.isAnnotationPresent(Repository.class) || aClass.isAnnotationPresent(Service.class)
                                    || aClass.isAnnotationPresent(Controller.class) || aClass.isAnnotationPresent(Component.class)) {
                                // 进来这里 说明有写spring 注解

                                // 判断注解上 是否设置了value  @Component(value = "hsp1") 如果设置了 将该value /id 当做key
                                // 存入ioc 容器中

                                // 先判断带有的注解是哪种注解
                                //这里老师演示一个Component注解指定value,分配id
                                //老师就是演示了一下机制.
                                if(aClass.isAnnotationPresent(Component.class)){
                                    // 拿到该类中的Component注解 因为该类可能还会有其他类型的注解
                                    // 传进去Component.class 为的是拿到我们需要的Component注解
                                     /*public <A extends Annotation> A getDeclaredAnnotation(类<A> annotationClass)
                                         参数：annotationClass - 对应于注释类型的Class对象
                                         结果：如果直接出现在该元素上，则指定注释类型的元素注释，否则为null
                                     */
                                    //获取到该注解
                                    Component component = aClass.getDeclaredAnnotation(Component.class);
                                    String id = component.value();
                                    // 将 className 替换为id 下面存的就变成id 了
                                    //if (!"".endsWith(id)){
                                    // "".endsWith("")= true

                                    if (!"".equals(id)){
                                        className = id;//替换
                                    }


                                }



                                //这时就可以反射对象，并放入到容器中
                                // 进行反射拿到该类的Class对象
                                Class<?> aClass1 = Class.forName(fullClassPath);
                                // 生成该类的实例对象 放入到ioc 容器中
                                Object o = aClass1.newInstance();

                                // 这里放进去的对象就是对应类型的类的对象 而不是Object类型的对象 why?
                                //放入到容器中, 将类名的首字母小写作为id
                                //StringUtils.uncapitalize()
                                //StringUtils 是spring提供的工具类

                                //ioc.put(className, o);
                                ioc.put(StringUtils.uncapitalize(className), o);

                                // 进行测试
                                // 结果：编译类型只是用来接收 在堆中存放的是运行时类型！！！
                                //Object objString = new String("www");
                                //String str = "str";
                                //ioc.put("objString", objString);
                                //ioc.put("str", str);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        }


    }

    // 编写方法 返回容器中的对象
    public Object getBean(String key){
        return ioc.get(key);
    }

}
