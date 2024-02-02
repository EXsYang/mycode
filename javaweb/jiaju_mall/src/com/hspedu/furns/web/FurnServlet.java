package com.hspedu.furns.web;

import com.google.gson.Gson;
import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import com.hspedu.furns.utils.DataUtils;
import com.hspedu.furns.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:33
 */
public class FurnServlet extends BasicServlet {
    // 提供FurnService 属性
    private FurnService furnService = new FurnServiceImpl();

    ///**
    // * 自己第一次写的 转发给的文件是/pages/manager/furn_manage.jsp 去看
    // * @param request
    // * @param response
    // * @throws ServletException
    // * @throws IOException
    // */
    //protected void furns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    System.out.println("FurnServlet furns() 被调用...");
    //    int countRequest = 0;
    //    countRequest = ++countRequest; // 这里注意正常不这样写 因为后加加这种写法加不上
    //
    //    // 将FurnList 返回给前端页面请求转发 并在前端动态展示出来
    //
    //    List<Furn> furns = furnService.furnList();
    //
    //    if (furns != null){
    //        System.out.println("数据库有家居信息 在request域对象中放入返回的list信息 并进行请求转发到furn_manage.jsp");
    //
    //        // 将返回的List信息 放入到request域中
    //        request.setAttribute("list",furns);
    //
    //        int count = 0;
    //        for (Furn furn : furns) {
    //            // 将返回的List 中的每一条家居数据 放入到request域中
    //            request.setAttribute("furn" + (++count),furn);
    //        }
    //
    //        request.setAttribute("count",count); //记录 数据库有几条家居记录 是循环到最后的值
    //        request.setAttribute("countRequest",countRequest); //记录 访问FurnServlet的次数
    //
    //        //请求转发到 furn_manage.jsp
    //        //System.out.println("请求转发到 furn_manage.jsp");
    //        // 直接请求 http://localhost:8080/jiaju_mall/furnServlet?count=&action=furns 是第一次访问furn_manage.jsp
    // 第一次请求时没报错是因为 jstl forEach 语句没有进去！
    //        会在下面的try中 进行页面转发 是第二次访问furn_manage.jsp
    // 转发到furn_manage.jsp 报 InvocationTargetException问题 是因为
    //            转发到furn_manage.jsp 时是request域对象中设置好了属性 是带着数据回去的
    //            可以进入jstl forEach  语句<c:forEach items="${requestScope.list}" var="furn">
    // 此时程序可以走到 ${furn.product_name} 在进行反射时会报错InvocationTargetException
    //        try {
    //            request.getRequestDispatcher("/pages/manager/furn_manage.jsp").forward(request,response);
    //        } catch (ServletException e) {
    //            System.out.println("furns 请求转发出现异常了");
    //            throw new RuntimeException();
    //        }
    //
    //        //System.out.println("不请求转发到 furn_manage.jsp");
    //
    //    }else{
    //        System.out.println("数据库没有家居信息 进行重定向");
    //        //重定向到 furn_manage.jsp
    //        response.sendRedirect(request.getContextPath() + "/pages/manager/furn_manage.jsp");
    //
    //    }
    //
    //
    //}

