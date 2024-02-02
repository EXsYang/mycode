package com.hspedu.spring.annotation2;

import com.hspedu.spring.component.MyComponent;
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
 * @create 2023-09-04-18:59
 */
public class YdSpringApplicationContext {

    // 定义一个属性用来接收配置文件的Class对象 只有获得了配置类的Class对象 才可以通过反射
    // 拿到配置类的注解信息
    private Class configClass;

    // 定义一个集合 用于存放ioc容器初始化对象 即配置了注解的类 就生成对应的类的对象 放入ioc容器
    private final ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    // 提供构造器 根据传进来的Class对象 进行容器的初始化工作
    public YdSpringApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 获取要扫描的包的全路径 根据全路径对文件进行遍历 对有注解的类进行反射放入到容器 提供get方法
        // 1 获取 @ComponentScan(value = "com.hspedu.spring.component")
        // 根据传进来的Class对象 获取到注解

        // 使用反射获取这个类的注解
        ComponentScan componentScan = (ComponentScan)this.configClass.getDeclaredAnnotation(ComponentScan.class);
        // 得到要扫描的包的value
        String path = componentScan.value();
        //System.out.println("path= " + path); //path= com.hspedu.spring.component

        // 因为getResource() 是按照斜杠来获取的 所以 将.替换为/
        path = path.replace(".","/");

        // 通过类的加载器 得到要扫描的包的全路径
        ClassLoader classLoader = YdSpringApplicationContext.class.getClassLoader();
        //URL resource = classLoader.getResource("com/hspedu/spring/component");
        URL resource = classLoader.getResource(path);
        //System.out.println("resource= " + resource);
        //resource= file:/D:/Java_developer_tools/ssm/my_spring/spring/out/production/spring/com/hspedu/spring/component

        // 得到要扫描的包的文件 因为此时的url 不是 String 类型
        // 因此需要通过getPath()方法 转换为String类型

        //File file = new File(resource.getPath());
        File file = new File(resource.getFile());

        //System.out.println("resource.getPath()= " + resource.getPath());
        //System.out.println("resource.getFile()= " + resource.getFile());
        //resource.getPath()= /D:/Java_developer_tools/ssm/my_spring/spring/out/production/spring/com/hspedu/spring/component
        //resource.getFile()= /D:/Java_developer_tools/ssm/my_spring/spring/out/production/spring/com/hspedu/spring/component


        // 判断该文件是不是一个目录
        if (file.isDirectory()){
            // 是一个目录 将该目录中的文件取出放在一个list集合中 方便进行遍历
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println("=====================");
                System.out.println(f.getAbsolutePath());
                //D:\Java_developer_tools\ssm\my_spring\spring\out\production\spring\com\hspedu\spring\component\MyComponent.class

                // 判断该文件是否为.class 文件 .properties 文件会报错 indexOf()找不到 会返回-1 ，
                // 此时substring(?,-1) 右边的值处出现角标越界异常
                if (f.getAbsolutePath().endsWith(".class")){

                    // 通过绝对路径得到 要扫描的文件的文件名
                    //System.out.println("左边边界值= "+f.getAbsolutePath().lastIndexOf("/")); //左边边界值= -1
                    //String className = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/") + 1, f.getAbsolutePath().indexOf(".class"));
                    // 注意这里要找的是反斜杠 \ ,而且要写两个 反斜杠 一个代表的是转义字符
                    //System.out.println("左边反斜杠的边界值= "+f.getAbsolutePath().lastIndexOf("\\")); // 左边反斜杠的边界值= 94
                    String className = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1, f.getAbsolutePath().indexOf(".class"));
                    System.out.println("className= " + className);

                    // 判断此处的文件是否 有注释 如果有四种注释中的一种 就放到ioc容器集合中

                    // 获取全类名 因为反射需要使用.间隔 ,同时 类名没有后缀
                    // Class.forName(com.hspedu.spring.component.UserDao)
                    if (path.contains("/")){
                        System.out.println("进行替换 变成点 . 间隔");
                    //s1.replace() 方法执行后，返回的结果才是替换过的.
                        path = path.replace("/",".");
                    //System.out.println("path= " + path);

                    }

                    String classFullName = path + "." + className;
                    System.out.println("classFullName= " + classFullName);

                    // 先使用类的加载器 loadClass()得到轻量级(不完整)的 Class 对象,
                    // 这个得到的对象 只有类的信息
                    // 没有调用类的静态方法 和类之间的关系 仅用于判断 是否存在四种注解之一
                    try {
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        //isAnnotationPresent() 判断该注解是否存在
                        if(aClass.isAnnotationPresent(Repository.class) || aClass.isAnnotationPresent(Service.class)
                        || aClass.isAnnotationPresent(Controller.class) || aClass.isAnnotationPresent(Component.class)){
                            // 到这里 说明该类有注解 进行反射生成对象 放入ioc容器

                            // 判断该类的注解上是否有value id 如果有 将该value 作为集合的key/id保存
                            if (aClass.isAnnotationPresent(Component.class)){
                                // 该类的注解是Component 反射需要传进去注解类的class对象 因此需要判断该类上的是哪一个注解
                                Component component = aClass.getDeclaredAnnotation(Component.class);
                                // 字符串比较内容是否相等 要用equals()
                                //if ("" != component.value()){
                                if (!"".equals(component.value())){
                                    // 如果该注解 有写指定的id 就进行替换
                                    className = component.value();
                                }
                            }else if (aClass.isAnnotationPresent(Controller.class)){
                                Controller controller = aClass.getDeclaredAnnotation(Controller.class);
                                if (!"".equals(controller.value())){
                                    className = controller.value();
                                }
                            }else if (aClass.isAnnotationPresent(Service.class)){
                                Service service = aClass.getDeclaredAnnotation(Service.class);
                                if (!"".equals(service.value())){
                                    className = service.value();
                                }
                            }else if (aClass.isAnnotationPresent(Repository.class)){
                                Repository repository = aClass.getDeclaredAnnotation(Repository.class);
                                if (!"".equals(repository.value())){
                                    className = repository.value();
                                }
                            }

                            Class<?> aClass1 = Class.forName(classFullName);
                            Object instance = aClass1.newInstance();

                            //ioc.put(className,instance);

                            // StringUtils.uncapitalize() 转为首字母小写
                            ioc.put(StringUtils.uncapitalize(className),instance);


                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    System.out.println();
                }


            }

        }





    }



}
