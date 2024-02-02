package com.hspedu.hspspringmvc.context;

import com.hspedu.hspspringmvc.annotation.AutoWired;
import com.hspedu.hspspringmvc.annotation.Controller;
import com.hspedu.hspspringmvc.annotation.Service;
import com.hspedu.hspspringmvc.xml.XMLParser;

import javax.print.attribute.standard.Fidelity;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @create 2023-10-01-15:02
 * @description: HspWebApplicationContext 表示我们自己的spring容器
 */
public class HspWebApplicationContext {

    //定义属性ioc, 存放反射生成的Bean对象 /Controller/Service
    public ConcurrentHashMap<String, Object> ioc =
            new ConcurrentHashMap<>();
    //定义属性 classFullPathList ，保存包/子包的类的全路径
    private List<String> classFullPathList =
            new ArrayList<>();
    //定义属性 configLocation 是要进行扫描的spring配置文件 的文件名
    private String configLocation;// 属性，表示spring容器配置文件

    //无参构造器
    public HspWebApplicationContext() {
    }


    public HspWebApplicationContext(String configLocation) {
        this.configLocation = configLocation;
    }


    //编写方法完成自己的spring容器的初始化
    // 需要在tomcat启动时自动加载中央控制器HspDispatcherServlet的init方法中，
    // 来调用下面这个init方法
    public void init() {

        //这里是写死的 固定的spring容器配置文件 => 做活
        //String beanPackage = XMLParser.getBeanPackage("hspspringmvc.xml");

        //做活后
        String beanPackage = XMLParser.getBeanPackage(configLocation.split(":")[1]);
        String[] beanPackages = beanPackage.split(",");
        //遍历 beanPackages ，进行扫描 下面的判断稍微有点问题 因为即使是返回的空串进行分割
        // length也是1 恒成立 可以使用返回的beanPackage是否为空串进行判断
        //split如果 按照xxx 进行分割 找不到要分割的值xxx 分割失败 split.length=1
        //空串使用逗号分割后的length= 1
        if (beanPackages.length > 0) { // 如果长度大于0 在扫描，如果一个包都没有就不用扫描了
            for (String pack : beanPackages) {
                scanPackage(pack);
            }
        }


        System.out.println("扫描后的：classFullPathList= " + classFullPathList);

        //将扫描到的类反射到ioc容器
        executionInstance();
        System.out.println("扫描后的：ioc= " + ioc);

        //完成注入的bean对象的 属性装配
        executeAutoWired();
        System.out.println("自动装配后的ioc= " + ioc);

    }

