package com.hspedu.mhl.view;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.hspedu.mhl.dao.MenuDAO;
import com.hspedu.mhl.domain.*;
import com.hspedu.mhl.service.*;
import com.hspedu.mhl.utils.Utility;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-04-29-17:58
 */
public class MHLView {
    private boolean loop = true;
    private String key = "";
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();
    private LoginService loginService = new LoginService();

    public static void main(String[] args) {


        MHLView mhlView = new MHLView();
        mhlView.mhlView();
    }

    public void registerEmp() {
        System.out.println("===========注册用户==============");
        System.out.print("请输入你的用户名：");
        String empId = Utility.readString(50);
        System.out.print("请输入你的密码：");
        String pwd = Utility.readString(50);
        System.out.print("请输入你的姓名：");
        String name = Utility.readString(50);
        System.out.print("请输入你的岗位：");
        String job = Utility.readString(50);

        if (employeeService.addEmployee(empId, pwd, name, job)) {

            System.out.println("注册成功");

        } else {
            System.out.println("注册失败");
        }


    }

    //删除用户
    public void deleteEmp() {
        System.out.println("==========用户列表===========");
        System.out.println("用户名\t\t\t密码");
        List<Login> logins = loginService.loginList();
        for (Login login :
                logins) {
            System.out.println(login);
        }
        System.out.print("请输入要删除的用户Id: ");
        String empId = Utility.readString(50);
        if(loginService.checkEmpId(empId)){ //即使()中为false,这个语句也会走只是不进去
            //查询到login表中有相同的empId,进行删除操作
            if (employeeService.deleteEmployee(empId)) {
                System.out.println("用户删除成功");
            } else {
                System.out.println("用户删除失败");
            }
        }



    }

    //查看用户列表
    public void showEmpList(){
        //显示用户列表，并将用户列表的信息更新到login表中
        System.out.println("===========显示用户列表==============");
        System.out.println("id\t\tempId\t\tpwd\t\t\t\t\t\t\t\t\t\tname\t\tjob");
        List<Employee> employees = employeeService.showList();

        for (Employee employee:
             employees) {
            if(!loginService.checkNOMD5EmpIdAndPwd(employee.getEmpId(),employee.getPwd())){
                //没有在login表里查到employee里应该有的信息，向login表添加数据
                loginService.updateEmpIdAndPwdToLogin(employee.getEmpId(), employee.getPwd());
            }
            System.out.println(employee);
        }

    }

    //    完成结账
    public void payBill() {
        System.out.println("===========结账服务=============");
        System.out.print("请选择要结账的餐桌编号(-1退出):");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("=========取消结账===========");
            return;
        }
        //验证餐桌是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        if (diningTable == null) {
            System.out.println("=========输入的餐桌号不存在==============");
            return;
        }
        //验证餐桌号是否有需要结账的账单
        if (!billService.hasPayBillByDiningTableId(diningTableId)) {
            System.out.println("=========该餐位没有未结账的账单==============");
            return;
        }

        System.out.print("结账方式(现金/微信/支付宝)回车表示退出：");
        String payMode = Utility.readString(30, "");//如果直接回车，返回的是空串 ""
        if ("".equals(payMode)) {
            System.out.println("==========取消结账===============");
            return;
        }

        System.out.print("确认是否结账(Y/N): ");
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            //真的要结账
            if (billService.payBill(diningTableId, payMode)) {
                System.out.println("============完成结账================");
            }


        } else {
            System.out.println("============取消结账================");
        }


    }


    //显示账单
    public void listBill() {
        System.out.println("===============显示账单=================");
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t状态");
        List<Bill> list = billService.list();

        for (Bill bill : list) {
            System.out.println(bill.getId() + "\t\t\t" + bill.getMenuId() + "\t\t\t" + bill.getNums() + "\t\t\t" + bill.getMoney() + "\t\t" + bill.getDiningTableId()
                    + "\t\t\t" + bill.getBillDate().toString() + "\t\t" + bill.getState());
        }
        System.out.println("==============显示完毕=================");

    }

    //显示多表账单
    public void listBill2() {
        System.out.println("===============显示账单=================");
        System.out.println("\n编号\t\t菜品号\t\t菜品名\t\t\t价格\t\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t状态");
        List<MultiTableBean> list = billService.list2();

        for (MultiTableBean bill : list) {
            System.out.println(bill.getId() + "\t\t\t" + bill.getMenuId() + "\t\t\t" + bill.getName2() + "\t\t\t" + bill.getPrice() + "\t\t\t" + bill.getNums() + "\t\t\t" + bill.getMoney() + "\t\t" + bill.getDiningTableId()
                    + "\t\t\t" + bill.getBillDate().toString() + "\t\t" + bill.getState());
        }
        System.out.println("==============显示完毕=================");

    }


    //点餐服务
    public void orderMenu() {
        System.out.println("===========点餐服务===========");
        System.out.print("请输入点餐的桌号(-1退出):");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("========取消点餐===========");
            return;//后面不用走了，直接return出去了
        }


        System.out.print("请输入点餐的菜品号(-1退出):");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("========取消点餐===========");
            return;
        }
        System.out.print("请输入点餐的菜品的数(-1退出):");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("========取消点餐===========");
            return;
        }