    /**
     * 检查库存
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FurnServlet checkStock() 被调用...");


    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet list() 被调用...");


        // 将FurnList 返回给前端页面请求转发 并在前端动态展示出来
        List<Furn> furns = furnService.furnList();

        if (furns != null) {
            System.out.println("数据库有家居信息 在request域对象中放入返回的list信息 并进行请求转发到furn_manage.jsp");

            // 将返回的List信息 放入到request域中
            request.setAttribute("furns", furns);


            //int count = 0;
            //        for (Furn furn : furns) {
            //            // 将返回的List 中的每一条家居数据 放入到request域中
            //            request.setAttribute("furn" + (++count),furn);
            //        }

            //请求转发到 furn_manage.jsp
            //System.out.println("请求转发到 furn_manage.jsp");
            // 直接请求 http://localhost:8080/jiaju_mall/furnServlet?&action=list 会在这里再次进行页面转发
            try {
                request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
                //request.getRequestDispatcher("/pages/manager/furn_manage.jsp").forward(request,response);
            } catch (ServletException e) {
                System.out.println("list 请求转发出现异常了");
                throw new RuntimeException();
            }

            //System.out.println("不请求转发到 furn_manage.jsp");

        } else {
            System.out.println("数据库没有家居信息 进行重定向");
            //重定向到 furn_manage.jsp
            response.sendRedirect(request.getContextPath() + "/views/manage/furn_manage.jsp");

        }


    }
    // FurnServlet 中不用重新doGet() 直接在父类BasicServlet中写doGet()就可以了
    // FurnServlet 类提供相关的功能方法即可 get/post请求的处理交给父类BasicServlet
    //protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    doPost(request, response);
    //}


    ///**
    // * 添加家居
    // *
    // * @param req
    // * @param resp
    // * @throws ServletException
    // * @throws IOException
    // */
    //protected void addFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    //<td class="product-name"><input name="name" style="width: 60%" type="text" value="Name"/></td>
    //    //<td class="product-name"><input name="maker" style="width: 90%" type="text" value="蚂蚁家居"/></td>
    //    //<td class="product-price-cart"><input name="price" style="width: 90%" type="text" value="60.00"/></td>
    //    //    <input name="sales" style="width: 90%" type="text" value="100"/>
    //    //</td>
    //    //    <input name="stock" style="width: 90%" type="text" value="80"/>
    //
    //    // imgPath 暂时没有传过来
    //
    //    // 处理中文乱码问题 竟然没有处理掉中文乱码问题！！？
    //    // 原因：前面的Basic 已经有了获取参数操作了所以在中间进行设置不生效！！
    //    // 要设置在获取参数之前！！
    //    //req.setCharacterEncoding("utf-8");
    //
    //    String name = req.getParameter("name"); //商品名
    //    System.out.println("从浏览器接收到的 商品名：" + name); //new?°???????
    //    String maker = req.getParameter("maker"); //制造商
    //    String price = req.getParameter("price"); //价格
    //    // 转为BigDecimal对象
    //    BigDecimal bigDecimalPrice = new BigDecimal(price);
    //
    //    String sales = req.getParameter("sales"); //销量
    //    String stock = req.getParameter("stock"); //库存
    //
    //
    //    // 构建Furn对象
    //    Furn furn = new Furn(null, name, maker, bigDecimalPrice, Integer.parseInt(sales), Integer.parseInt(stock), "assets/images/product-image/default.jpg");
    //    //    furn = new Furn(null, name, maker, new BigDecimal(price),
    //    //            new Integer(sales), new Integer(stock), "assets/images/product-image/default.jpg");
    //
    //
    //    // 测试BeanUtils  反射使用的是带参构造器吗？ 还是set方法？
    //    // 测试方法：前端页面添加一个input 用于提交imgPath
    //
    //    // 添加数据之前先判断数据库中是否存在该furn对象
    //    if (furnService.isExistsFurn(furn)) {
    //        // 不存在可以添加
    //
    //        // 添加到数据库
    //        if (furnService.addFurn(furn)) {
    //            System.out.println("前端家居数据添加到DB成功！请求转发到furn_manage.jsp");
    //            //自己写的
    //            // 请求转发到显示家居列表页面
    //            // 转发之前先到数据库查询list 结果返回之后在进行页面转发
    //            // 数据库查询list
    //            List<Furn> furns = furnService.furnList();
    //            // 给当前请求对象req设置属性
    //            req.setAttribute("furns", furns);
    //
    //            req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    //
    //
    //
    //        } else {
    //            System.out.println("前端家居数据添加到DB失败！请求转发到furn_add.jsp");
    //            // 请求转发到furn_add.jsp
    //            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
    //
    //        }
    //
    //    }else{
    //        System.out.println("数据库中已经存在了 该furn对象信息 请求转发到furn_add.jsp 重新添加数据");
    //        // 可以给前端设置提示信息
    //        req.setAttribute("msg","数据库中已存在该furn对象信息 请重新添加数据~");
    //
    //        // 请求转发到furn_add.jsp
    //        req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
    //
    //    }
    //
    //
    //
    //    // 老韩写的
    //
    //
    //    furnService.addFurn(furn);
    //
    //    //请求转发到家居显示页面, 即需要重新走一下 FurnServlet-list方法
    //    //req.getRequestDispatcher("/manage/furnServlet?action=list")
    //    //        .forward(req, resp);
    //    //老师说明: 因为这里使用请求转发, 当用户刷新页面时, 会重新发出一次添加请求
    //    //就会造成数据重复提交： 解决方案使用 重定向即可.
    //    //因为重定向实际是让浏览器重新发请求, 所以我们回送的url , 是一个完整url
    //    resp.sendRedirect(req.getContextPath()
    //            + "/manage/furnServlet?action=list");
    //
    //
    //}

