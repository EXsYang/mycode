package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.DiningTableDAO;
import com.hspedu.mhl.domain.DiningTable;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-05-05-16:18
 */
public class DiningTableService {

    //定义DiningTableDAO对象
    DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌的信息
    public List<DiningTable> diningTableState() {
        return diningTableDAO.queryMulti("select id,state from diningTable", DiningTable.class);

    }

    //根据id 查询对应的餐桌DiningTable对象
    //如果返回null,说明id编号对应的餐桌不存在
    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);

    }

    //如果餐桌可以预定，调用方法，对其状态进行更新（包括预定人的姓名和电话）
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        //update 受影响的行数
        int update = diningTableDAO.update("update diningTable set state='已经预定',orderName=?,orderTel=? where id=?", orderName, orderTel, id);
        return update > 0; //

    }

    //根据餐桌id ,  更新餐桌状态
    public boolean updateDiningTableStateById(int id){
        return diningTableDAO.update("update diningTable set state='就餐中' where id=?",id) > 0;
    }
    //结账后，根据餐桌id , 更新餐桌状态,将State置空,将orderName orderTel 设置为 null
    public boolean updateDiningTableStateByIdToFree(int id,String state){
        return diningTableDAO.update("update diningTable set state=?,orderName='',orderTel='' where id=? ",state,id) > 0;
    }


}
