package com.hspedu2.spring.ioc;

import com.hspedu2.spring.annotation.Autowired;
import com.hspedu2.spring.annotation.Component;
import com.hspedu2.spring.annotation.ComponentScan;
import com.hspedu2.spring.annotation.Scope;
import com.hspedu2.spring.processor.BeanPostProcessor;
import com.hspedu2.spring.processor.InitializingBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-15:45
 */

//1 编写自己的spring容器 扫描包得到bean
public class HspSpringApplicationContext {

    // 首先得到要扫描的包
    // 定义一个属性 用来接收配置文件的clazz对象
    private Class configClass;

    // 定义一个属性用来存放bean的定义信息 用Map集合存放
    private Map<String, BeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>();

    // 定义一个属性 单例池singletonObjects
    private Map<String, Object> singletonObjects =
            new ConcurrentHashMap<>();

    // 定义一个属性 用于存放所有的后置处理器对象
    private List<BeanPostProcessor> beanPostProcessorList =
            new ArrayList<>();

    // 提供构造器 用于初始化容器
    public HspSpringApplicationContext(Class configClass) {
        beanDefinitionsByScan(configClass);

        // 下面进行单例池的初始化
        // 遍历beanDefinitionMap 如果scope属性的值为singleton 就实例化一个对象放入到单例池
        Enumeration keys = ((ConcurrentHashMap) beanDefinitionMap).keys();
        while (keys.hasMoreElements()) {
            String beanName = (String) keys.nextElement();
            // 获取该beanName 在定义的集合中对应的beanDefinition对象
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            // 判断scope是否为单例的
            if ("singleton".equals(beanDefinition.getScope())) {
                // 是单例的 但是 不可以直接使用反射生成实例对象 要走一下方法 createBean
                // 好处是 可以统一进行管理 容器中生成的对象都是通过该方法生成的
                // 获取出对应的clazz对象
                //Class beanClazz = beanDefinition.getBeanClazz();
                Object instance = createBean(beanName,beanDefinition);

                // 将该实例放入到单例池中
                singletonObjects.put(beanName, instance);


            }


        }


    }

