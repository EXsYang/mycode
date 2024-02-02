package com.hspedu.spring.ioc;

import com.hspedu.spring.annotation.Autowired;
import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.annotation.ComponentScan;
import com.hspedu.spring.annotation.Scope;
import com.hspedu.spring.component.MonsterDao;
import com.hspedu.spring.processor.BeanPostProcessor;
import com.hspedu.spring.processor.InitializingBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: HspSpringApplicationContext 类的作用类似Spring原生ioc容器
 * @create 2023-09-03-19:20
 */
public class HspSpringApplicationContext {

    // 用于接收 传进来的配置文件HspSpringConfig的class对象 便于使用反射 解析到它的注解
    // 一个类底层只要是做反射 通常都要拿到这个类的Class类型 如果不拿到这个类的Class类型无法做反射！！
    //ioc我存放的就是通过反射创建的对象(基于注解方式)
    private Class configClass;

    //定义属性BeanDefinitionMap -> 存放BeanDefinition对象
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>();

    //定义属性SingletonObjects -> 存放单例对象
    private ConcurrentHashMap<String, Object> singletonObjects =
            new ConcurrentHashMap<>();

    //定义一个属性ArrayList 存放后置处理器
    private List<BeanPostProcessor> beanPostProcessorList =
            new ArrayList<>();