    /**
     * 添加家居
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //<td class="product-name"><input name="name" style="width: 60%" type="text" value="Name"/></td>
        //<td class="product-name"><input name="maker" style="width: 90%" type="text" value="蚂蚁家居"/></td>
        //<td class="product-price-cart"><input name="price" style="width: 90%" type="text" value="60.00"/></td>
        //    <input name="sales" style="width: 90%" type="text" value="100"/>
        //</td>
        //    <input name="stock" style="width: 90%" type="text" value="80"/>

        // imgPath 没有传过来

        // 处理中文乱码问题 竟然没有处理掉中文乱码问题！！？
        // 原因：前面的Basic 已经有了获取参数操作了所以在中间进行设置不生效！！
        // 要设置在获取参数之前！！
        //req.setCharacterEncoding("utf-8");

        //String name = req.getParameter("name"); //商品名
        //System.out.println("从浏览器接收到的 商品名：" + name); //new?°???????
        //String maker = req.getParameter("maker"); //制造商
        //String price = req.getParameter("price"); //价格
        //String sales = req.getParameter("sales"); //销量

        //我们可以对获取的到数据, 进行一个校验
        //1. 使用java的正则表达式来验证 sales是一个正整数
        //2. 如果没有通过校验，则直接返回furn_add.jsp -> request.setAttribute("mes","xx")
        //3. 这里可以直接进行转换
        //try {
        //    Integer.parseInt(sales);
        //} catch (Exception e) {
        //    System.out.println("sales 数据格式不正确，请输入数字");
        //    req.setAttribute("msg","sales 数据格式不正确，请输入数字");
        //    //返回添加页面
        //    req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
        //    return;
        //    //e.printStackTrace();
        //}
        //String stock = req.getParameter("stock"); //库存
        //图片的路径 imgPath 使用默认即可


        // 后端数据校验 假如有人直接把前端的js代码拿掉在进行提交 绕过前端校验 此时需要进行后端校验
        // 测试 前端输入了一个 price="hello"


        //// 构建Furn对象
        //Furn furn = null;
        //try {
        //    furn = new Furn(null, name, maker, new BigDecimal(price),
        //            new Integer(sales), new Integer(stock), "assets/images/product-image/default.jpg");
        //} catch (NumberFormatException e) {
        //    System.out.println("添加数据格式不对...，请重新输入");
        //    req.setAttribute("msg","添加数据格式不对...，请重新输入");
        //    //返回添加页面 furn_add.jsp
        //    req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
        //    return;
        //}


        //Furn furn = new Furn(null, name, maker, new BigDecimal(price),
        //        new Integer(sales), new Integer(stock), "assets/images/product-image/default.jpg");


        //后面我们会学习SpringMVC -> 专门的用于数据校验的规则/框架 JSR303... Hibernate Validator
        //这里我们使用第二种方式, 完成将前端提交的数据， 封装成Furn的Javabean对象

        //Furn furn = new Furn();
        //try {
        //    //populate: 迁移;输入数据
        //    //老韩解读 讲 req.getParameterMap() 数据封装到furn 对象
        //    //使用反射将数据封装, 有一个前提就是表单提交的数据字段名
        //    //<input name="maker" style="width: 90%" type="text" value=""/>
        //    //需要和封装的Javabean的属性名一致
        //    BeanUtils.populate(furn, req.getParameterMap());
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

        //System.out.println("furn= " + furn);
        //furn= Furn{id=null, name='vvvv', maker='蚂蚁家居', price=60.00, sales=100, stock=80, imgPath='null'}
        //.SQLException: Column 'img_path' cannot be null Query:
        // INSERT INTO furn( `id` ,`name` , `maker` , `price` , `sales`
        // , `stock`, `img_path`) VALUES(NULL , ? , ? , ? , ? , ?, ? )
        // Parameters: [vvvv, 蚂蚁家居, 60.00, 100, 80, null]

        //自动将提交的数据，封装到Furn对象

        Furn furn = DataUtils.copyParamsToBean(req.getParameterMap(), new Furn());


        // 测试BeanUtils  反射使用的是带参构造器吗？ 还是set方法？
        // 测试方法：前端页面添加一个input 用于提交imgPath
        // 测试BeanUtils  反射使用的是带参构造器吗？ 还是set方法？
        // 测试方法：前端页面添加一个input 用于提交imgPath
        //前端 发送了input name=imgPath的一个 空字符串
        // 说明：如果getParameter("param") param和name=param的值相同
        // 即前端有该名字的参数返回空串"",没有则返回null;
        // 后端BeanUtils反射 的imgPath后也是空
        //说明反射走的是空参构造器的set方法！


        // 添加到数据库
        // 老韩写的
        furnService.addFurn(furn);


        //请求转发到家居显示页面, 即需要重新走一下 FurnServlet-list方法
        //req.getRequestDispatcher("/manage/furnServlet?action=list")
        //        .forward(req, resp);
        //老师说明: 因为这里使用请求转发, 当用户刷新页面时, 会重新发出一次添加请求
        //就会造成数据重复提交： 解决方案使用 重定向即可.
        //因为重定向实际是让浏览器重新发请求, 所以我们回送的url , 是一个完整url
        //resp.sendRedirect(req.getContextPath()
        //        + "/manage/furnServlet?action=list");


        // 添加家居成功后 能够回显原来操作页面所在页面数据 重定向到进行添加操作的page页面

        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
        // 因为设置在了session对象中 所以这里可以获取到 点击添加家居时那个页面中的page信息
        //int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE
        //int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //Page<Furn> page = furnService.page(pageNo, pageSize);

        //System.out.println("获取到添加时家居页的page对象= " + page);

        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        //req.setAttribute("page", page);

        // 因为下一个页面还需要用到page对象 所以这里使用请求转发
        // 但是请求转发会造成 重复添加数据的情况 所以还是要进行重定向
        // 因此 需要将数据设置到session对象中
        //HttpSession session = req.getSession();
        //session.setAttribute("page", page);
        // 设置一个flag 表明分页显示时启用此处设置的page
        //session.setAttribute("flagPage", "true");


        // 返回总页数
        //req.setAttribute("pageTotalCount", page.getPageTotalCount());

        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        //req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);

        // 防止刷新页面重复添加这里使用重定向
        //因为重定向实际是让浏览器重新发请求, 所以我们回送的url , 是一个完整url
        //resp.sendRedirect(req.getContextPath()
        //        + "/manage/furnServlet?action=page");

        // 在后面加上前端update.jsp 通过form传过来的参数pageNo
        resp.sendRedirect(req.getContextPath()
                + "/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));


    }

    /**
     * 删除家居 自己写的
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取id信息 根据前端传过来的信息进行获取id
        String id = req.getParameter("id");
        System.out.println("前端传过来的 id= " + id);

        Gson gson = new Gson();
        //ajax请求失败时返回下面的对象
        Furn defaultFurn = new Furn(-1, "", "", new BigDecimal(-1), 0, 0, "");
        String strDefaultFurn = gson.toJson(defaultFurn);
        //ajax请求失败时返回下面的对象
        Furn defaultFurn2 = new Furn(-2, "", "", new BigDecimal(-1), 0, 0, "");
        String strDefaultFurn2 = gson.toJson(defaultFurn2);

        // 到数据库进行验证 查看是否有该id 如果有就删除 根据删除成功与否进行返回相关信息
        int affectedRows = furnService.deleteFurnById(Integer.parseInt(id));
        if (affectedRows > 0) {
            //给浏览器返回User对象的json字符串形式结果   //服务器端什么也没有返回，会导致ajax失败，会进来此函数
            //不写response.setContentType 前端控制台会出现 => XML 解析错误：格式不佳 ... 需要设置一下ContentType
        /*XML 解析错误：格式不佳
        位置：http://localhost:8080/ajax/checkLoginServlet2?username=%E6%9D%A8%E8%BE%BE&date=Tue%20Jul%2004%202023%2016%3A52%3A54%20GMT%2B0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)
        行 1，列 1：*/

            System.out.println("删除成功");
            //response.setContentType("text/html;charset=utf-8");
            resp.setContentType("text/json;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write(strDefaultFurn);

        } else {
            System.out.println("删除失败");
            //response.setContentType("text/html;charset=utf-8");
            resp.setContentType("text/json;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write(strDefaultFurn2);
        }

    }

