package com.hspedu.furns.web;

import com.hspedu.furns.entity.Admin;
import com.hspedu.furns.service.AdminService;
import com.hspedu.furns.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-1:17
 */
public class AdminServlet extends BasicServlet {

    private AdminService adminService = new AdminServiceImpl();

    /**
     * 验证管理员登录的方法 方法名和MemberServlet中的 login() 方法名相同没事   因为程序走到BasicServlet不是同一个servlet对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AdminServlet login被调用...");

        // 直接用表单提交的数据进行登录验证
        String admin_name = request.getParameter("admin_name");
        String admin_pwd = request.getParameter("admin_pwd");

        if (admin_name != null){
            System.out.println("admin_name= " + admin_name);
        }
        if (admin_pwd != null){
            System.out.println("admin_pwd= " + admin_pwd);
        }

        // 判断输入的管理员名是否存在
        if(adminService.isExistsAdminName(admin_name)){
            System.out.println("管理员 " + admin_name + " 存在");

            // 构建管理员 Admin对象
            Admin admin = new Admin(null, admin_name, admin_pwd);
            Admin queryLoginAdmin = adminService.adminLogin(admin);
            if (queryLoginAdmin != null){
                System.out.println("管理员 " + queryLoginAdmin.getAdminName() + "登录成功");

                // 将该管理员对象放入到session域中
                HttpSession session = request.getSession();
                session.setAttribute("admin",queryLoginAdmin);

                // 请求转发到 manage_menu.html界面
                request.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(request,response);



            }else{
                System.out.println("管理员" + admin_name + " 登录失败");

                // 请求转发到 manage_login.html界面
                request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request,response);
            }


        }else{
            System.out.println("管理员 " + admin_name + " 不存在  重定向到管理员登录界面");
            // 在浏览器端 重定向到管理员登录界面
            //String contextPath = getServletContext().getContextPath();// contextPath=/jiaju_mall
            //response.sendRedirect(contextPath + "/views/manage/manage_login.jsp");

            response.sendRedirect(request.getContextPath() + "/views/manage/manage_login.jsp");

        }



    }

}
