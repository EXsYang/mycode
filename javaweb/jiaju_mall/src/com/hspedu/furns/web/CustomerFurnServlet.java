package com.hspedu.furns.web;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import com.hspedu.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-29-23:51
 */
public class CustomerFurnServlet extends BasicServlet {
    // 提供furnService属性
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 首页分页方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CustomerFurnServlet page() 被调用...");

        // 获取数据库中的数据 进行分页显示
        //furnService.page();
        //下面的代码是没有进行任何dml操作时进行分页显示的代码
        //是首次进行分页的页面显示请求

        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        // 如果前端没有给pageSize参数，默认给一个 4
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), 4);

        Page<Furn> page = furnService.page(pageNo, pageSize);

        System.out.println("获取到page对象= " + page);

        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        request.setAttribute("page", page);
        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);


    }

    /**
     * 按照 家居名分页
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这里的业务逻辑和后台分页显示家居信息非常相似
        System.out.println("CustomerFurnServlet pageByName() 被调用...");

        // 上来先判断是否登录过
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        if (username != null){
            // 说明该用户登录过
            // 将登录过的信息传给index.jsp 进行判断
            System.out.println("当前用户已经登录过");

        }


        // 获取数据库中的数据 进行分页显示
        //furnService.page();
        //下面的代码是没有进行任何dml操作时进行分页显示的代码
        //是首次进行分页的页面显示请求

        //简约风格小椅子
        // 获取前端传过来的分页参数name 按照家居名分页
        //老师解读
        //1.如果参数有name 带上没有值, 接收到的是""
        //2.如果name参数都没有, 接收到的null
        //3.也就是说我们把 "" 和 null 业务逻辑合并在一起..
        String name = req.getParameter("name");
        if (null == name) {
            name = "";
        }


        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        // 如果前端没有给pageSize参数，默认给一个
        //int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), 4);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用service方法, 获取Page对象
        // 如果name为空 或name=null 就查询数据库表中的所有数据
        // 并按照这里传入的pageNo返回 对应页数的page对象
        Page<Furn> page = furnService.pageByName(name, pageNo, pageSize);

        // 设置url 修改前端页面url

        // 将搜索的家居名通过page对象的url属性带给前端
        StringBuilder url = new StringBuilder("customerFurnServlet?action=pageByName");

        // 如果传入的name不为空 即是通过搜索框 传入了搜索内容 传过来值了
        // 就走按name分页的逻辑 在url后拼接上name的值 带给前端 因为前端的分页需要用到这个name
        // 放在生成的页码a标签href中
        if (!"".equals(name)){ // 如果name不为"", 则在拼接 name参数
            url.append("&name=").append(name);
        }
        //else {
        //    url.append("&name=");
        //}
        // 将url设置给page对象
        page.setUrl(url.toString());




        System.out.println("获取到按照家居名分页的page对象= " + page);

        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        req.setAttribute("page", page);

        // 将搜索的家居名带给前端 如果有此属性说明 是通过家居名搜索得到的分页
        //req.setAttribute("name", name);


        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);


    }
}