    /**
     * 删除家居 老韩的
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取id信息 根据前端传过来的信息进行获取id
        //String id = req.getParameter("id");
        //System.out.println("前端传过来的 id= " + id);

        //老韩解读
        //为了防止接收的id 不是一个数字的字符串 比如"hello"
        //我就使用一个工具方法, 如果是可以转成数字的字符串，就转，否则就返回一个默认值
        int id = DataUtils.parseInt(req.getParameter("id"), 0);

        //通过id删除对应的家居信息
        furnService.deleteFurnById(id);


        // 删除家居成功后 能够回显原来操作页面所在页面数据 重定向到进行添加操作的page页面

        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
        // 因为设置在了session对象中 所以这里可以获取到 点击添加家居时那个页面中的page信息
        //int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE
        //int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //Page<Furn> page = furnService.page(pageNo, pageSize);

        //System.out.println("获取到删除时家居页的page对象= " + page);

        // 所在删除页面 的页码如果大于 删除后的总页码 那么就显示现存有的数据的最后一页的内容
        //if(pageNo > page.getPageTotalCount()){
        //
        //}


        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        //req.setAttribute("page", page);

        // 因为下一个页面还需要用到page对象 所以这里使用请求转发
        // 但是请求转发会造成 重复添加数据的情况 所以还是要进行重定向
        // 因此 需要将数据设置到session对象中
        //HttpSession session = req.getSession();
        //session.setAttribute("page", page);
        // 设置一个flag 表明分页显示时启用此处设置的page
        //session.setAttribute("flagPage", "true");


        // 返回总页数
        //req.setAttribute("pageTotalCount", page.getPageTotalCount());

        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        //req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);

        // 防止刷新页面重复添加这里使用重定向
        //因为重定向实际是让浏览器重新发请求, 所以我们回送的url , 是一个完整url
        //resp.sendRedirect(req.getContextPath()
        //        + "/manage/furnServlet?action=page");

        // 这里使用重定向 在参数位置将pageNo 传递过去
        resp.sendRedirect(req.getContextPath()
                + "/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));


        //req.getRequestDispatcher("/manage/furnServlet?action=list").forward(req,resp);

        //重定向到list 家居列表页，看到最新数据 不要使用请求转发，不然一刷新页面又会重复删除
        //resp.sendRedirect("/jiaju_mall/manage/furnServlet?action=list");
        //resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=list");

    }

    /**
     * 回显修改家居信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取id信息 根据前端传过来的信息进行获取id
        //String id = req.getParameter("id");
        //System.out.println("前端传过来的 id= " + id);

        //老韩解读
        //为了防止接收的id 不是一个数字的字符串 比如"hello"
        //我就使用一个工具方法, 如果是可以转成数字的字符串，就转，否则就返回一个默认值
        int id = DataUtils.parseInt(req.getParameter("id"), 0);

        //通过id查询对应的家居信息并返回
        Furn furn = furnService.queryFurnById(id);
        //将该furn对象数据放入到request域对象的属性中 传递给前端furn_update.jsp 页面
        //然后在前端取出该数据信息并显示
        req.setAttribute("furn", furn);
        System.out.println("showFurn根据id查询到的furn= " + furn);
        // 请求转发到修改信息回显界面 furn_update.jsp


        //req.getRequestDispatcher("views/manage/furn_update.jsp").forward(req,resp);
        // 没写空格=>   jiaju_mall/manage/ views/manage/furn_update.jsp
        // 原因是配置servlet url 多级目录结构造成的
        // 因为这里是查询  没有对数据库修改 所以可以请求转发 到furn_update.jsp 进行修改回显

        // 1 pageNo也可以放在request域对象中 在下一个页面通过requestScope 取出

        //2 老师说明: 如果请求带来的参数 pageNo=1 , 而且是请求转发到下一个页面, 在下一个页面可以通过 param.pageNo
        req.getRequestDispatcher("/views/manage/furn_update.jsp").forward(req, resp);

        //重定向 修改信息回显界面 furn_update.jsp
        //resp.sendRedirect("/jiaju_mall/manage/furnServlet?action=list");
        //resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=list");

    }

    /**
     * 正式处理修改家居信息 可以处理图片的文件上传问题
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 将前端传过来的数据封装成Furn对象
        //Furn furn = DataUtils.copyParamsToBean(req.getParameterMap(), new Furn());

        //需要通过url 一项一项的获取了


        //&id=${requestScope.furn.id}&pageNo=${param.pageNo}
        // 默认给一个0
        int id = DataUtils.parseInt(req.getParameter("id"), 0);

        // 通过id 到数据库查询是否有该id的furn数据
        Furn furn = furnService.queryFurnById(id);
        System.out.println(furn);

        // todo 如果furn为null就不处理，直接返回

        if (furn != null) {
            //如果furn不为null就继续走下面的逻辑

            // 先要判断是否 有文件上传 如果有则更新数据库表furn 的img_path字段
            if (ServletFileUpload.isMultipartContent(req)) {
                //System.out.println("ok");
                //2. 创建 DiskFileItemFactory 对象, 用于构建一个解析上传数据的工具对象
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                //3. 创建一个解析上传数据的工具对象
                /**
                 *     表单提交的数据就是 input 元素
                 *     <input type="file" name="pic" id="" value="2xxx.jpg" onchange="prev(this)"/>
                 *     家居名: <input type="text" name="name"><br/>
                 *     <input type="submit" value="上传"/>
                 */
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                //解决接收到文件名是中文乱码问题
                servletFileUpload.setHeaderEncoding("utf-8");


