package com.hspedu.furns.test;

import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.dao.daoimpl.FurnDAOImpl;
import com.hspedu.furns.entity.Furn;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:23
 */
public class FurnDAOImplTest {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Test
    public void queryFurnList(){

        // 测试获取所有的家居数据信息
        List<Furn> furns = furnDAO.queryFurnList();

        if (furns != null){
            System.out.println("数据库中保存的有家居信息");
            for (Furn furn : furns) {
                System.out.println("家具信息= " + furn);
            }


        }else{
            System.out.println("数据库中没有家居信息");
        }


    }


    @Test
    public void saveFurn(){

        // 创建BigDecimal对象
        BigDecimal price = new BigDecimal(333);

        // 创建furn对象
        Furn furn = new Furn(null, "jy小台灯", "杨达工程", price, 3, 100, "assets/images/product-image/default.jpg");

        if(furnDAO.saveFurn(furn) > 0){
            System.out.println("保存家居信息成功！");
        }else{
            System.out.println("保存家居信息失败！");
        }
    }

    @Test
    public void isExistsFurn(){

        // 创建BigDecimal对象
        BigDecimal price = new BigDecimal(333);

        // 创建furn对象
        Furn furn = new Furn(null, "jy小台灯x", "杨达工程", price, 3, 100, "");

        if(furnDAO.isExistsFurn(furn) != null){
            System.out.println("furn is exists");
        }else{
            System.out.println("furn not exists");
        }
    }
    @Test
    public void deleteFurnById(){

        //SELECT id FROM furn WHERE  `name` ='简约风格小椅子' AND `maker` ='熊猫家居'  AND `price` =180 AND
        //`sales` =666  AND `stock` =7 AND `img_path` ='assets/images/product-image/4.jpg' ;

        // 创建BigDecimal对象
        BigDecimal price = new BigDecimal(180);

        // 创建furn对象
        Furn furn = new Furn(33, "简约风格小椅子", "熊猫家居", price, 666, 7, "");
        // 这里走的是带参构造器
        // 因为在带参构造器中设置了条件 所以创建出来的对象的imgPath为默认值
        //System.out.println(furn.getImgPath()); //assets/images/product-image/default.jpg

        int affectedRows = furnDAO.deleteFurnById(33);

        System.out.println(affectedRows); // 删除语句 删除成功返回0 的原因是 在这个打印语句中又调用了一遍方法
        //if(furnDAO.isExistsFurn(furn) != null){
        //    System.out.println("furn is exists");
        //}else{
        //    System.out.println("furn not exists");
        //}
    }

    @Test
    public void getTotalRow(){

        System.out.println(furnDAO.getTotalRow());

    }
    @Test
    public void getPageItems(){

        //List<Page> pageItems = pageDAO.getPageItems(0, 3);
        //System.out.println(pageItems);

        List<Furn> pageItems = furnDAO.getPageItems(0, 7);
        System.out.println(pageItems);

    }
    @Test
    public void getTotalRowByName(){

        //List<Page> pageItems = pageDAO.getPageItems(0, 3);
        //System.out.println(pageItems);

        System.out.println(furnDAO.getTotalRowByName("aa"));
        System.out.println(furnDAO.getTotalRowByName("")); // 在后端传入空串返回所有数据
        //System.out.println(furnDAO.getTotalRowByName(null)); // 在后端传入空串返回所有数据
        //System.out.println(furnDAO.getTotalRowByName("小椅子"));

    }
    @Test
    public void getPageItemsByName(){

        //List<Page> pageItems = pageDAO.getPageItems(0, 3);
        //System.out.println(pageItems);

        //System.out.println(furnDAO.getPageItemsByName("aa"));

        System.out.println(furnDAO.getPageItemsByName("",0,3));
        System.out.println(furnDAO.getPageItemsByName("小椅子",0,3));

    }
}