    //创建方法，完成对包的扫描  io/容器/字符串的处理
    // 该方法将要扫描的类的全路径保存到集合classFullPathList中
    /**
     * @param pack 表示要扫描的包 "com.hspedu.controller"
     */
    public void scanPackage(String pack) {

        //得到包所在的工作路径[绝对路径]
        //下面这句话的含义是 通过类的加载器，得到你指定的包对应的 工作路径[绝对路径]
        //比如"com.hspedu.controller" => url 是 // D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/my_springmvc/WEB-INF/classes/com/hspedu/controller/
        //细节说明：
        // 1.不要直接使用Junit测试，否则 url null
        // 2.启动tomcat来测试

        // 通过传进来的要扫描的包 获取到运行时 工作目录下的绝对路径
        System.out.println("scanPackage() 要扫描的pack= " + pack);

        URL classPath = this.getClass().getClassLoader().
                getResource("/");
        System.out.println("classPath= " + classPath);

        // 使用类的加载器进行获取
        URL url = this.getClass().getClassLoader().
                getResource("/" + pack.replaceAll("\\.", "/"));
        /*但是在Tomcat下path是否以’/'开头无所谓，可以以斜杠开头也可以不以斜杠开头 都可以获取到项目的加载路径
        即在Tomcat下可以 使用classLoader.getResource("/")
        获取到类加载路径 //classPath= file:/D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/my_springmvc/WEB-INF/classes/  */

        System.out.println("要扫描的包的工作目录下的url= " + url);
        //要扫描的包的工作目录下的url=
        // file:/D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/my_springmvc/WEB-INF/classes/com/hspedu/controller/

        // 将要扫描的包下所有的类的全路径放入到集合中
        //  url.getPath();或者url.getFile();
        //  可以去除url对象toString() 时前面的"file:/"
        //String path = url.getPath();
        String path = url.getFile();
        System.out.println("path= " + path);

        //在io中，把目录也视为一个文件
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            //遍历dir[文件/子目录]
            for (File file : files) {
                if (file.isDirectory()) { // 如果是一个子目录，需要递归扫描
                    // 说明是子包 还要进行扫描
                    scanPackage(pack + "." + file.getName());
                } else {
                    //在正则表达式中 正斜杠/ 不需要转移就可以匹配到

                    //老韩说明：这时，你扫描到的文件，可能是.class,也可能是其他文件
                    //就算是.class,也存在是不是需要注入到容器的问题 【该文件的类中有没有写@Controller、@Service、@Component、@Repository】
                    //目前先把文件的全路径都保存到集合，后面在注入对象到容器时，再处理
                    // 说明是文件 获取文件名 拼接成类的全路径 放入到classFullPathList集合
                    //file.getName() 方法也会拿到后缀
                    // 该方法得到的形式如=>文件名：news1.txt
                    String classFullPath = pack + "." + file.getName().replace(".class", "");
                    classFullPathList.add(classFullPath);
                }
            }
        }

    }

    // 该方法将扫描到的类 满足条件的情况下 反射到ioc容器中
    public void executionInstance() { // 任务:验证该方法和老韩的区别
        // 首先判断存放类的全路径的集合有没有元素 是否扫描到类
        if (classFullPathList.size() == 0) { //说明没有扫描到类
            return; //直接返回不走了 结束方法
        }

        // 到这里 说明集合不为空 遍历classFullPathList 进行判断 是否有注解@Controller
        // 通过反射获取该类的class对象
        for (String classFullPath : classFullPathList) {
            try {
                Class<?> clazz = Class.forName(classFullPath);

                // 是否有注解@Controller
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 说明当前这个类有注解@Controller 生成对象放入到Map集合
                    Object instance = clazz.newInstance();

                    String simpleName = clazz.getSimpleName();
                    //System.out.println("simpleName= " + simpleName);
                    //simpleName= GoodsController
                    //得到类名首字母小写
                    String beanName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                    System.out.println("beanName= " + beanName);
                    ioc.put(beanName, instance);
                }// 如果有其他注解，可以扩展
                else if (clazz.isAnnotationPresent(Service.class)) {
                    //说明这个类上标注的是@Service注解

                    //将这个类注入到spring容器中
                    //首先确认 这个类上有没有指定注入时该bean的名称
                    //通过反射拿到这个类的Service注解
                    //Service serviceDeclaredAnnotation = clazz.getDeclaredAnnotation(Service.class);
                    Service serviceAnnotation = clazz.getAnnotation(Service.class);
                    //获取到Service注解上的value值 => 就是注入的beanName
                    String beanName = serviceAnnotation.value();

                    if ("".equals(beanName)) {
                        //说明该@Service注解上没有指定beanName 就用默认机制注入Service
                        //可以通过接口名/类名[首字母小写] 来注入ioc容器
                        //1. 得到所有的接口的名称=>反射
                        Class<?>[] interfaces = clazz.getInterfaces();
                        //得到该Service的实例对象instance
                        Object instance = clazz.newInstance();
                        //2.遍历该Service实现子类 实现的接口 通过多个接口名来注入
                        for (Class<?> anInterface : interfaces) {
                            String simpleName = anInterface.getSimpleName();
                            System.out.println("clazz.getSimpleName()= " + simpleName);
                            String typeName = anInterface.getTypeName();
                            System.out.println("clazz.getTypeName()= " + typeName);
                            // 得到接口名的首字母小写
                            String typeName2 = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                            // 按照接口名 注入该clazz的实现类的对象
                            //下面这种写法不行 会注入多个对象 不划算 扩大范围 只需要注入一个对象
                            ioc.put(typeName2, instance);
                        }

                        // 3.按照该实现类的类名注入实例对象
                        // 得到该类的简单类名
                        String simpleName = clazz.getSimpleName();
                        // 得到该类的简单类名首字母小写形式
                        String simpleName2 = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                        // 按照类名首字母小写注入到ioc容器中
                        ioc.put(simpleName2, instance);

                    } else { // 如果有指定名称，就使用该名称注入即可
                        // 到这里就是在Service注解上写了值
                        // 就按照提供的值注入bean到ioc容器
                        ioc.put(beanName, clazz.newInstance());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //编写方法完成属性的自动装配
    public void executeAutoWired() {

        // 遍历ioc容器 取出容器中的bean
        // 判断ioc有没有要装配的对象
        if (ioc.isEmpty()) {
            //如果ioc容器为空 直接返回 都没有controller对象 何谈装配属性对象
            return; // 也可以抛出异常
        }

        // 取出bean对象类中的所有的属性 查看属性上是否有@AutoWired注解
        // 遍历ioc 所有的bean 然后获取bean的所有字段/属性，判断是否需要装配
        // ioc 是ConcurrentHashMap 使用最简单的遍历方式
        // 取出entrySet

        Set<Map.Entry<String, Object>> entries = ioc.entrySet();
        // entry => <String, Object>
        // String 就是注入的beanName ; Object 就是注入的bean对象

        for (Map.Entry<String, Object> entry : entries) {
            // key 就是注入的beanName
            //String key = entry.getKey();
            // entry.getValue()就是注入的bean对象
            Object bean = entry.getValue();

            // 取出类中所有的属性
            // getFields() 拿不到私有的属性 注意！！！
            // 要用getDeclaredFields() 拿到本类中所有的属性
            //Field[] fields = bean.getClass().getFields();
            Field[] declaredFields = bean.getClass().getDeclaredFields();

            // 遍历类中所有的属性 判断是否有@AutoWired注解
            for (Field declaredField : declaredFields) {
                //判断该属性上是否有@AutoWired注解
                if (declaredField.isAnnotationPresent(AutoWired.class)) {
                    // 如果这个字段上没有这个@AutoWired注解我们就不需要进行处理
                    // 有注解 获取属性的属性名 查看ioc容器中是否有对应名称的bean

                    //得到当前字段的@Autowired
                    AutoWired autoWiredAnnotation = declaredField.getDeclaredAnnotation(AutoWired.class);
                    String beanName = autoWiredAnnotation.value();

                    if ("".equals(beanName)) {
                        //说明按照默认机制进行装配 按照属性的类型名首字母小写进行装配
                        //field.getType() 方法 可以拿到字段的类型 该属性的类型
                        Class<?> type = declaredField.getType();
                        String simpleName = type.getSimpleName();
                        //得到属性字段类型名称的首字母小写形式,作为名字来进行装配
                        beanName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                    }
                    //按照@AutoWired中设置的value进行装配
                    //从ioc容器中获取到bean
                    Object instance = ioc.get(beanName);
                    if (null == instance) {
                        // 说明你指定的名字对应的bean对象不在ioc容器中
                        //return;
                        throw new RuntimeException("ioc容器中 没有该属性名的bean/不存在你要装配的bean ");
                    }
                    // 可以走到这里没有抛异常
                    // 说明ioc 容器中有该属性名的bean对象
                    // 进行反射注入
                    // 防止该属性是私有的 private 进行反射爆破
                    declaredField.setAccessible(true);

                    // 进行属性自动装配
                    try {
                        declaredField.set(bean, instance);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }

        }


    }


}
