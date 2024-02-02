package com.hspedu.spring.hspapplicationcontext;

import com.hspedu.spring.bean.Monster;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: 用于模拟Spring ioc 容器
 * 老师解读
 * 1. 这个程序用于实现Spring的一个简单容器机制
 * 2. 后面我们还会详细的实现
 * 3. 这里我们实现如何将beans.xml文件进行解析，并生成对象,放入容器中
 * 4. 提供一个方法 getBean(id) 返回对应的对象
 * 5. 这里就是一个开胃小点心, 理解Spring容器的机制
 * @create 2023-08-31-18:02
 */
public class HspApplicationContext {


    /* ioc 的属性 beanFactory 里有三个重要的属性
         beanDefinitionMap 存放的是beans 里定义的bean信息 包括是不是懒加载 lazyInit
         beanDefinitionNames 存放的是beans中的所有的bean id
         singletonObjects:存放的是beans 里的单例对象 可以称为单例池
    */

    // 定义一个属性  存放对象
    private ConcurrentHashMap<String, Object> singletonObjects =
            new ConcurrentHashMap<>();

    /**
     * 构造器
     * 接收一个容器的配置文件 比如 beans.xml, 该文件默认在src
     * 构造器 完成容器的初始化
     * 参考： ApplicationContext ioc =
     * new ClassPathXmlApplicationContext("beans.xml");
     * 因此 给该构造器提供一个形参 用于获取该形参文件的ioc容器
     */
    public HspApplicationContext(String iocBeanXmlFile) throws Exception {


        //1. 得到类加载路径
        String path = this.getClass().getResource("/").getPath();

        //2. 创建 SaxReader
        // 使用dom4j 读取配置文件 保存 第一个bean 的信息
        SAXReader saxReader = new SAXReader();

        //3. 得到Document对象 读取beans.xml
        //Document document = saxReader.read(path + iocBeanXmlFile);
        Document document = saxReader.read(new File(path + iocBeanXmlFile));
        //4. 得到rootDocument
        Element rootElement = document.getRootElement();
        // 获取根节点下面的bean节点
        List<Element> beans = rootElement.elements("bean");

        //5. 得到第一个bean-monster01
        Element bean = beans.get(0);

        //6. 获取到第一个bean-monster01的相关属性
        /*
           <bean class="com.hspedu.spring.bean.Monster" id="monster01">
           <!--<property name="monsterId" value="1"></property> 使用自闭和标签的方式 -->
           <!--使用自闭和标签的方式 ：-->
           <property name="monsterId" value="1"/>
           <property name="name" value="牛魔王"/>
           <property name="skill" value="牛魔王拳"/>
           </bean>
         */

        String beanFullPath = bean.attributeValue("class");
        String id = bean.attributeValue("id");

        // 获取bean 节点下的property节点元素
        List<Element> propertys = bean.elements("property");

        //遍历->老师简化直接获取
        // 获取property元素的value属性的值
        String monsterId = propertys.get(0).attributeValue("value");
        String name = propertys.get(1).attributeValue("value");
        String skill = propertys.get(2).attributeValue("value");


        //7. 使用反射创建对象.=> 回顾反射机制
        // 得到Monster 类的Class对象
        Class<?> aClass = Class.forName(beanFullPath);
        // 通过Class对象创建Monster对象
        //这里o对象就是Monster对象
        Object o = aClass.newInstance();
        Monster monster = (Monster) o;

        //给o对象赋值
        //反射来赋值=> 这里老师就简化，直接赋值->目的就是先理解流程
        //这里的方法就是setter方法
        //Method[] declaredMethods = aClass.getDeclaredMethods();
        //for (Method declaredMethod : declaredMethods) {
        //    declaredMethod.invoke();
        //}
        //赋值
        monster.setMonsterId(Integer.parseInt(monsterId));
        monster.setName(name);
        monster.setSkill(skill);

        //8. 将创建好的monster对象放入到singletonObjects
        singletonObjects.put(id,monster);


    }


    // 提供一个方法 用于返回bean对象
    public Object getBean(String beanId){
        // 根据传进来的对象id   //这里小伙伴可以在处理
        return singletonObjects.get(beanId);
    }


}
