package com.hspedu1.hspspringmvc1.context;

import com.hspedu1.hspspringmvc1.annotation.Autowired;
import com.hspedu1.hspspringmvc1.annotation.Controller;
import com.hspedu1.hspspringmvc1.annotation.Service;
import com.hspedu1.hspspringmvc1.xml.XMLParser;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @create 2023-10-06-12:42
 * @description: HspWebApplicationContext 表示我们自己的spring容器
 */
public class HspWebApplicationContext1 {

    public Map<String, Object> ioc =
            new ConcurrentHashMap<>();
    // 定义属性用于表示spring配置文件
    private String springConfigFile;
    // 定义一个集合用于存放要扫描的所有的类的全路径
    private List<String> classFullPathList =
            new ArrayList<>();
    //定义属性configLocation 要扫描的spring xml 配置文件 "hspspringmvc1.xml"
    private String configLocation;

    public HspWebApplicationContext1() {
    }

    // 提供带参构造器
    public HspWebApplicationContext1(String configLocation) {
        this.configLocation = configLocation;


    }

    public void init() {
        //String basePackage = XMLParser.parser("hspspringmvc1.xml");
        // 下面是从web.xml 动态获取hspspringmvc.xml文件的方式
        String basePackage = XMLParser.parser(configLocation);

        System.out.println("需要扫描的包beanPackage= " + basePackage);

        //判断basePackage是否为空串"" 如果为空串 就不需要扫描
        if ("".equals(basePackage)) {
            return;
        }

        //到这 说明有需要扫描的包 判断需要扫描的包是否大于0
        String[] beanPackages = basePackage.split(",");
        if (beanPackages.length >= 1) {
            //说明至少有1个包需要扫描 进行遍历 将要扫描的包进行扫描
            for (String beanPackage : beanPackages) {
                //需要扫描到包
                //System.out.println("需要扫描的包beanPackage= " + beanPackage);
                //扫描到所有的要扫描的包下的类的全路径 放入到classFullPathList集合
                scanPackage(beanPackage);
            }
        }


        System.out.println("扫描后的全类名的集合classFullPathList= " + classFullPathList);
        //实例化对象 注入到容器中
        executionInstance();
        System.out.println("注入实例对象后的ioc容器= " + ioc);
        //完成spring容器对象的自动装配
        executeAutoWired();
        System.out.println("自动装配后的ioc容器= " + ioc);


    }

    //创建方法，完成对包的扫描  io/容器/字符串的处理
    // 该方法将要扫描的类的全路径保存到集合classFullPathList中

    /**
     * @param pack 表示要扫描的包 "com.hspedu1.controller"
     */
    public void scanPackage(String pack) {

        System.out.println("scanPackage() 要扫描的pack= " + pack);
        // 该方法将要扫描的类的全路径保存到集合classFullPathList中
        // 首先通过类的加载器拿到要扫描的包 在工作目录下的真实的绝对路径
        String classPath = this.getClass().getClassLoader().
                getResource("/").getPath();
        System.out.println("classPath!= " + classPath);
        //classPath= file:/D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/my_springmvc/WEB-INF/classes/

        // 通过类的加载路径拼接上包名得到要扫描的包的绝对路径
        if (pack.contains(".")) {
            pack = pack.replaceAll("\\.", "/");

        }

        System.out.println("pack= " + pack); //pack= com/hspedu1/controller
        String path = this.getClass().getResource("/" + pack).getPath();
        /*但是在Tomcat下path是否以’/'开头无所谓，可以以斜杠开头也可以不以斜杠开头 都可以获取到项目的加载路径
            即在Tomcat下可以 使用classLoader.getResource("/")
            获取到类加载路径 //classPath= file:/D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/my_springmvc/WEB-INF/classes/  */
        System.out.println("要扫描的包的绝对路径path= " + path);

        // 构建文件对象 要扫描的包
        File fileDirectory = new File(path);
        if (fileDirectory.isDirectory()) {
            System.out.println("fileDirectory是目录");
            File[] files = fileDirectory.listFiles();
            for (File file : files) {
                //file 可能还是一个文件夹/子包

                if (file.isDirectory()) {
                    // 是子包 进行递归调用
                    System.out.println("file是子包file.getName()= " + file.getName());
                    // file是子包file.getName()= cc  如果这个file文件是目录 file.getName() 没有后缀.class
                    //String subPack = pack+"/"+file.getName().substring(0,file.getName().indexOf("."));
                    //String subPack = pack + "/" + file.getName();
                    String subPack = pack + "." + file.getName();
                    System.out.println("是子包 subPack= " + subPack);
                    scanPackage(subPack);
                } else {
                    //是一个文件 将该文件的全类名保存到classFullPathList集合
                    //拼接成全类路径
                    String fileFullPath = pack + "/" + file.getName().substring(0, file.getName().indexOf("."));
                    //将全类路径中的所有的斜杠替换成点 变成全类名
                    //在正则表达式中 正斜杠/ 不需要转移就可以匹配到
                    String replaceAllFileFullPath = fileFullPath.replaceAll("/", ".");
                    System.out.println("扫描到的类的全路径replaceAllFileFullPath= " + replaceAllFileFullPath);
                    //放入到classFullPathList集合
                    classFullPathList.add(replaceAllFileFullPath);
                }


            }


        }
    }


