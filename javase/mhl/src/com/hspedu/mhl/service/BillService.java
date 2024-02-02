package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.BillDAO;
import com.hspedu.mhl.dao.MultiTableBeanDAO;
import com.hspedu.mhl.domain.Bill;
import com.hspedu.mhl.domain.DiningTable;
import com.hspedu.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 * @author yangda
 * @description: 处理和账单相关的业务逻辑
 * @create 2023-05-05-22:30
 */
public class BillService {
    //定义BillDAO属性
    private BillDAO billDAO = new BillDAO();
    //定义MenuService属性
    private MenuService menuService = new MenuService();
    //定义DiningTableService属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义MultiTableBeanService属性
    private MultiTableBeanDAO multiTableBeanDAO = new MultiTableBeanDAO();


    //餐桌号，菜品号进行校验（放在界面层去做）

    //生成账单的方法
    //1.生成账单
    //2.点餐成功，需要更新餐桌状态
    //3.如果成功，返回 true 否则 false
    /* mysql> select * from bill;
+----+--------------------------------------+--------+------+-------+---------------+---------------------+-----------+
| id(系统自动生成) | billId(随机生成的uuid)| menuId(用户传进来的) | nums(用户传进来的) | money(根据前两项计算所得) |
 diningTableId(用户传进来的) | billDate(数据库的系统时间now())| state(在方法中进行设置)|
+----+--------------------------------------+--------+------+-------+---------------+---------------------+-----------+
|  1 | 7eeb212b-bbe0-4842-a203-31c6cb836f3e |      3 |    4 |   144 |             2 | 2023-05-05 22:48:27 | 未结账    |
+----+--------------------------------------+--------+------+-------+---------------+---------------------+-----------+*/
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        //生成一个账单号，UUID
        String billId = UUID.randomUUID().toString();

        //生成账单
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')", billId, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        //money 菜品价格price * 点餐数量nums
        //需要根据传进来的菜品id (menuId) 得到菜品对象 Menu;

        if (update <= 0) {
            return false;
        }

        //更新餐桌状态
        return diningTableService.updateDiningTableStateById(diningTableId);
    }

    public List<Bill> list(){
        return billDAO.queryMulti("select * from bill",Bill.class);

    }
    //多表查询，bill menu
    public List<MultiTableBean> list2(){
        return multiTableBeanDAO.queryMulti("SELECT bill.*,NAME as name2,price FROM bill,menu WHERE bill.`menuId`=menu.`id`",MultiTableBean.class);

        //不对价格price查询，程序运行不会有什么影响，只是价格为null而已
        //return multiTableBeanDAO.queryMulti("SELECT bill.*,NAME FROM bill,menu WHERE bill.`menuId`=menu.`id`",MultiTableBean.class);

    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId){
        Bill bill = billDAO.querySingle("select * from bill where diningTableId=? and state='未结账' limit 0,1", Bill.class, diningTableId);
        return bill != null;


    }

    //完成结账【如果餐桌存在，并且该餐桌有未结账的账单】
    public boolean payBill(int diningTaleId,String payMode){//payMode 需要传进来结账的方式
        //修改bill表
        //将state 改为付款方式
        int update = billDAO.update("update bill set state=? where diningTableId=?", payMode, diningTaleId);
        if (update <= 0){
            return false;
        }


        //修改餐桌状态为空，并且将姓名和tel置空，去DiningTableService 增加新的方法，在这里用DiningTableService对象调用

        //
        if(!diningTableService.updateDiningTableStateByIdToFree(diningTaleId, "空")){//取反相当于没有成功，返回一个false
            return false;
        }

        //到这都成功了,返回true
        return true;

    }

}
