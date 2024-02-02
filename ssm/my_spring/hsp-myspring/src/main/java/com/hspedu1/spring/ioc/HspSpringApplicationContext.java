package com.hspedu1.spring.ioc;

import com.hspedu1.spring.annotation.Autowired;
import com.hspedu1.spring.annotation.Component;
import com.hspedu1.spring.annotation.ComponentScan;
import com.hspedu1.spring.annotation.Scope;
import com.hspedu1.spring.processor.BeanPostProcessor;
import com.hspedu1.spring.processor.InitializingBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-15:45
 */

//1 编写自己的spring容器 扫描包得到bean
public class HspSpringApplicationContext {

    // 扫描包得到bean的class对象，排除不是bean的

    //定义属性configClass 用于接收一个配置类的clazz对象
    private Class configClass;

    //定义属性beanDefinitionMap 用于存放bean定义信息
    private Map<String, BeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>();

    //定义属性 singletonObjects 单例池
    private Map<String, Object> singletonObjects =
            new ConcurrentHashMap<>();


    //定义一个List集合存放后置处理器 简化处理
    private List<BeanPostProcessor> beanPostProcessorList =
            new ArrayList<>();


    //构造器 接收一个配置类的clazz对象
    public HspSpringApplicationContext(Class configClass) {

        beanDefinitionsByScan(configClass);

        // 下面初始化单例池
        // 如果是单例的就创建对象放入到单例池中
        // 遍历bean定义集合 如果是singleton 的就进行实例化 并放入到单例池中
        Set<String> keySet = beanDefinitionMap.keySet();
        for (String beanName : keySet) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                // 说明是单例的 反射生成对象放入到单例池中

                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }


        }


    }

    // 定义一个根据beanDefinition生成对象的方法createBean
    public Object createBean(String beanName, BeanDefinition beanDefinition) {

        // 该方法不管是单例的还是多实例的 只要是传进来一个beanDefinition
        // 就会生成对象

        Class beanClazz = beanDefinition.getBeanClazz();
        Object instance = null;
        try {
            instance = beanClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 这里已经生成了bean对象 此处适合进行依赖注入
        // 判断该对象是否需要进行依赖注入 属性上是否有注解@Autowired
        // 获取该类的所有的属性 进行遍历
        // 这里的monsterService对象什么属性也没拿到？！
        //getFields:获取所有 public 修饰的属性，包含本类以及父类的
        // Field[] fields = beanClazz.getFields();

        //getDeclaredFields:获取本类中所有属性 这包括公共，受保护，默认（包）访问和私有字段，但不包括继承的字段。
        // 注意此处要使用beanClazz.getDeclaredFields(); 拿到声明的属性

        Field[] declaredFields = beanClazz.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            //System.out.println("declaredField= " + declaredField);
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                //说明该属性field 需要进行自动装配/自动依赖注入
                // 获取该属性的属性名
                String fieldName = declaredField.getName();
                //System.out.println("fieldName= " + fieldName);
                //根据属性的属性名 去容器中获取该对象
                Object bean = getBean(fieldName);
                //使用反射 给对象instance 注入依赖对象bean
                try {
                    // 此处的set 不是instance对象对应的类 中生成的setter方法
                    // 而是反射直接给属性装配值的一个set方法

                    // 因为属性是私有的 所以需要进行反射暴破
                    declaredField.setAccessible(true);

                    declaredField.set(instance, bean);//通过反射来操作属性
                    //System.out.println("自动装配成功！");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 在初始化方法前调用后置处理器的Before方法
        // 遍历后置处理器集合
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            // 调用后置处理器的Before方法
            Object current = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            if (current != null) {
                instance = current;
            }

        }


        // 这里已经生成了bean实例了 判断是否需要调用初始化方法
        // 如果该对象对应的类实现了InitializingBean接口 就调用初始化方法
        if (instance instanceof InitializingBean) {
            // 说明该对象实现了InitializingBean接口
            try {
                ((InitializingBean) instance).afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 在初始化方法后调用后置处理器的After方法
        // 遍历后置处理器集合
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            // 调用后置处理器的Before方法 这里传进去的instance
            // 有可能是上面postProcessBeforeInitialization()修改过的
            Object current = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            if (current != null) {
                instance = current;
            }

        }


        // 将生成的对象返回
        return instance;

    }

    // 定义一个根据beanName 返回容器中bean对象的方法getBean
    public Object getBean(String name) {
        // 上来先去定义Bean信息的Map去查找 看传进来的name是否存在
        if (beanDefinitionMap.containsKey(name)) {
            //System.out.println("beanName= " + name + " 定义信息存在 是一个组件");
            // 如果在定义Bean信息的Map 找到了 判断该bean是否为单例的
            // 是单例singleton的就直接从单例池中获取该bean 并返回
            // 此时单例池中一定有该单例对象 否则就是初始化单例池是代码的问题
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            if ("singleton".equals(beanDefinition.getScope())) {
                //是单例 从单例池直接获取并返回
                Object singletonBean = singletonObjects.get(name);
                return singletonBean;
            } else if ("prototype".equals(beanDefinition.getScope())) {
                //是多实例的 创建一个对象并返回
                Object prototypeBean = createBean(name, beanDefinition);
                return prototypeBean;
            } else {
                throw new RuntimeException("该scope的value有问题");
            }

        } else {
            throw new NullPointerException("定义信息不存在 是瞎传了一个name");
        }


        //return null;
    }


    // 通过扫描初始化容器的定义信息
    public void beanDefinitionsByScan(Class configClass) {
        this.configClass = configClass;

        // 构造器初始化时 扫描指定的包 指定的包的路径在传进来的configClass对象中获取
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            // 如果传进来的配置类的class对象 类上有@ComponentScan注解
            // 就进来进行下一步的解析
            // 解析该注解上的value
            // 拿到该注解ComponentScan
            ComponentScan componentScan = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);

            //拿到注解上的value 注意 需要转成ComponentScan才可以点出value
            String path = componentScan.value();

            //System.out.println("要扫描的包= " + path);
            // 要扫描的包= com.hspedu1.spring.component

            // 通过类的加载器拿到要扫描的包的全路径
            // 类的加载器使用的是正斜杠 因此需要进行替换一下
            path = path.replace(".", "/");
            ClassLoader classLoader = HspSpringApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            //System.out.println("要扫描的包的全路径= " + resource);

            // 创建一个file对象
            File file = new File(resource.getFile());
            // 判断这个文件是否是一个文件目录
            if (file.isDirectory()) { // 测试此抽象路径名表示的文件是否为目录。
                // 是一个文件目录 取出单个文件
                // file.listFiles() : 如果此抽象路径名不表示目录，则此方法返回null 。
                // 否则将返回一个File对象的数组，一个用于目录中的每个文件或目录。
                File[] files = file.listFiles();
                for (File file1 : files) {
                    //System.out.println("单个文件的绝对路径= " + file1.getAbsolutePath());
                    //用绝对路径拿到文件名
                    String absolutePath = file1.getAbsolutePath();

                    // 判断 该文件是否以.class 结尾 不能保证该文件夹下的所有文件都是.class文件！因此需要进行判断
                    if (absolutePath.endsWith(".class")) {


                        String className = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.indexOf(".class"));
                        //System.out.println("文件名= " + className);
                        // 用得到的文件名再拼接出文件的全路径 用于反射生成clazz对象
                        // 因为反射使用的全路径是包名.包名.包名.类名 且没有后缀.class 的形式
                        // 将替换过的path 再替换回去
                        if (path.contains("/")) {
                            path = path.replace("/", ".");
                            //System.out.println("进行替换");
                        }

                        //System.out.println("替换回去的path= " + path);
                        String fullClassPath = path + "." + className;
                        //System.out.println("全类名= " + fullClassPath);

                        // 进行反射 生成对象
                        try {
                            Class<?> loadClass = classLoader.loadClass(fullClassPath);
                            // 判断类上是否有注解@ComponentScan
                            // 注意 获取注解的类对象不可以加@
                            if (loadClass.isAnnotationPresent(Component.class)) {
                                // 说明是组件 此时生成对象 封装bean类的定义信息到BeanDefinition
                                // 并放入到定义的集合beanDefinitionMap中
                                //System.out.println("类" + className + "是一个组件");
                                Class<?> beanClass = Class.forName(fullClassPath);

                                // 在这里已经拿到了类的clazz对象 判断该类有没有实现BeanPostProcessor接口
                                // 如果该类实现了BeanPostProcessor接口
                                // 就把该类生成的对象放入到后置处理器集合beanPostProcessorList
                                // 同时直接跳出本次循环 不往下走了 即不生成该类对应的beanDefinition
                                // 也不放入到beanDefinitionMap 初始化单例池时 就不会被放入到单例池
                                // BeanPostProcessor.class.isAssignableFrom(beanClass) 这句话的意思是,
                                // beanClass对应的类实现了BeanPostProcessor接口

                                if (BeanPostProcessor.class.isAssignableFrom(beanClass)) {
                                    try {
                                        // 生成实例
                                        BeanPostProcessor beanPostProcessor = (BeanPostProcessor) beanClass.newInstance();
                                        //放入后置处理器List集合
                                        beanPostProcessorList.add(beanPostProcessor);
                                        //跳出本次循环
                                        continue;
                                    } catch (InstantiationException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }

                                // 得到Component注解的value 如果设置了值 就把该值作为key
                                // 如果没有设置值 就使用类名首字母小写作为BeanDefinitionMap的key
                                Component component = beanClass.getDeclaredAnnotation(Component.class);
                                String beanName = component.value();
                                if ("".equals(beanName)){
                                    // 没设置值 就把类名首字母小写作为beanName
                                    beanName = StringUtils.uncapitalize(className);
                                }

                                //创建BeanDefinition实例 用于封装bean的定义信息
                                BeanDefinition beanDefinition = new BeanDefinition();
                                //将这个beanClass对象封装到一个beanDefinition
                                beanDefinition.setBeanClazz(beanClass);

                                // 获取类的作用范围 Scope
                                if (beanClass.isAnnotationPresent(Scope.class)) {
                                    //说明设置了Scope注解 读取Scope value
                                    Scope scope = beanClass.getDeclaredAnnotation(Scope.class);
                                    String scopeValue = scope.value();
                                    if ("".equals(scopeValue)) {
                                        //说明没有设置值 就设置为默认值singleton
                                        beanDefinition.setScope("singleton");
                                    } else {
                                        //说明设置值了 将该值封装到beanDefinition
                                        beanDefinition.setScope(scopeValue);
                                    }
                                } else {
                                    //说明没有设置了Scope注解 就使用默认值
                                    beanDefinition.setScope("singleton");

                                }
                                //beanDefinitionMap.put(StringUtils.uncapitalize(className), beanDefinition);
                                beanDefinitionMap.put(beanName, beanDefinition);

                            } else {
                                //System.out.println("类" + className + "不是一个组件");
                            }

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }

        }
    }
}
