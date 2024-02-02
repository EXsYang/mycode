package com.hspedu.spring.annotation3;

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
 * @description:
 * @create 2023-09-04-18:59
 */
public class YdSpringApplicationContext {

    private Class configClass;

    private final ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    public YdSpringApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 获取要扫描的包的路径 获取全路径 获取文件名

        // 获取配置类的注解
        ComponentScan componentScan = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);

        // 获取注解上的value
        String path = componentScan.value();
        System.out.println("path= " + path); //path= com.hspedu.spring.component

        // 将.替换为/
        path = path.replace(".", "/");
        System.out.println("替换成/ 的path= " +  path ); // 替换成/ 的path= com/hspedu/spring/component

        // 通过类的加载器 获取要加载的包的全路径 任何一个类的class对象 都可以拿到类的加载器
        ClassLoader classLoader = YdSpringApplicationContext.class.getClassLoader();

        URL resource = classLoader.getResource(path);
        System.out.println("resource= " + resource);
        //resource= file:/D:/Java_developer_tools/ssm/my_spring/spring/out/production/spring/com/hspedu/spring/component

        // 根据全路径 拿到对应的文件目录
        File file = new File(resource.getFile());
        //File file = new File(resource.getFile()+"s.xml");
        //File[] files = file.listFiles();
        //System.out.println("当file不是目录时="+files); //当file不是目录时=null

        // 判断该文件是不是一个目录
        if (file.isDirectory()){
            //file.listFiles() 如果file 不是目录文件会报错 ，如果目录为空，则该数组将为空。 如果此抽象路径名不表示目录，或返回I / O错误，则返回null 。

            // 注意：idea 在源码路径src目录下创建的的空文件夹
            // ,不会自动同步到out目录下 只有当源码路径src目录下的文件中有文件时才会同步到out目录下

            // 当file是目录 file.listFiles(); 但是文件为空时 不会报错 只有当 不是目录文件时才会返回null
            //File[] files = file.listFiles();
            //System.out.println("files= " + files); // files= [Ljava.io.File;@4617c264
            //for (File f : files) {
            //    System.out.println("f= " + f); // 文件夹为空时 什么也不会输出 不会进入到循环中
            //}

            File[] files = file.listFiles();
            System.out.println("files= " + files);
            for (File f : files) {
                System.out.println("f.getAbsolutePath()= " + f.getAbsolutePath());

                // 根据绝对路径 获取文件名 然后和前面的path 进行拼接 这里主要是可以得到文件名
                // 通过绝对路径来获取 类的全路径太难 因为全路径是变化的 
                // 先过滤掉文件名后缀不是.class 的 , 因为如果f.getAbsolutePath().indexOf(".class") 找不到
                // ,会返回-1 substring(?,-1)  会出现数组越界异常
                String fileAbsolutePath = f.getAbsolutePath();
                //if(fileAbsolutePath.indexOf(".class") != -1){
                if(fileAbsolutePath.contains(".class")){
                    // 拿到类名
                    String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));

                    // 拼接成全类名
                    // 将path/替换成. 因为反射 要用的全类名的格式是
                    // 包名.包名.包名.类名  com.hspedu.spring.component.UserDao 以点间隔的 同时类名没有后缀！
                    // 老韩解读: s1.replace() 方法执行后，返回的结果才是替换过的. // 注意对 s1 没有任何影响
                    if (path.contains("/")){
                        path = path.replace("/",".");
                    }

                    // 拼接成全类名
                    String fullClassName = path + "." + className;

                    // 使用类的加载器 的loadClass 方法 获取到类的信息 判断一下 该类有没有 四个注解之一
                    // classLoader.loadClass() 拿到的是轻量级的 class 信息
                    try {
                        Class<?> aClass = classLoader.loadClass(fullClassName);
                        if (aClass.isAnnotationPresent(Repository.class) || aClass.isAnnotationPresent(Service.class) ||
                        aClass.isAnnotationPresent(Controller.class) || aClass.isAnnotationPresent(Component.class)){
                            // 定义一个变量 用来确定是否需要 首字母小写 默认是首字母小写
                            boolean toLowerCase = true;

                            // 到这里说明该类是有 四个注解之一的类 进行反射 生成对象放入ioc容器中
                            // 在这里可以先判断这个注解 到底是哪一个注解 拿到这个注解对象
                            // 这个不完整的aClass 对象 也可以拿到对应的类里面的注解的信息！！！
                            if (aClass.isAnnotationPresent(Repository.class)){
                                Repository repository = aClass.getDeclaredAnnotation(Repository.class);

                                if (!"".equals(repository.value())){
                                    // 如果注解上值不为空串 把className进行替换
                                    className = repository.value();
                                    //如果指定了值 就用这个值作为id 首字母小写的方式 就要拿掉了
                                    // 因为如果指定的值 有可能就是首字母大写
                                    toLowerCase = false;
                                }
                            }else if (aClass.isAnnotationPresent(Service.class)){
                                Service service = aClass.getDeclaredAnnotation(Service.class);
                                if (!"".equals(service.value())){
                                    className = service.value();
                                    toLowerCase = false;
                                }
                            }else if (aClass.isAnnotationPresent(Controller.class)){
                                Controller controller = aClass.getDeclaredAnnotation(Controller.class);
                                if (!"".equals(controller.value())){
                                    className = controller.value();
                                    toLowerCase = false;
                                }
                            }else if (aClass.isAnnotationPresent(Component.class)){
                                Component component = aClass.getDeclaredAnnotation(Component.class);
                                if (!"".equals(component.value())){
                                    className = component.value();
                                    toLowerCase = false;
                                }
                            }

                            Class<?> aClass1 = Class.forName(fullClassName);
                            Object o = aClass1.newInstance();
                            // 这里是按照运行类型 放进去的
                            if (toLowerCase){
                                ioc.put(StringUtils.uncapitalize(className),o);
                            }else {
                                ioc.put(className,o);
                            }

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }



            }

        }


    }
}
