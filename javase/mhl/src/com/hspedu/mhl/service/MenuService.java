package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.MenuDAO;
import com.hspedu.mhl.domain.Menu;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-05-05-20:56
 */
public class MenuService {
    //定义MenuDAO属性
    private MenuDAO menuDAO = new MenuDAO();

    //返回所有菜品，返回给界面使用
    public List<Menu> menuList(){
       return menuDAO.queryMulti("select * from menu",Menu.class);
    }

    //根据菜品id 得到菜品对象
    public Menu getMenuById(int id){
        return menuDAO.querySingle("select * from menu where id=?",Menu.class,id);
    }




}
