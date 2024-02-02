package com.hspedu.spring.test;


import com.hspedu.spring.bean.*;
import com.hspedu.spring.component.MyComponent;
import com.hspedu.spring.component.UserAction;
import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.component.UserService;
import com.hspedu.spring.depinjection.BookDao;
import com.hspedu.spring.depinjection.BookService;
import com.hspedu.spring.service.MemberServiceImpl;
import com.hspedu.spring.web.OrderAction;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * @author yangda
 * @description:
 * @create 2023-08-30-14:33
 */
public class SpringBeanTest {


    //
    @Test
    public void testGetBeanByInterface(){

        // 当spring 容器开启了 基于注解的AOP功能 getBean() 方法类似于自己写的getProxy()
        // 返回的是代理对象 不管是用 类型还是id 去获取 返回的都是代理对象！！！
        // 不是之前的getBean() 了 类似于进行了一次包裹


        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 也可以通过接口 获取 获取到的是实现类的对象 就是在容器中的那个对象
        // 要求容器中只有一个实现子类
        // 有多个会抛异常  expected single matching bean but found 2: xxx,xxxx
        Human human = ioc.getBean(Human.class);
        System.out.println("human 运行类型= " + human.getClass() + " hash=" +human);

        //human 运行类型= class com.hspedu.spring.test.Man
        //expected single matching bean but found 2: com.hspedu.spring.test.Man#0,com.hspedu.spring.test.Man#1






    }



    //测试 通过泛型依赖注入配置bean
    @Test
    public void setPropertyByDependencyInjection() {

        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans07.xml");

        //BookService bookService = new BookService();
        //bookService.save();

        BookService bookService = ioc.getBean("bookService", BookService.class);
        bookService.save();

        //

        System.out.println("ok");


    }

    //测试 通过注解配置bean
    @Test
    public void setPropertyByAutowired() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans06.xml");

        UserService userService = ioc.getBean("userService", UserService.class);
        System.out.println("ioc容器中的userService=" + userService);

        UserService userService200 = ioc.getBean("userService200", UserService.class);
        System.out.println("ioc容器中的userService200=" + userService200);