                //4. 关键的地方, servletFileUpload 对象可以把表单提交的数据text / 文件
                //   将其封装到 FileItem 文件项中
                //   老师的编程心得体会: 如果我们不知道一个对象是什么结构[1.输出 2.debug 3. 底层自动看到]
                try {
                    List<FileItem> list = servletFileUpload.parseRequest(req);

                    System.out.println(list);

                /*
                list==>
                 [name=3.jpg 就是上传文件的文件名 如果input type=file 没有上传文件 直接获取
                  String name = fileItem.getName();
                            // 如果不传图片 只修改其他项 会导致name=""
                            System.out.println("上传的文件名=" + name);

                [name=3.jpg, StoreLocation=D:\hspedu_javaweb\apache-tomcat-8.0.50-windows-x64\apache-tomcat-8.0.50\temp\xupload__7e34374f_17fce4168b1__7f4b_00000000.tmp, size=106398bytes, isFormField=false, FieldName=pic,
                name=null, StoreLocation=D:\hspedu_javaweb\apache-tomcat-8.0.50-windows-x64\apache-tomcat-8.0.50\temp\xupload__7e34374f_17fce4168b1__7f4b_00000001.tmp, size=6bytes, isFormField=true, FieldName=name]

                 */
                    //System.out.println("list==>" + list);
                    for (FileItem fileItem : list) {

                        /* [name=3.jpg 就是上传文件的文件名 如果input type=file 没有上传文件 直接获取
                        String name = fileItem.getName();
                        // 如果不传图片 只修改其他项 会导致获取的文件名name=""
                        System.out.println("上传的文件名=" + name);*/


                        //System.out.println("fileItem= " + fileItem);
                        //判断是不是一个文件=> 你是OOP程序员
                        if (fileItem.isFormField()) {//如果是true就是文本 input text(普通的表单字段)
                            // 获取form 表单的文本类型 的值

                            if ("name".equals(fileItem.getFieldName())) {//家居名
                                furn.setName(fileItem.getString("utf-8"));
                            } else if ("maker".equals(fileItem.getFieldName())) {//制造商
                                furn.setMaker(fileItem.getString("utf-8"));
                            } else if ("price".equals(fileItem.getFieldName())) {//价格
                                furn.setPrice(new BigDecimal(fileItem.getString("utf-8")));
                            } else if ("sales".equals(fileItem.getFieldName())) {//销量
                                furn.setSales(new Integer(fileItem.getString("utf-8")));
                            } else if ("stock".equals(fileItem.getFieldName())) {//库存
                                furn.setStock(new Integer(fileItem.getString("utf-8")));
                            }


                        } else {//是一个文件
                            //用一个方法
                            //获取上传的文件的名字
                            String name = fileItem.getName();
                            System.out.println("上传的文件名=" + name);
                            // 如果不传图片！！ 只修改其他项 会导致name=""  所以进行判断
                            // 如果文件名等于不等于空 就对文件进行处理 如果文件名等于空 就不对furn进行处理
                            if (!"".equals(name)) {
                                System.out.println("用户上传的有图片 进行服务器端上传操作");
                                //把这个上传到 服务器的 temp下的文件保存到你指定的目录
                                //1.指定一个目录 , 就是我们网站工作目录下
                                String filePath = "/" + WebUtils.FURN_IMG_DIRECTORY;
                                //2. 获取到完整目录 [io/servlet基础]
                                //  这个目录是和你的web项目运行环境绑定的. 是动态.
                                //fileRealPath=D:\hspedu_javaweb\fileupdown\out\artifacts\fileupdown_war_exploded\xupload\
                                String fileRealPath =
                                        req.getServletContext().getRealPath(filePath);
                                //System.out.println("fileRealPath=" + fileRealPath);

                                //3. 创建这个上传的目录=> 创建目录?=> Java基础
                                //   老师思路; 我们写一个工具类，可以返回 /2024/11/11 字符串
                                File fileRealPathDirectory = new File(fileRealPath + "/" + WebUtils.getYearMonthDay()); //这里最后的斜杠被构造器拿掉了
                                //File fileRealPathDirectory = new File(fileRealPath); // 暂时不做子目录的处理     这里最后的斜杠被构造器拿掉了
                                if (!fileRealPathDirectory.exists()) {//不存在，就创建
                                    fileRealPathDirectory.mkdirs();//创建
                                }

                                //4. 将文件拷贝到fileRealPathDirectory目录
                                //   构建一个上传文件的完整路径 ：目录+文件名
                                //   对上传的文件名进行处理, 前面增加一个前缀，保证是唯一即可, 不错
                                name = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
                                //System.out.println("fileRealPathDirectory= " + fileRealPathDirectory);
                                //System.out.println("fileRealPathDirectory.getPath()= " + fileRealPathDirectory.getPath());

                                String fileFullPath = fileRealPathDirectory + "/" + name;
                                fileItem.write(new File(fileFullPath)); //保存 即从临时文件夹 复制到新的上传位置

                                //D:\Java_developer_tools\javaweb\jiaju_mall\web\assets\images\product-image\1.jpg
                                // 如果也需要同时对web目录下进行更新如何处理？暂时不处理

                                fileItem.getOutputStream().close(); //关闭流

                                //5. 提示信息
                                //resp.setContentType("text/html;charset=utf-8");
                                //resp.getWriter().write("上传成功~");

                                // 删除原来旧的图片 只有上传了新的图片的 才用删除旧的图片 没有上传新的图片就不用删除
                                // 这里获取到的路径是 assets/images/product-image/2.jpg
                                String imgPath = furn.getImgPath();
                                // 需要得到真正的路径删除
                                System.out.println("imgPath= " + imgPath);
                                // imgPath= assets/images/product-image/14.jpg

                                //String realImgPath1 = req.getServletContext().getRealPath(imgPath);
                                //System.out.println("realImgPath1= " + realImgPath1);
                                // realImgPath1= D:\Java_developer_tools\javaweb\jiaju_mall\
                                // out\artifacts\jiaju_mall_war_exploded\assets\images\product-image\14.jpg

                                String realImgPath2 = req.getServletContext().getRealPath("/" + imgPath);
                                System.out.println("realImgPath2= " + realImgPath2);
                                // realImgPath2= D:\Java_developer_tools\javaweb\jiaju_mall\
                                // out\artifacts\jiaju_mall_war_exploded\assets\images\product-image\14.jpg

                                // 即req.getServletContext().getRealPath(imgPath)和req.getServletContext().getRealPath("/"+imgPath)没有区别
                                // 都是从根目录开始计算的！！！

                                File oldImgFile = new File(realImgPath2);
                                if (oldImgFile.exists()) {
                                    // 如果旧的图片文件存在 就删除
                                    if (oldImgFile.delete()){
                                        System.out.println("删除旧图片成功");
                                    }else {
                                        System.out.println("删除旧图片失败");
                                    }

                                }


                                //更新家居的图片路径 注意前面不可以带斜杠
                                //furn.setImgPath(filePath + "/" + name); //这里前面带了斜杠 会导致显示不出来 /解析成了主机加端口
                                furn.setImgPath(WebUtils.FURN_IMG_DIRECTORY + "/" + WebUtils.getYearMonthDay() + name);


                            } else {
                                //  没上传图片文件 就使用原来的图片 不做处理 即仍然使用原来的图片 但不用上传到文件夹
                                System.out.println("用户没有上传图片");

                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("不是文件表单");
            }


            // 更新furn数据到DB
            int affectedRows = furnService.updateFurn(furn);
            if (affectedRows > 0) {
                System.out.println("修改家居信息成功");
                // 请求转发到更新成功页面 update_ok.jsp
                // 注意这里也需要使用请求转发 而不是重定向 因为update_ok.jsp 中需要使用到param.pageNo取url数据
                req.getRequestDispatcher("/views/manage/update_ok.jsp").forward(req, resp);
            } else {
                System.out.println("修改家居信息失败");
            }
            // 重定向到显示家居列表页面 防止再次刷新时 导致二次修改
            //resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=list");

            //测试 form表单action属性 ?后面的值是否会被拿掉，为了看到提交的地址信息 所使用的请求转发
            //req.getRequestDispatcher("/manage/furnServlet?action=list").forward(req,resp);


            // 修改家居成功后 能够回显原来操作页面所在页面数据 重定向到进行添加操作的page页面

            // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
            // 因为设置在了session对象中 所以这里可以获取到 点击修改家居时那个页面中的page信息
            //int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
            // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE
            //int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

            //Page<Furn> page = furnService.page(pageNo, pageSize);

            //System.out.println("获取到修改时家居页的page对象= " + page);

            // 将分页需要显示的数据 返回给前端页面
            // 给域对象设置属性
            //req.setAttribute("page", page);

            // 因为下一个页面还需要用到page对象 所以这里使用请求转发
            // 但是请求转发会造成 重复添加数据的情况 所以还是要进行重定向
            // 因此 需要将数据设置到session对象中
            //HttpSession session = req.getSession();
            //session.setAttribute("page", page);
            // 设置一个flag 表明分页显示时启用此处设置的page
            //session.setAttribute("flagPage", "true");


            // 返回总页数
            //req.setAttribute("pageTotalCount", page.getPageTotalCount());

            //请求转发到list页面 这里要显示的不是所有的家居信息
            //直接 请求转发到 "/views/manage/furn_manage.jsp"
            //req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);

            // 防止刷新页面重复添加这里使用重定向
            //因为重定向实际是让浏览器重新发请求, 所以我们回送的url , 是一个完整url
            //resp.sendRedirect(req.getContextPath()
            //        + "/manage/furnServlet?action=page");

            // 在后面加上前端update.jsp 通过form传过来的参数pageNo
            //resp.sendRedirect(req.getContextPath()
            //        + "/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));


        } else {
            System.out.println("根据id在数据库查询的furn对象为空，直接返回原页面");
        }
    }

    ///**
    // * 正式处理修改家居信息
    // *
    // * @param req
    // * @param resp
    // * @throws ServletException
    // * @throws IOException
    // */
    //protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //
    //    // 将前端传过来的数据封装成Furn对象
    //    Furn furn = DataUtils.copyParamsToBean(req.getParameterMap(), new Furn());
    //    System.out.println(furn);
    //    // 更新数据
    //    int affectedRows = furnService.updateFurn(furn);
    //    if (affectedRows > 0) {
    //        System.out.println("修改家居信息成功");
    //    } else {
    //        System.out.println("修改家居信息失败");
    //    }
    //    // 重定向到显示家居列表页面 防止再次刷新时 导致二次修改
    //    //resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=list");
    //
    //    //测试 form表单action属性 ?后面的值是否会被拿掉，为了看到提交的地址信息 所使用的请求转发
    //    //req.getRequestDispatcher("/manage/furnServlet?action=list").forward(req,resp);
    //
    //
    //    // 修改家居成功后 能够回显原来操作页面所在页面数据 重定向到进行添加操作的page页面
    //
    //    // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
    //    // 因为设置在了session对象中 所以这里可以获取到 点击修改家居时那个页面中的page信息
    //    //int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
    //    // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE
    //    //int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
    //
    //    //Page<Furn> page = furnService.page(pageNo, pageSize);
    //
    //    //System.out.println("获取到修改时家居页的page对象= " + page);
    //
    //    // 将分页需要显示的数据 返回给前端页面
    //    // 给域对象设置属性
    //    //req.setAttribute("page", page);
    //
    //    // 因为下一个页面还需要用到page对象 所以这里使用请求转发
    //    // 但是请求转发会造成 重复添加数据的情况 所以还是要进行重定向
    //    // 因此 需要将数据设置到session对象中
    //    //HttpSession session = req.getSession();
    //    //session.setAttribute("page", page);
    //    // 设置一个flag 表明分页显示时启用此处设置的page
    //    //session.setAttribute("flagPage", "true");
    //
    //
    //    // 返回总页数
    //    //req.setAttribute("pageTotalCount", page.getPageTotalCount());
    //
    //    //请求转发到list页面 这里要显示的不是所有的家居信息
    //    //直接 请求转发到 "/views/manage/furn_manage.jsp"
    //    //req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    //
    //    // 防止刷新页面重复添加这里使用重定向
    //    //因为重定向实际是让浏览器重新发请求, 所以我们回送的url , 是一个完整url
    //    //resp.sendRedirect(req.getContextPath()
    //    //        + "/manage/furnServlet?action=page");
    //
    //    // 在后面加上前端update.jsp 通过form传过来的参数pageNo
    //    resp.sendRedirect(req.getContextPath()
    //            + "/manage/furnServlet?action=page&pageNo="+req.getParameter("pageNo"));
    //}
    //


    ///**
    // * 分页显示
    // *
    // * @param req
    // * @param resp
    // * @throws ServletException
    // * @throws IOException
    // */
    //protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    System.out.println("FurnServlet page() 被调用...");
    //
    //    // 上来先判断session 对象中是否有page数据 如果有 就是使用session中的数据
    //    HttpSession session = req.getSession();
    //
    //    Object flagPage = session.getAttribute("flagPage");
    //    if (flagPage != null && "true".equals(flagPage.toString())) {
    //        // 此处是删除最后一页数据时,最后一页只有一条数据 这一条数据删除后该页就没有数据的情况
    //        // 说明是对page进行操作过 又设置的page 需要按照此出的page进行页面显示
    //
    //       /* 这里获取到的page对象的 items 属性 为空 页码大于实际存在的页码时导致
    //          计算获取分页信息的 开始位置begin时 begin 大于实际存在的数据行数
    //          获取的items 为空的ArrayList集合 "[]" 因此在下面使用相关的属性时也为空
    //       *    int begin = (pageNo - 1) * pageSize;
    //            List<Furn> pageItems = furnDAO.getPageItems(begin, pageSize);
    //            page.setItems(pageItems);
    //       *
    //       *
    //       * */
    //
    //        Page page = (Page) session.getAttribute("page");
    //
    //        List items = page.getItems();
    //
    //        Integer pageNO = 1; // 初始化为1
    //        // 只要new 出一个集合 就会分配地址值 就不会为null
    //        //if (items == null && page.getPageNo() > 1){
    //        // 集合非空判断 使用isEmpty() / size() 进行判断
    //        if (items.size() == 0  && page.getPageNo() > 1){
    //            // 通过页码获取到的分页数据为空 说明此处页码 大于 数据库中新的分页页码 需要重新设置pageNo
    //            // 设置一个可以获取到分页数据的pageNo 即获取最后一页
    //            // 获取最后一页
    //            pageNO = page.getPageNo();
    //            Integer lastNO = --pageNO;
    //
    //            // 获取最后一页数据 覆盖之前的page
    //            Page lastPage = furnService.page(lastNO, page.getPageSize());
    //
    //            req.setAttribute("page", lastPage);
    //
    //            session.setAttribute("page", lastPage);
    //
    //            // 防止再次使用 将flagPage 设置为false
    //            session.setAttribute("flagPage", "false");
    //
    //            //直接 请求转发到 "/views/manage/furn_manage.jsp"
    //            req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    //
    //        // 获取最新的page对象的信息
    //        //Page<Furn> currentPage = furnService.page(page.getPageNo(), page.getPageSize());
    //
    //        //如果 之前的page对象的页数大于了当前最新的页码 就返回最后一页的page对象
    //        // 这里两个参数相等 均为最新的页码数 不可以以此来判断
    //        //if (page.getPageTotalCount() > currentPage.getPageTotalCount()) {
    //        //    int lastNo = 1; // 设置最后一页的默认值为1 防止lastNo赋给pageNo时为0
    //        //    if (currentPage.getPageTotalCount() > 0) {
    //        //        lastNo = currentPage.getPageTotalCount();
    //        //    }
    //            // 获取最后一页的page信息 并返回  最后一页的page对象
    //            //Page lastPage = furnService.page(lastNo, page.getPageSize());
    //            //System.out.println("获取到最后一页的Page对象= " + lastPage);
    //            // 为了让同一个会话 也可以访问到page对象的信息 设置到session域中
    //            //session.setAttribute("page", page);
    //
    //            // 返回总页数
    //            //req.setAttribute("pageTotalCount", page.getPageTotalCount());
    //
    //            //请求转发到list页面 这里要显示的不是所有的家居信息
    //            //直接 请求转发到 "/views/manage/furn_manage.jsp"
    //            //req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    //            //进入到if中后就不往后走了
    //            return;
    //        }
    //
    //        // 可以走到这里 说明删除后该页还有数据
    //
    //        // 将分页需要显示的数据 返回给前端页面
    //        // 老韩使用的req 用于参数传递
    //        // 给域对象设置属性
    //        req.setAttribute("page", page);
    //
    //        // 自己使用的session 用于多个页面之间进行参数传递
    //        // 为了让同一个会话 也可以访问到page对象的信息 设置到session域中
    //        session.setAttribute("page", page);
    //
    //        // 防止再次使用 将flagPage 设置为false
    //        session.setAttribute("flagPage", "false");
    //
    //        //请求转发到list页面 这里要显示的不是所有的家居信息
    //        //直接 请求转发到 "/views/manage/furn_manage.jsp"
    //        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    //
    //        // 返回总页数
    //        //req.setAttribute("pageTotalCount", page.getPageTotalCount());
    //
    //        //如果在这里的页面请求上一页/下一页 会发生什么?
    //        // 可以正常进行 因为上一页/下一页 使用的是request域对象中的page 这里进行了覆盖
    //
    //
    //    } else {
    //
    //        //下面的代码是没有进行任何dml操作时进行分页显示的代码
    //        //是首次进行分页的页面显示请求
    //
    //        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错 默认显示第一页给一个1
    //        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
    //        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE
    //        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
    //
    //        Page<Furn> page = furnService.page(pageNo, pageSize);
    //
    //        System.out.println("获取到page对象= " + page);
    //
    //        // 将分页需要显示的数据 返回给前端页面
    //        // 给域对象设置属性
    //        req.setAttribute("page", page);
    //
    //        // 为了让同一个会话 也可以访问到page对象的信息 设置到session域中
    //        session.setAttribute("page", page);
    //
    //        // 返回总页数
    //        //req.setAttribute("pageTotalCount", page.getPageTotalCount());
    //
    //        //请求转发到list页面 这里要显示的不是所有的家居信息
    //        //直接 请求转发到 "/views/manage/furn_manage.jsp"
    //        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    //    }
    //}

    /**
     * 处理分页请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("furnServlet page() 被调用...");

        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用service方法, 获取Page对象
        Page<Furn> page = furnService.page(pageNo, pageSize);
        //将page放入到request域
        req.setAttribute("page", page);
        //请求转发到furn_manage.jsp
        req.getRequestDispatcher("/views/manage/furn_manage.jsp")
                .forward(req, resp);
    }


    /**
     * 显示上一页的家居信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void previousPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FurnServlet previousPage() 被调用...");
        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        // 上一页的页码
        int previousNo = pageNo - 1;
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE

        Page<Furn> page = null;
        //if(previousNo > 0){
        if (previousNo >= 1) {
            //上一页大于0 才可以获取上一页的信息
            //上一页大于等于1 才可以获取上一页的信息
            page = furnService.page(previousNo, pageSize);
            System.out.println("获取到上一页的Page对象= " + page);


        } else {
            //当前页小于或等于0 不可以获取上一页的信息 留在当前页
            //请求转发到当前页
            //获取当前页要显示的页面信息
            page = furnService.page(pageNo, pageSize);
            System.out.println("获取到当前页的Page对象= " + page);

        }


        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        //req.setAttribute("page", page);

        //因为前端使用的是session对象进行 页面展示的所以 要给session对象设置属性
        HttpSession session = req.getSession();
        session.setAttribute("page", page);


        // 返回总页数
        //req.setAttribute("pageTotalCount", page.getPageTotalCount());

        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);


    }

    /**
     * 显示下一页的家居信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void nextPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FurnServlet nextPage() 被调用...");
        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        // 下一页的页码
        int nextNo = pageNo + 1;
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE

        // 获取当前页的page对象 用来获取总共有多少页 来判断是否获取下一页的信息
        Page<Furn> page = furnService.page(pageNo, pageSize);

        if (nextNo <= page.getPageTotalCount()) {
            // 如果下一页的页码 小于最后一页的页码 可以转发到下一页
            page = furnService.page(nextNo, pageSize);
            System.out.println("获取到下一页的Page对象= " + page);
        }

        // 如果下一页的页码 大于最后一页的页面 即留在当前页 在最后一页
        //请求转发到当前页 此时 pageNo==pageTotalCount
        //获取当前页要显示的页面信息

        System.out.println("获取到当前页的Page对象= " + page);


        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        //req.setAttribute("page", page);

        //因为前端使用的是session对象进行 页面展示的所以 要给session对象设置属性
        HttpSession session = req.getSession();
        session.setAttribute("page", page);

        // 返回总页数
        //req.setAttribute("pageTotalCount", page.getPageTotalCount());

        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }

    /**
     * 显示第一页的家居信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void firstPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FurnServlet firstPage() 被调用...");
        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错
        //int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE

        int firstNo = 1; // 设置第一页为1

        // 第一页的page对象
        Page page = furnService.page(firstNo, pageSize);
        System.out.println("获取到第一页的Page对象= " + page);
        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        //req.setAttribute("page", page);

        //因为前端使用的是session对象进行 页面展示的所以 要给session对象设置属性
        HttpSession session = req.getSession();
        session.setAttribute("page", page);


        // 返回总页数
        //req.setAttribute("pageTotalCount", page.getPageTotalCount());

        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }

    /**
     * 显示最后一页的家居信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void lastPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FurnServlet lastPage() 被调用...");
        // 获取前端传过来的分页参数 pageNo 如果为0 limit 进行分页时会出现负数 报错
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 如果前端没有给pageSize参数，默认给一个Page.PAGE_SIZE

        // 获取当前页的page对象 用来获取总共有多少页 来判断是否获取下一页的信息
        Page<Furn> page = furnService.page(pageNo, pageSize);


        // 最后一页的页码
        /*如果数据库中没有数据 一共0页
        page.getTotalRow() = 0; page.getPageTotalCount() = 0;
        不可以直接将最后一页的数 直接赋给lastNo 需要判断是否为大于0
        否则查询时会报错!
         */
        int lastNo = 1; // 设置最后一页的默认值为1
        if (page.getPageTotalCount() > 0) {
            /*
            // 如果数据库中有值 才把从数据库得到的值赋给lastNo

            如果在Service层的 page方法中 设置控制PageTotalCount默认为1 的条件
            这里会获取到 0 的值
            这里再次加了一个初始化的默认值为1 的条件 此时有两重保险 防止lastNo赋给pageNo时为0
            */
            lastNo = page.getPageTotalCount();
        }

        // 最后一页的page对象
        page = furnService.page(lastNo, pageSize);
        System.out.println("获取到最后一页的Page对象= " + page);
        // 将分页需要显示的数据 返回给前端页面
        // 给域对象设置属性
        //req.setAttribute("page", page);

        //因为前端使用的是session对象进行 页面展示的所以 要给session对象设置属性
        HttpSession session = req.getSession();
        session.setAttribute("page", page);

        // 返回总页数
        //req.setAttribute("pageTotalCount", page.getPageTotalCount());

        //请求转发到list页面 这里要显示的不是所有的家居信息
        //直接 请求转发到 "/views/manage/furn_manage.jsp"
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }


}