    //构造器
    public HspSpringApplicationContext(Class configClass) {

        // 扫描包，得到bean的class对象 排除包下不是bean的
        // 扫描将bean信息封装到beanDefinition对象 并放入到beanDefinitionMap

        beanDefinitionsByScan(configClass);


        // 初始化单例池,也就是如果bean是单例的就实例化，并放入到单例池Map
        // 通过beanDefinitionMap, 初始化singletonObjects单例池
        // 封装成方法
        // 遍历所有的beanDefinition对象
        // 这里是java基础-> 集合和枚举

        // 因为到这里已经把bean的信息都放入到beanDefinitionMap中了
        // 所有可以遍历beanDefinitionMap 查看那些是要放入到单例池中的
        Enumeration<String> keys = beanDefinitionMap.keys();
        while (keys.hasMoreElements()) {
            //得到beanName 用于获取对应的beanDefinition 同时作为单例池的key
            String beanName = keys.nextElement();
            //System.out.println("beanName= " + beanName);
            //通过beanName 得到对应的beanDefinition对象
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            //判断这个beanDefinition里的Scope是否为singleton 还是prototype
            if ("singleton".equalsIgnoreCase(beanDefinition.getScope())) {
                // 说明 这个bean是单例的 生成对象 将其放入到单例池
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }

        }

        //System.out.println("singletonObjects 单例池= " + singletonObjects);
        //System.out.println("beanDefinitionMap= " + beanDefinitionMap);

    }


    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        // 通过传进来的beanDefinition得到clazz
        Class clazz = beanDefinition.getClazz();
        try {
            //使用反射得到实例
            Object instance = clazz.getDeclaredConstructor().newInstance();

            //在这里进行依赖注入是最合适的！！ 返回对象之前先把依赖关系建立起来
            //首先 得到clazz对象里的所有的属性 进行遍历 判断哪个属性有@Autowired注解
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                // 判断该属性上是否有@Autowired注解
                // 注意要获取注解的类对象不可以加@！！！
                //if(declaredField.isAnnotationPresent(@Autowired.class)){
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    //提示一下
                    //处理@Autowired 的required ,很简单
                    //Autowired annotation = declaredField.getAnnotation(Autowired.class)
                    //annotation.required()=> 然后根据true, 是false 进行其它处理..

                    // 如果属性上有该注解 就获取该属性的属性名
                    String fieldName = declaredField.getName();
                    // 通过getBean() 获取容器中key/id为该属性名的一个bean
                    Object bean = getBean(fieldName);
                    // 进行装配
                    // 使用反射 将该bean对象 装配要返回的对象instance的该属性
                    //IllegalAccessException 因为要设置的属性时私有的 所以会出现非法访问异常
                    // 因此需要进行反射暴破
                    declaredField.setAccessible(true);
                    // 下面这个set 不是走的类里面生成的的set方法 而是直接通过反射直接设置的值
                    declaredField.set(instance, bean);
                }
            }

            // 在创建好Bean实例后，判断是否需要进行初始化【老师心得：
            // 容器中常用的一个方法是，根据该类是否实现了某个接口，
            // 来判断是否要执行某个业务逻辑，】
            // 标记接口(里面一个方法也没有 如Serializable) 只是给底层看的 并没有实现方法
            //
            //

            System.out.println("=========创建好实例了====== Bean= " + instance);

            // 在Bean初始化方法前 调用后置处理器的before方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                // 在后置处理器的before方法，可以对容器的bean实例进行处理
                // 然后返回处理后的bean实例，相当于做了一个前置处理
                Object current =
                        beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
                // 如果返回来的不是null 再去改变instance 如果返回来的是null 就不改变使用原来的instance
                if (current != null) {
                    instance = current;
                }

            }


            //这里判断是否要执行bean的初始化方法
            //判断当前创建的instance bean对象是否实现了InitializingBean接口
            // instanceof 判断某个对象的运行类型 是不是某个类型或者某个类型的子类型
            // 这里就是用到了接口编程的手法
            if (instance instanceof InitializingBean) {
                // 将instance 转成InitializingBean类型
                try {
                    ((InitializingBean) instance).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 在Bean初始化方法后 调用后置处理器的after方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                // 在后置处理器的after方法，还可以对容器的bean实例进行处理
                // 然后返回处理后的bean实例，相当于做了一个后置处理
                Object current = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
                // 如果返回来的不是null 再去改变instance 如果返回来的是null 就不改变使用原来的instance
                if (current != null) {
                    instance = current;
                }
            }

            System.out.println("-----------------------------------");
            return instance; // 这里返回了 下边的那个return null 就不会走！！
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;

    }


    // 该方法完成对指定包的扫描，并将Bean信息封装到BeanDefinition对象,再放入到Map
    public void beanDefinitionsByScan(Class configClass) {
        // 便于跨方法使用configClass对象
        this.configClass = configClass;

        //获取要扫描的包
        //1. 先得到HspSpringConfig配置的的@ComponentScan(value = "com.hspedu.spring.component")
        ComponentScan componentScan = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);

        //2. 通过componentScan的value=> 即要扫描的包
        // 这个value() 就是注解中定义的 String value() default "";
        String path = componentScan.value();
        System.out.println("要扫描的包=" + path);//要扫描的包=com.hspedu.spring.component

        // 得到要扫描的包下的所有的资源(类 .class文件)
        // 1.得到类的加载器(App 类加载器) 使用任何一个类的.class 都可以得到类的加载器
        ClassLoader classLoader = HspSpringApplicationContext.class.getClassLoader();

        //在 path 中，将 所有的 . 替换成/
        // 老韩解读: s1.replace() 方法执行后，返回的结果才是替换过的. // 注意对 s1 没有任何影响
        //String replacePath = path.replace(".", "/");
        path = path.replace(".", "/");

        // 2.通过类的加载器获取到要扫描的包的资源 url
        // classLoader.getResource("com/hspedu/spring/component"); 默认是按照斜杠 / 来间隔各级文件目录的
        // 但是我们这里是按照点 . 来间隔的 因此需要替换一下 将.替换为正斜杠 /

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
        //resource= file:/D:/Java_developer_tools/ssm/my_spring/hsp-myspring/target/classes/com/hspedu/spring/component

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

                    //System.out.println(f.getAbsolutePath());

                    // 4.获取全类名 com.hspedu.spring.component.UserDao
                    // 反射时 需要放入 到Class.forName("com.hspedu.spring.component.UserDao")
                    // 注意forName 里的路径格式 是用点 . 间隔 同时最后是一个文件名 没有后缀.java 或 .class

                    // 获取到遍历的文件的绝对路径fileAbsolutePath
                    String fileAbsolutePath = f.getAbsolutePath();
                    //System.out.println("f.getAbsolutePath()= " + f.getAbsolutePath());


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

                            // 1) 使用类的加载器 得到正在遍历的类的 Class对象
                            Class<?> clazz = classLoader.loadClass(fullClassPath);

                            // 判断该类是不是需要注入容器,  判断该类中是否有注解 Repository/Service/Controller/Component中的任意一个
                            // 因为可能会有普通类 如 AA类 中没有写任何注解！
                            //isAnnotationPresent() :如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。
                            // 这里是类上有Component注解 这里的‘此元素’指的是类！！
                            if (clazz.isAnnotationPresent(Component.class)) {
                                // 如果该类 使用了@Component 注解 ,说明是一个Spring bean
                                System.out.println("这是一个Spring bean = " + clazz + " 类名= " + className);

                                // 这里判断出扫描的包下的文件/类都是使用@Component修饰的
                                // 即是一个需要被注入到容器中的组件
                                // 老师说明：
                                // 1.为了方便，老韩这里将后置处理器放入到一个ArrayList
                                // 2.如果发现是一个后置处理器，放入到 beanPostProcessorList
                                // 3.在原生的Spring容器中，对后置处理器还是走的getBean,createBean
                                //   ,但是需要我们在singletonObjects 加入相应的业务逻辑
                                // 4.因为这里我们是为了讲解后置处理器的机制，我就简化
                                // 5.如果小伙伴们，仍然想走以前的逻辑 也可以 就是麻烦一点

                                // 首先要判断当前的这个class 有没有实现接口BeanPostProcessor
                                // 说明,这里我们不能使用 instanceof 来判断clazz是否实现了BeanPostProcessor
                                // 原因: clazz不是一个实例对象，而是一个类对象/clazz对象,使用isAssignableFrom()
                                // 可以将其当作一个语法理解
                                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                    BeanPostProcessor beanPostProcessor =
                                            (BeanPostProcessor) clazz.newInstance();
                                    // 放入到beanPostProcessorList
                                    beanPostProcessorList.add(beanPostProcessor);

                                    // 放入到List集合后 就跳出本次循环 后面的代码不走了 主要是不会再放到beanDefinitionMap中了
                                    // 即没有生成对应的beanDefinition对象 也没有放入到beanDefinitionMap
                                    // 就不会在初始化singletonObjects单例池时  放入该beanPostProcessor对象
                                    continue;
                                }

                                //先得到beanName
                                //1.得到Component注解
                                Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                                //2.得到Component注解设置的value 做为BeanDefinitionMap的key
                                String beanName = componentAnnotation.value();
                                //默认使用类名首字母小写 还没有处理

                                if ("".equals(beanName)) {
                                    //如果没有设置value 就默认类名首字母小写作为beanName
                                    beanName = StringUtils.uncapitalize(className);
                                }

                                // 将Bean的信息封装到BeanDefinition对象->放入到BeanDefinitionMap
                                BeanDefinition beanDefinition = new BeanDefinition();

                                // 获取该clazz对象上的Scope注解信息 并封装到BeanDefinition对象中保存
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    // 如果该clazz对象有Scope注解 解析注解中的value值
                                    Scope scope = clazz.getDeclaredAnnotation(Scope.class);
                                    //将Scope注解上的value值封装到beanDefinition对象中
                                    beanDefinition.setScope(scope.value());
                                } else {
                                    //没有写注解 就设置默认值singleton
                                    beanDefinition.setScope("singleton");
                                }

                                // 将该clazz对象放入到BeanDefinition
                                beanDefinition.setClazz(clazz);

                                // 将BeanDefinition对象放入到BeanDefinitionMap
                                beanDefinitionMap.put(beanName, beanDefinition);


                            } else {
                                // 如果该类没有使用@Component 注解 ,说明不是一个Spring bean
                                System.out.println("不是一个Spring bean = " + clazz + " 类名= " + className);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("=============================");
            }
        }
    }


    // 编写方法 返回容器中的对象
    public Object getBean(String name) {

        // 根据传进来的name 去beanDefinitionMap查找 看看是否存在该name对应的beanDefinition
        if (beanDefinitionMap.containsKey(name)) {
            //如果存在就走下面的代码
            //获取对应的beanDefinition 如果可以进来说明一定存在 beanDefinition不会为null
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            //查看该beanDefinition 的Scope属性 为singleton还是prototype
            if ("singleton".equalsIgnoreCase(beanDefinition.getScope())) {
                // 说明是单例的 直接去单例池获取对应的 bean对象并返回
                // 初始化后的单例池中一定有该对象 否则就是初始化单例池是的代码有问题
                return singletonObjects.get(name);
            } else {
                // 多实例的 使用createBean反射创建一个对象并返回
                return createBean(name, beanDefinition);
            }
        } else {
            // 抛出一个异常 说明传进来的name 不存在 是瞎传了一个name
            // 抛出异常可以代替返回值 就不用在返回了 return
            throw new NullPointerException("没有该bean");
        }

        // 抛出一个异常


        //
        //return null;
    }

}