        UserAction userAction = ioc.getBean("userAction", UserAction.class);
        System.out.println("userAction=" + userAction);
        userAction.sayOk();




    }
    //测试 通过注解配置bean
    @Test
    public void setBeanByAnnotation() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans05.xml");

        UserDao userDao = ioc.getBean(UserDao.class);
        UserService userService = ioc.getBean(UserService.class);
        UserAction userAction = ioc.getBean(UserAction.class);
        MyComponent myComponent = ioc.getBean(MyComponent.class);

        System.out.println("ok");

        System.out.println("userDao= " + userDao);
        System.out.println("userService= " + userService);
        System.out.println("userAction= " + userAction);
        System.out.println("myComponent= " + myComponent);


    }
    //测试 通过spring el 给bean属性赋值
    @Test
    public void setBeanBySpEL() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans04.xml");

        SpELBean spELBean = ioc.getBean("spELBean", SpELBean.class);

        System.out.println("spELBean= " + spELBean);


    }
    //测试 通过自动装配给bean属性赋值
    @Test
    public void setBeanByAutowire() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans03.xml");

        OrderAction orderAction = ioc.getBean("orderAction", OrderAction.class);

        //验证是否自动装配上OrderService
        System.out.println("orderAction.getOrderService()= " + orderAction.getOrderService());
        //验证是否自动装配上OrderDAO
        System.out.println("orderAction.getOrderService().getOrderDao()= " + orderAction.getOrderService().getOrderDao());


    }
    //测试 通过属性文件给bean属性赋值
    @Test
    public void setBeanByPropertiesFile() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans03.xml");

        Monster monster1000 = ioc.getBean("monster1000", Monster.class);

        System.out.println("monster1000= " + monster1000);


    }


    //测试 后置处理器
    @Test
    public void testBeanPostProcessor() {
        //后置处理器 可以配置多个
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans02.xml");

        House house = ioc.getBean("house", House.class);
        House house2 = ioc.getBean("house2", House.class);

        System.out.println("使用house= " + house);
        System.out.println("使用house2= " + house2);

        ((ConfigurableApplicationContext)ioc).close();
    }


        //测试Bean创建 生命周期
    @Test
    public void testBeanLifecycle() {


        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        House house = ioc.getBean("house", House.class);
        System.out.println("house使用 " + house);

        //ClassPathXmlApplicationContext
        //关闭容器
        //1. 这里又要考察大家的java基础
        //2. ioc的编译类型 ApplicationContext , 运行类型 ClassPathXmlApplicationContext
        //3. 因为ClassPathXmlApplicationContext 实现了 ConfigurableApplicationContext
        //4. ClassPathXmlApplicationContext 是有close
        //5. 将ioc 转成ClassPathXmlApplicationContext,再调用close也可以 但是尽量转成一个接口 更加灵活
        //ioc.close();
        //关闭ioc容器.

        ((ConfigurableApplicationContext) ioc).close();
    }

    //测试Bean创建顺序
    @Test
    public void testBeanScope() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Cat cat = ioc.getBean("cat", Cat.class);
        Cat cat2 = ioc.getBean("cat", Cat.class);
        Cat cat3 = ioc.getBean("cat", Cat.class);

        System.out.println("cat= " + cat);
        System.out.println("cat2= " + cat2);
        System.out.println("cat3= " + cat3);

    }

    //测试Bean创建顺序
    @Test
    public void testBeanByCreate() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        System.out.println("ok");

    }
    //配置Bean通过继承
    @Test
    public void getBeanByExtends() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster10 = ioc.getBean("monster10", Monster.class);
        System.out.println("monster10=" + monster10);

        Monster monster11 = ioc.getBean("monster11", Monster.class);
        System.out.println("monster11=" + monster11);

        //如果bean指定了 abstract="true", 表示该bean对象, 是用于被继承 获取会报错=> BeanIsAbstractException
        //Monster monster12 = ioc.getBean("monster12", Monster.class);

        Monster monster13 = ioc.getBean("monster13", Monster.class);
        System.out.println("monster13=" + monster13);



    }
    //使用FactoryBean 获取bean对象
    @Test
    public void getBeanByFactoryBean() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster05 = ioc.getBean("my_monster05", Monster.class);

        System.out.println("my_monster05=" + my_monster05);

    }
    //使用实例工厂 获取bean对象
    @Test
    public void getBeanByInstanceFactory() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster02 = ioc.getBean("my_monster02", Monster.class);
        Monster my_monster03 = ioc.getBean("my_monster02", Monster.class);

        Monster my_monster04 = ioc.getBean("my_monster03", Monster.class);
        System.out.println("my_monster02=" + my_monster02);

        // 虽然获取了两次 但是使用的仍然是同一个实例工厂对象 同一个实例工厂对象中的monster03对象仍然是同一个
        System.out.println(my_monster02 == my_monster03); //true

        // 这里使用的是配置了两个不同的实例工厂对象 都去获取monster03对象
        // 虽然获取到的monster03对象的各个属性的值是相同的
        // 但是 是不同的monster对象 只是属性值相同而已
        System.out.println(my_monster02 == my_monster04); //false
        System.out.println(my_monster02);
        System.out.println(my_monster04);





    }
    //使用静态工厂 返回bean对象
    @Test
    public void getBeanByStaticFactory() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster01 = ioc.getBean("my_monster01", Monster.class);
        Monster my_monster02 = ioc.getBean("my_monster01", Monster.class);


        Monster my_monster04 = ioc.getBean("my_monster04", Monster.class);
        System.out.println("my_monster01=" + my_monster01);

        // 不管获取了多少次返回的都是同一个对象
        System.out.println( my_monster01 == my_monster02); //true

        // 这里是配置了两份 使用静态工厂对象获取同一个monster02对象时 检验是否获取的是同一个对象的测试
        System.out.println( my_monster01 == my_monster04); //true

    }

    //给属性进行级联赋值
    @Test
    public void setBeanByRelation() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Emp emp = ioc.getBean("emp", Emp.class);
        System.out.println("emp=" + emp);

    }

    //使用util:list名称空间给属性赋值
    @Test
    public void setBeanByUtilList() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        BookStore bookStore2 = ioc.getBean("bookStore2", BookStore.class);
        System.out.println("bookStore2=" + bookStore2);

    }

    //给集合数组属性进行赋值
    @Test
    public void setBeanByCollection() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Master master = ioc.getBean("master", Master.class);
        System.out.println("master=" + master);

    }
    //通过内部bean 设置属性 之间的依赖关系
    @Test
    public void setBeanByPro() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

        // spring默认情况下是单例的 会放在单例池中 即在beanFactory singletonObjects 中
        MemberServiceImpl memberService2 = ioc.getBean("memberService2", MemberServiceImpl.class);

        memberService2.add();

    }
    //通过ref来设置bean属性 之间的依赖关系
    @Test
    public void setBeanByRef() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

        // spring默认情况下是单例的 会放在单例池中 即在beanFactory singletonObjects 中
        MemberServiceImpl memberService = ioc.getBean("memberService", MemberServiceImpl.class);

        memberService.add();

    }

    //通过p名称空间来设置属性
    @Test
    public void setBeanByP() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");


        Object monster06 = ioc.getBean("monster06");
        //Monster monster06 = ioc.getBean("monster06",Monster.class);

        System.out.println("monster06=" + monster06);

    }

    //通过构造器来设置属性
    @Test
    public void setBeanByConstructor() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");


        Monster monster03 = ioc.getBean("monster03", Monster.class);
        Monster monster04 = ioc.getBean("monster04", Monster.class);
        Monster monster05 = ioc.getBean("monster05", Monster.class);

        System.out.println("monster03=" + monster03);
        System.out.println("monster03.name=" + monster03.getName());

        System.out.println("monster04=" + monster04);
        System.out.println("monster05=" + monster05);
    }

    @Test
    public void getBeanByType() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

       /* 1. 按类型来获取 bean, 要求 ioc 容器中的同一个类的 bean 只能有一个(即使是其他同一个类的bean给了id也不行，
             只能有一个！！！), 否则会抛出异常
             NoUniqueBeanDefinitionException
          2. 这种方式的应用场景：比如 XxxAction/Servlet/Controller, 或 XxxService 在一个线程
             中只需要一个对象实例(单例)的情况
          3. 老师这里在说明一下: 在容器配置文件(比如 beans.xml)中给属性赋值, 底层是通过
             setter 方法完成的, 这也是为什么我们需要提供 setter 方法的原因
        */
        Monster bean = ioc.getBean(Monster.class);

        System.out.println("bean=" + bean);
        System.out.println("bean.name=" + bean.getName());
    }

    @Test
    public void getBeanByIdOrByIdAndType() {

        // 1.创建容器 ApplicationContext 最好使用接口接收
        // 2.该容器和容器配置文件关联
        // 这里的ClassPath 是指类路径 有些地方也把它称为类加载路径
        // 程序运行后它的工作路径其实是发生变化了 不在src开发目录下 是在out目录下
        // 它会在指定的默认的类加载路径去读取这个文件beans.xml
        //
        /*
            开发目录src 对应out/production/spring目录 而out/production/spring目录又是
            默认的类加载路径，即会默认读取该路径下的文件，就出现一个对应关系
            开发目录src => out/production/spring
            默认会把src 当作类的加载路径 但是程序运行过后 真实走的是out目录那条线
            因此 在src目录下的文件beans.xml 直接写在
            new ClassPathXmlApplicationContext("beans.xml") 构造器里 会被默认读取到
         */
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        //3. 通过getBean获取对应的对象
        //   默认返回的是Object , 但是运行类型Monster
        //Object monster01 = ioc.getBean("monster01");
        Monster monster01 = (Monster) ioc.getBean("monster01");

        /* ioc 的属性 beanFactory 里有三个重要的属性
         beanDefinitionMap 存放的是beans 里定义的bean信息 包括是不是懒加载 lazyInit
         beanDefinitionNames 存放的是beans中的所有的bean id
         singletonObjects:存放的是beans 里的单例对象 可以称为单例池


*/


        //4. 输出
        System.out.println("monster01=" + monster01 + " 运行类型=" + monster01.getClass());
        System.out.println("monster01=" + monster01 + "属性name=" + monster01.getName() +
                " monsterId=" + monster01.getMonsterId());

        //5. 也可以再获取的时候，直接指定Class类型, 可以再次获取
        Monster monster011 = ioc.getBean("monster01", Monster.class);
        System.out.println("monster011=" + monster011 + " monster011.name=" + monster011.getName() +
                " monster011.skill=" + monster011.getSkill());


        System.out.println("========================");
        // 输出beans里面配置的所有的bean id
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName= " + beanDefinitionName);
        }


        System.out.println("========================");


        System.out.println("ok~~~");
    }

    //验证类加载路径
    @Test
    public void classPath() {


        File file = new File(this.getClass().getResource("/").getPath());
        //看到类的加载路径
        System.out.println("file=" + file);
        // file=D:\Java_developer_tools\ssm\my_spring\spring\out\production\spring
    }

}