    //编写方法 实例化对象到spring容器
    public void executionInstance() {
        //首先判断classFullPathList集合是否为空
        if (classFullPathList.size() == 0) { //说明没有扫描到类
            return; //直接返回不走了 结束方法
        }

        // 说明classFullPathList集合不为空
        // 判断哪些类需要进行实例化 即那些类上有@Controller/@Service
        // 遍历集合classFullPathList
        for (String classFullPath : classFullPathList) {
            // 通过类的全路径 拿到calzz对象
            try {
                Class<?> clazz = Class.forName(classFullPath);


                //判断该clazz对应的类上是否有@Controller注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 说明是一个Controller
                    // 拿到这个类的简单类名
                    String beanName = clazz.getSimpleName();
                    //System.out.println("beanName= " + beanName);

                    // 反射生成实例对象
                    Object bean = clazz.newInstance();

                    // 判断该注解上是否指定的有值 即beanName
                    // 拿到注解@Controller
                    Controller controllerAnnotation = clazz.getAnnotation(Controller.class);
                    String currentName = controllerAnnotation.value();
                    if (!"".equals(currentName)) {
                        beanName = currentName;
                    }

                    String lowerBeanName = null;
                    // 得到类的首字母小写形式的类名
                    if (beanName.length() <= 1) {
                        lowerBeanName =
                                beanName.toLowerCase();
                    } else {

                        lowerBeanName =
                                beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                    }
                    //System.out.println("lowerBeanName= " + lowerBeanName);
                    ioc.put(lowerBeanName, bean);
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    // 该clazz对象对应的类包含@Service注解
                    // 将该Service注入到容器中 按照实现的接口名首字母小写
                    // 和该类的简单类名首字母小写注入
                    // 得到该类的实例对象service
                    Object service = clazz.newInstance();
                    //1. 得到所有的接口的名称=>反射
                    Class<?>[] interfaces = clazz.getInterfaces();
                    if (interfaces.length > 0) {
                        // 说明该类实现的有接口 按照接口名首字母小写注入bean
                        //2.遍历该Service实现子类 实现的接口 通过多个接口名来注入
                        for (Class<?> anInterface : interfaces) {
                            String anInterfaceSimpleName = anInterface.getSimpleName();
                            //得到接口名首字母小写形式
                            String beanName = anInterfaceSimpleName.substring(0, 1).toLowerCase() + anInterfaceSimpleName.substring(1);
                            ioc.put(beanName, service);
                        }
                    }

                    // 3.按照该实现类的类名注入实例对象
                    // 获取简单类名
                    String simpleName = clazz.getSimpleName();
                    // 拿到类名首字母小写形式
                    String beanName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);

                    // 拿到Service 判断是否指定了beanName
                    Service serviceAnnotation = clazz.getAnnotation(Service.class);
                    // 获取设置的value
                    String value = serviceAnnotation.value();

                    if (!"".equals(value)) {
                        // 说明设置了value 把该值当作beanName
                        // 修改beanName
                        beanName = value;
                    }

                    // 生成service实例对象 注入到ioc 容器中
                    ioc.put(beanName, service);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    //编写方法完成属性的自动装配
    public void executeAutoWired() {
        // 自动装配要在ioc容器对象注入完成之后 进行注入 不可以在初始化注入的过程中自动装配
        // 因为此时ioc 中还有bean没有注入完成 不能完成自动装配
        // 首先判断ioc容器是否为空
        if (ioc.isEmpty()) {
            //如果ioc容器为空 直接返回 都没有controller对象 何谈装配属性对象
            return;//直接返回或是抛出异常
        }
        // 到这里说明ioc容器不为空 可以考虑自动装配问题
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            String beanName = entry.getKey();
            Object bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            //取出对象对应的类的所有的属性 判断属性上是否有@Autowired注解
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    // 说明该属性需要进行自动装配

                    // 在容器中使用属性类型首字母小写和
                    // 接口名首字母小写和属性名分别注入ioc容器中bean对象
                    // 拿到该属性的属性名 去ioc容器中查找是否有该bean
                    String fieldName = declaredField.getName();
                    System.out.println("fieldName= " + fieldName);//fieldName= goodsService
                    // 看@Aurowired注解上是否指定了要装配的beanName
                    // 拿到属性上的@Autowired注解
                    Autowired autowiredAnnotation = declaredField.getAnnotation(Autowired.class);
                    String value = autowiredAnnotation.value();
                    // 判断是否指定了要装配的beanName 指定了就用该bean进行装配
                    // 如果没有指定 就使用上面获取的fieldName 进行装配
                    if (!"".equals(value)) {
                        //说明注解上指定了要自动装配的beanName 修改fieldName
                        fieldName = value;
                    }
                    //else {
                    // 使用属性的类型名或是属性名 选一种处理方法即可
                    //    // //说明按照默认机制进行装配 按照属性的类型名首字母小写进行装配
                    //    //field.getType() 方法 可以拿到字段的类型 该属性的类型
                    //    Class<?> declaredFieldType = declaredField.getType();
                    //    String simpleName = declaredFieldType.getSimpleName();
                    //    // 拿到属性字段类型名称的首字母小写形式，作为名字来进行装配
                    //    String  fieldTypeName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                    //
                    //}
                    System.out.println("自动装配前的ioc= " + ioc);
                    // autowiredBean 是要完成自动装配的属性值
                    Object autowiredBean = ioc.get(fieldName);
                    if (null == autowiredBean) {
                        // 说明没有找到该属性名/指定名称的bean
                        throw new NullPointerException("ioc 容器中没有beanName=" + fieldName + "的bean");
                    } else {
                        //说明找到了该属性名/指定名称的bean 进行自动装配
                        //使用反射的set()方法完成自动装配
                        //防止该属性为private的 进行反射暴破
                        //反射暴破需要用要设置的类的属性或者是方法进行 不能使用clazz对象
                        declaredField.setAccessible(true);
                        //在这里 完成自动装配
                        try {
                            declaredField.set(bean, autowiredBean);

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        //自动装配完成 不用break 如果有多个属性需要自动装配 继续循环完成自动装配

                    }

                }
            }
        }


    }
}