    // 通过扫描初始化容器的定义信息
    public void beanDefinitionsByScan(Class configClass) {
        this.configClass = configClass;

        // 获取HspSpringConfig类的注解 @ComponentScan(value = "com.hspedu2.spring.component")
        ComponentScan componentScan = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScan.value();
        //System.out.println("要扫描的包path= " + path);
        //要扫描的包path= com.hspedu2.spring.component

        //要获取该包的绝对路径
        //将.替换为正斜杠/
        path = path.replace(".", "/");
        //System.out.println("替换后的path= " + path);
        //使用类的加载器 得到类的加载路径
        ClassLoader classLoader = HspSpringApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        //System.out.println("resource= " + resource);

        File file = new File(resource.getPath());
        // 判断该file对象 是不是一个目录
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                //System.out.println("文件的绝对路径= " + f.getAbsolutePath());
                String fAbsolutePath = f.getAbsolutePath();
                // 排除掉后缀不是.class的文件
                if (fAbsolutePath.contains(".class")) {
                    // 拿到类名
                    String className = fAbsolutePath.substring(fAbsolutePath.lastIndexOf("\\") + 1, fAbsolutePath.indexOf(".class"));
                    //System.out.println("类名= " + className);

                    // 通过类名和传进来的path拼接成全类名
                    // 首先要把path再次进行替换
                    // 老韩解读: s1.replace() 方法执行后，返回的结果才是替换过的.
                    // 注意对 s1 没有任何影响
                    if (path.contains("/")) {
                        path = path.replace("/", ".");
                    }

                    String fullClassName = path + "." + className;
                    //System.out.println("全类名= " + fullClassName);

                    // 使用类的加载器
                    try {
                        // 得到简易版的clazz对象
                        Class<?> loadClass = classLoader.loadClass(fullClassName);

                        // 判断该类是否有注解@Component 如果有 将该类的信息封装到beanDefinition
                        if (loadClass.isAnnotationPresent(Component.class)) {

                            // 这里都是组件
                            // 先判断该类是不是一个后置处理器 判断该类有没有实现BeanPostProcessor接口
                            // class.isAssignableFrom 判断loadClass 所属的类是否实现了BeanPostProcessor接口
                            if (BeanPostProcessor.class.isAssignableFrom(loadClass)){
                                // 该类实现了后置处理器接口
                                // 实例化对象 放入到后置处理器的集合beanPostProcessorList
                                BeanPostProcessor beanPostProcessor = null;
                                try {
                                    // 后置处理器对象是通过反射直接创建的 为了简化同时也是为了不和bean对象的处理搞混
                                    // 不用放入到beanDefinitionMap 这里的后置处理器对象在扫描的时候就生成了
                                    // 早于所有的bean对象的生成时间 因此不用担心后置处理器晚于容器中的bean对象生成
                                    // 导致生成bean对象 调用createBean方法时 在createBean方法中调用后置处理器对象
                                    // 的Before和after方法时后置处理器对象为null ,扫描时就立即生成了后置处理器对象
                                    // 并放入到了后置处理器集合beanPostProcessorList

                                    // createBean 方法是生成的 容器中的所有的对象
                                    beanPostProcessor = (BeanPostProcessor)loadClass.newInstance();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                beanPostProcessorList.add(beanPostProcessor);
                                continue;
                            }



                            // 判断@Component注解上有没有写值
                            Component component = loadClass.getDeclaredAnnotation(Component.class);
                            // 当作设置值了 将beanName当作key
                            String beanName = component.value();
                            if ("".equals(component.value())) {
                                // 没有设置值 就使用类名首字母小写
                                beanName = StringUtils.uncapitalize(className);
                            }


                            // 创建一个beanDefinition对象 用来封装bean信息
                            BeanDefinition beanDefinition = new BeanDefinition();

                            // 查看类上有没有Scope注解信息
                            if (loadClass.isAnnotationPresent(Scope.class)) {
                                // 有注解@Scope 再看看有咩有 写value
                                // 获取该Scope注解
                                Scope scope = loadClass.getDeclaredAnnotation(Scope.class);

                                // 设置了@Scope注解
                                String value = scope.value();
                                // 判断设置没设置value
                                if ("".equals(value)) {
                                    // 没设置值 给定一个默认值 singleton
                                    beanDefinition.setScope("singleton");
                                } else if ("prototype".equals(value)) {
                                    // 设置的是多实例的
                                    beanDefinition.setScope(value);
                                } else if ("singleton".equals(value)) {
                                    // 设置的是单例的
                                    beanDefinition.setScope(value);

                                } else {
                                    beanDefinition.setScope(value);
                                    System.out.println("类名=" + className + " @Scope设置的value值是瞎写的");
                                }
                            } else {
                                // 没有设置@Scope注解
                                // 给定一个默认值 singleton
                                beanDefinition.setScope("singleton");
                            }

                            //进行反射 得到完整的Class对象 并放入到beanDefinition
                            Class<?> clazz = Class.forName(fullClassName);
                            beanDefinition.setBeanClazz(clazz);

                            // 将beanDefinition放入到集合beanDefinitionMap
                            beanDefinitionMap.put(beanName, beanDefinition);

                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 提供生成对象的方法 createBean 根据传进来的beanDefinition 生成对象
    public Object createBean(String beanName,BeanDefinition beanDefinition) {
        // 该方法不管是单例的还是多实例的 都为其生成对象
        //
        Class beanClazz = beanDefinition.getBeanClazz();
        Object instance = null;

        try {
            instance = beanClazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 这里最适合依赖注入 因为在这里已经生成实例对象了
        // 同时在这里会生成容器中所有的bean
        // 可以在bean对象生成前进行统一处理

        // 下面对当前要返回的实例进行是否进行依赖注入的判断
        // 依据是属性上是否有@Autowired注解
        // 因此需要取出该beanClazz对象上所有的属性
        // 注意要使用 beanClazz.getDeclaredFields(); 方法
        // 该方法取出的是该类中的所有的属性 不管修饰符是什么
        Field[] declaredFields = beanClazz.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            // 判断该属性上是否有@Autowired 注解
            if(declaredField.isAnnotationPresent(Autowired.class)){
                // 有自动装配注解@Autowired
                // 获取当前属性的属性名 根据该属性名 到容器中获取一个bean
                Object bean = getBean(declaredField.getName());

                // 使用反射 进行自动装配
                try {
                    //IllegalAccessException 因为该属性是私有的
                    // 因此 还需要进行反射爆破
                    declaredField.setAccessible(true);
                    // 给instance对象的declaredField属性 设置一个值 bean
                    declaredField.set(instance,bean);
                    //System.out.println("自动装配成功");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 在初始化方法之前调用后置处理器的Before方法
        // 遍历后置处理器集合 调用方法
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
            if (current != null){
                instance = current;
            }
        }




        // 这里已经生成了对象实例 在这里调用初始化方法最为合适
        // 根据该对象的类是否实现了InitializingBean接口 来确定是否调用
        // 初始化方法
        // 因为这里是实例对象 可以使用instanceof 进行判断
        if (instance instanceof InitializingBean){
            // 该实例实现了InitializingBean接口
            // 调用初始化方法
            InitializingBean initializingBean = (InitializingBean) instance;
            try {
                initializingBean.afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 在初始化方法之后调用后置处理器的After方法
        // 遍历后置处理器集合 调用方法 这里传进来的instance
        // 是已经走过postProcessBeforeInitialization 方法的instance
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            if (current != null){
                instance = current;
            }
        }







        return instance;

    }

    // 提供一个根据beanName 返回对象实例的方法getBean
    public Object getBean(String name) {
        // 首先根据传进来的name 去beanDefinitionMap 中查询 看看是否有该name的key
        if (beanDefinitionMap.containsKey(name)) {
            // 该name在定义信息集合beanDefinitionMap 存在
            // 说明有该name 的组件
            // 判断该name的作用范围scope 是singleton 还是 prototype
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            Object instance = null;
            if ("singleton".equals(beanDefinition.getScope())) {
                //是单实例的 直接去单例池中获取 一定可以获取到 否则就是初始化单例池代码有误
                instance = singletonObjects.get(name);
                // 将该对象直接返回
            } else if ("prototype".equals(beanDefinition.getScope())) {
                // 是多实例的 就创建一个对象并返回
                instance = createBean(name,beanDefinition);
            } else {
                throw new RuntimeException("该bean 的 scope value 是瞎输入的");
            }
            return instance;
        } else {
            throw new NullPointerException("该name 不存在 是瞎传了一个name");
        }
    }

}