//        System.out.print("确认是否点这个菜(Y/N):");
//        char key = Utility.readConfirmSelection();
//        if (key == 'Y'){
//
//        }

        //验证餐桌号
        DiningTable diningTableById = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTableById == null) {
            System.out.println("========餐桌号不存在===========");
            return;
        }
        //验证菜品编号
        Menu menuById = menuService.getMenuById(orderMenuId);
        if (menuById == null) {
            System.out.println("========菜品号不存在===========");
            return;
        }

        //点餐
        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("========点餐成功==========");
        } else {
            System.out.println("========点餐失败==========");
        }


    }


    public void showDiningTableState() {
        System.out.println("显示餐桌状态");
        System.out.println("\n餐桌编号\t\t\t餐桌状态");
        List<DiningTable> list =
                diningTableService.diningTableState();
        for (DiningTable diningtable : list) {
            System.out.println(diningtable);
        }
        System.out.println("餐桌状态显示完成");
    }

    //预定餐桌
    public void orderDiningTable() {
        System.out.println("============预定餐桌===============");
        System.out.print("请选择要预定的餐桌编号(-1退出)：");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("============取消预订=============");
            return;
        }

        //该方法得到的'Y'或'N'
        System.out.print("确认是否预订(Y/N): ");
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {//要预定
            //根据orderId 返回对应的DiningTable对象，如果为null,说明该对象不存在
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if (diningTable == null) {
                System.out.println("=======预定餐桌不存在=========");
                return;
            }

            //判断该餐桌状态是否为 '空'
            if (!("空".equals(diningTable.getState()))) {
                System.out.println("=========该餐桌已经预定或就餐中=======");
                return;
            }

            //接收预定信息
            System.out.print("预定人的名字：");
            String orderName = Utility.readString(50);
            System.out.print("预定人的电话：");
            String orderTel = Utility.readString(50);

            //这时说明真的可以预定，更新餐桌状态
            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                System.out.println("======预定餐桌成功========");
            } else {
                System.out.println("===========预定餐桌失败===========");
            }


        } else {
            System.out.println("============取消预订=============");
        }


    }

    //显示所有菜品
    public void showMenuList() {
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        List<Menu> list = menuService.menuList();
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("============显示完成==============");
    }


    public void mhlView() {

        while (loop) {

            System.out.println("==============满汉楼=================");
            System.out.println("\t\t1 注册满汉楼");
            System.out.println("\t\t2 登录满汉楼");
            System.out.println("\t\t3 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    registerEmp();
                    break;
                case "2":
                    System.out.print("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.print("请输入密 码：");
                    String pwd = Utility.readString(50);
                    //不去employee总表进行登录验证了，去分表login 进行验证
//                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
//                    if (employee != null) {
//                        System.out.println("============登录成功(" + employee.getName() + ")===========");

                    boolean check = loginService.checkEmpIdAndPwd(empId, pwd);
                        if(check){
//                    System.out.println("============登录成功(" + employee.getName() + ")===========");
                    System.out.println("============登录成功(" + empId + ")===========");
                        while (loop) {
                            System.out.println("\n==============满汉楼二级菜单===================");
                            System.out.println("\t\t1 显示餐桌状态");
                            System.out.println("\t\t2 预定餐桌");
                            System.out.println("\t\t3 显示所有菜品");
                            System.out.println("\t\t4 点餐服务");
                            System.out.println("\t\t5 查看账单");
                            System.out.println("\t\t6 结账");
                            System.out.println("\t\t7 删除用户");
                            System.out.println("\t\t8 用户列表");
                            System.out.println("\t\t9 退出满汉楼");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    showDiningTableState();
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    showMenuList();
                                    break;
                                case "4":
//                                    System.out.println("点餐服务");
                                    orderMenu();
                                    break;
                                case "5":
//                                    listBill();
                                    listBill2();
                                    break;
                                case "6":
//                                    System.out.println("结账");
                                    payBill();
                                    break;
//                                case "7":
////                                    System.out.println("注册用户");
//                                    registerEmp();
//                                    break;
                                case "7":
//                                    System.out.println("删除用户");
                                    deleteEmp();
                                    break;
                                case "8":
//                                    System.out.println("用户列表");
                                    showEmpList();
                                    break;
                                case "9":
                                    loop = false;
                                    System.out.println("退出满汉楼");
                                    break;
                                default:
                                    System.out.println("输入有误，请重 新输入！");
                            }
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "3":
                    loop = false;
                    System.out.println("退出满汉楼");
                    break;
                default:
                    System.out.println("输入有误，请重新输入：");
            }


        }


    }


}
