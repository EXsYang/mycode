package com.hspedu.tomcat;

import com.hspedu.tomcat.handler.HspRequestHandler;
import com.hspedu.tomcat.servlet.CallServlet;
import com.hspedu.tomcat.servlet.HspHttpServlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: 通过 反射 + xml来初始化容器
 * @create 2023-06-04-22:08
 */
public class HspTomcatV3 {

    //1. 存放容器 servletMapping
    // -ConcurrentHashMap
    // -HashMap
    // key            - value
    // ServletName    对应的实例

    public static final ConcurrentHashMap<String, HspHttpServlet>
            servletMapping = new ConcurrentHashMap<>();


    //2容器 servletUrlMapping
    // -ConcurrentHashMap
    // -HashMap
    // key                    - value
    // url-pattern       ServletName

    public static final ConcurrentHashMap<String, String>
            servletUrlMapping = new ConcurrentHashMap<>();


    public static final ConcurrentHashMap<String, Filter>
            filterMapping = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, String>
            filterUrlMapping = new ConcurrentHashMap<>();




    public static void main(String[] args) throws IOException {

        System.out.println("========");
        URL resource = HspTomcatV3.class.getResource("/");
        System.out.println("Maven 项目下真实的类加载路径= " + resource.getPath());

        /*
        tomcat本身是有servlet的jar 的，tomcat 运行 可以找到HttpServlet


        下面这行代码 会抛出异常
        Exception in thread "main" java.lang.NoClassDefFoundError:
        javax/servlet/http/HttpServlet

        public class CallServlet extends HttpServlet {}
        运行时找不到 类HttpServlet

        https://www.ibashu.cn/news/show_118694.html
        NoClassDefFoundError错误的发生，是因为Java虚拟机在编译时能找到合适的类，而在运行时不能找到合适的类导致的错误。例如在运行时我们想调用某个类的方法或者访问这个类的静态成员的时候，发现这个类不可用，此时Java虚拟机就会抛出NoClassDefFoundError错误。与ClassNotFoundException的不同在于，这个错误发生只在运行时需要加载对应的类不成功，而不是编译时发生。很多Java开发者很容易在这里把这两个错误搞混。
        */

        // 如果写了下面的代码也会报错 NoClassDefFoundError
        //HttpServlet callServlet = new CallServlet();
        //System.out.println("callServlet=" + callServlet);


        HspTomcatV3 hspTomcatV3 = new HspTomcatV3();
        //对容器进行初始化
        hspTomcatV3.init();

        // 在8080端口进行监听，获取http请求
        ServerSocket serverSocket = new ServerSocket(8088);
        //
        //// serverSocket没有关闭就一直监听
        while (!serverSocket.isClosed()){
            System.out.println("========HspTomcatV2 正在监听8080端口=======");
            Socket socket = serverSocket.accept();
            System.out.println("==========连接成功===========");




            new Thread(new HspRequestHandler(socket)).start();
        }

    }

    //直接对两个容器进行初始化
    public void init() {
        //读取web.xml => dom4j =>
        //得到web.xml文件的路径 => 拷贝一份. web.xml文件到工作目录target\classes下
        /*注意：在main/resources目录下的资源会自动拷贝到工作目录target\classes下*/
        //URL resource = HspTomcatV3.class.getResource("/");// 得到的是工作目录，而不是源码目录
        String path = HspTomcatV3.class.getResource("/").getPath();// 得到的是工作目录，而不是源码目录
        System.out.println("path= " + path);

        // 使用dom4j 读取
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));
            System.out.println("document= " + document);
            //得到根节点 <web-app>
            Element rootElement = document.getRootElement();
            System.out.println("rootElement=" + rootElement);
            // 遍历根元素下面的所有节点元素
            List<Element> elements = rootElement.elements(); // 不写参数取出来的servlet_name=null,
            // 即将xml中根节点下一级全部节点取出
            //List<Element> elements = rootElement.elements("servlet");
            System.out.println("elements= " + elements);
            for (Element element : elements) {
                //Element servlet_name = element.element("servlet-name");
                //System.out.println("servlet-name=" + servlet_name);//null

                //if ("display-name".equalsIgnoreCase(element.getName())){
                //    // 是display-name
                //    System.out.println("发现 display-name");
                //}else

                //if ("servlet".equalsIgnoreCase(element.element("servlet").getText())){
                if ("servlet".equalsIgnoreCase(element.getName())) {
                    // 是servlet
                    System.out.println("发现 servlet");
                    // 使用反射将servlet实例放入servletMapping
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMapping.put(servletName.getText(), (HspHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());

                    //}else if ("servlet-mapping".equalsIgnoreCase(element.element("servlet-mapping").getText())){
                } else if ("servlet-mapping".equalsIgnoreCase(element.getName())) {
                    // 是servlet-mapping
                    System.out.println("发现 servlet-mapping");
                    // 使用反射将 放入servletUrlMapping
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUrlMapping.put(urlPattern.getText(), servletName.getText());

                }
            }
        } catch (Exception e) {
            System.out.println("出异常");
            e.printStackTrace();
        }

        System.out.println("servletMapping= " + servletMapping);
        System.out.println("servletUrlMapping= " + servletUrlMapping);
    }


}
