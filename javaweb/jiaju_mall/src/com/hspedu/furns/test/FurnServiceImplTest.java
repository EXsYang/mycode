package com.hspedu.furns.test;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:31
 */
public class FurnServiceImplTest {

    private FurnService furnService = new FurnServiceImpl();

    @Test
    public void furnList() {

        List<Furn> furns = furnService.furnList();

        if (furns != null) {
            System.out.println("数据库中保存的有家居信息");
            for (Furn furn : furns) {
                System.out.println("家具信息= " + furn);
            }

        } else {
            System.out.println("数据库中没有家居信息");
        }
    }

    @Test
    public void addFurn() {


        // 创建BigDecimal对象
        BigDecimal price = new BigDecimal(333);

        // 创建furn对象
        Furn furn = new Furn(null, "jy小台灯", "杨达工程", price, 3, 100, "");

        if (furnService.addFurn(furn)) {
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }

    }

    @Test
    public void isExistsFurn(){

        // 创建BigDecimal对象
        BigDecimal price = new BigDecimal(333);

        // 创建furn对象
        Furn furn = new Furn(null, "jy小台灯x", "杨达工程", price, 3, 100, "");

        if(!furnService.isExistsFurn(furn)){
            System.out.println("furn is exists");
        }else{
            System.out.println("furn not exists");
        }
    }

    @Test
    public void deleteFurnById(){

        BigDecimal price = new BigDecimal(180);

        // 创建furn对象
        Furn furn = new Furn(32, "简约风格小椅子", "熊猫家居", price, 666, 7, "");
        // 这里走的是带参构造器
        // 因为在带参构造器中设置了条件 所以创建出来的对象的imgPath为默认值
        //System.out.println(furn.getImgPath()); //assets/images/product-image/default.jpg

        int affectedRows = furnService.deleteFurnById(32);

        System.out.println(furnService.deleteFurnById(32)); // 删除语句 删除成功返回0 的原因是 在这个打印语句中又调用了一遍方法
        System.out.println(affectedRows);
        //if(furnDAO.isExistsFurn(furn) != null){
        //    System.out.println("furn is exists");
        //}else{
        //    System.out.println("furn not exists");
        //}
    }

    @Test
    public void queryFurnById(){

        System.out.println(furnService.queryFurnById(0)); // null
        System.out.println(furnService.queryFurnById(245)); // null

    }
    @Test
    public void updateFurn(){
        // 这里传入的id=null 数据库没有报错 原因是 创建表时 没有非空约束
        //System.out.println(furnService.updateFurn(new Furn())); //
        // 注意 这里走的是带参构造器 imgPath的赋值有判断条件 传入"" 或 null 使用的是默认值
        System.out.println(furnService.updateFurn(new Furn(87, "简约风格小椅子", "33家居", new BigDecimal(43), 666, 7, "")));

    }

    @Test
    public void page(){

        //Page<Furn> page = furnService.page(0, 2);
        // 使用debug查看具体信息
        Page<Furn> page = furnService.page(2, 3);
        System.out.println(page);//com.hspedu.furns.entity.Page@2ed0fbae

    }

    @Test
    public void pageByName(){

        //Page<Furn> page = furnService.page(0, 2);
        // 使用debug查看具体信息
        Page<Furn> pageByName = furnService.pageByName("简约风格小椅子",1, 3);
        System.out.println(pageByName);//com.hspedu.furns.entity.Page@2ed0fbae

    }

}
