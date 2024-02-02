package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.EmployeeDAO;
import com.hspedu.mhl.domain.Employee;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-05-04-17:27
 */
public class EmployeeService {
    EmployeeDAO employeeDAO = new EmployeeDAO();
    private LoginService loginService = new LoginService();

    //方法，根据empId 和 pwd 返回一个Employee对象
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = MD5(?)", Employee.class, empId, pwd);

    }

    //注册用户的方法，注册时需要把empId和pwd 同时对login表进行更新
    public boolean addEmployee(String empId, String pwd, String name, String job) {
        //验证 login表中用户名是否存在，存在不可以进行注册
        if(loginService.checkEmpId(empId)){
            System.out.println("用户名已存在");
            return false;
        }


        //同时对login表进行更新，需要验证login中是否有该id和密码 上面id相同，走不到下面
//        if(loginService.checkEmpIdAndPwd(empId,pwd)){
//            System.out.println("该用户名和密码在login表中存在");
//            return false;
//        }
        //login中不存在进行添加操作
        if(employeeDAO.update("INSERT INTO employee VALUES(NULL, ?, MD5(?),?,?);", empId, pwd, name, job) <= 0){
            return false;
        }
        //更新login表中添加用户名和密码(里面有MD5)
        if(!loginService.addEmpIdAndPwd(empId,pwd)){
            return false;
        }
        return true;
    }

    //根据用户id删除用户的方法,同时对login表进行更新
    public boolean deleteEmployee(String empId) {
        if (employeeDAO.update("delete from employee where empId=?", empId) <= 0) {
            return false;
        }
        //同时对login表进行更新
        if (!loginService.deleteEmp(empId)) {
            return false;
        }
        return true;
    }


    //修改用户的方法
//    public boolean updateEmployee(String empId, String pwd, String name, String job){
//        employeeDAO.update("update employee set empId=?,pwd=?,name=?,job=?",)
//    }

    //查询所有用户的方法,即显示用户列表
    public List<Employee> showList(){
        return employeeDAO.queryMulti("select * from employee",Employee.class);
    }

}
