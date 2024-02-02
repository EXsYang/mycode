package com.hspedu.furns.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-16-20:00
 */
public abstract class BasicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将子类的doPost() 提到抽象父类BasicServlet
        System.out.println("BasicServlet doPost() 被调用...");

        // 判断form 表单属性是否有 enctype="multipart/form-data" 如果有则
        // 不可以直接通过 req.getParameter("action")
        // 获取到form 表单内的 input name=action 的value



                        // 在获取参数之前设置编码格式
        // 处理中文乱码问题 一定要设置在获取参数之前    在获取参数中间设置编码格式不好使
        req.setCharacterEncoding("utf-8");

        // 取出 action
        String action = req.getParameter("action");
        System.out.println("action= " + action);

        // 通过反射获取 this对象  对应的方法
        // 1.this 对象 就是请求的Servlet   通过动态绑定 这里是实现子类的MemberServlet对象
        // 2.2.declaredMethod 方法对象就是当前请求的servlet对应的"action名字" 的方法, 该方法对象(declaredMethod)
        //System.out.println("this= " + this);//this= com.hspedu.furns.web.MemberServlet@12478698
        //  是变化的,根据用户请求
        //3. 老韩的体会：使用模板模式+反射+动态机制===> 简化多个 if--else if---..

        // 通过反射获取 子类Servlet对象
        Class<? extends BasicServlet> aClass = this.getClass();
        // getDeclaredMethod(action,req,resp); 填入方法名 方法形参的数据类型对应的Class对象
        //Method declaredMethod = aClass.getDeclaredMethod(action,req,resp);// 这里不可以填入 req,resp对象 应该填入 方法形参的数据类型对应的Class对象
        try {
            // 通过反射动态的获取 this对象/当前对象 的相关方法

            // 这里的 this对象是动态的，方法名action也是动态的
            // action 如果为null 下面这行代码空指针异常 getDeclaredMethod()方法中 使用 action.方法 了 ==> name.intern()
            Method declaredMethod = aClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            // 通过 invoke 方法调用 declaredMethod方法对象的方法

            // 说明： invoke方法
            // 第一个参数：是declaredMethod 获取的方法对应的类的对象 即此时是实现子类的MemberServlet对象
            // 此时传入的this 是通过动态绑定 运行类型是子类MemberServlet对象
            // 第二三个参数 是传入方法名为方法action(实际上是login或register等方法)的 实参
            //使用方法对象，进行反射调用
            //Object invoke = declaredMethod.invoke(this, req, resp);

            //int a = 4/0 ;
            declaredMethod.invoke(this, req, resp);
            // 到这里 就已经走通了！！
            //System.out.println("invoke= " + invoke);// invoke= null
            //System.out.println("invoke.getClass()= " + invoke.getClass());// 空指针异常

        } catch (Exception e) {
            // java基础 异常机制
            // 将发生的异常继续抛出
            // 否则doFilter中捕获不到异常 被catch处理后 后面的catch没有机会捕获！
            // 老师心得体会：异常机制是可以参与业务逻辑的
            // 老师把问题暴露出来 让你看到 学到东西
            e.printStackTrace();
            throw new RuntimeException(e);

        }


    }

    //在BasicServlet中, 增加处理Get请求 否则前端页面405
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
