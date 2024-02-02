package com.hspedu.dao_.test;

import com.hspedu.dao_.dao.ActorDAO;
import com.hspedu.dao_.dao.GoodsDAO;
import com.hspedu.dao_.domain.Actor;
import com.hspedu.dao_.domain.Goods;
import org.junit.Test;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class TestDAO {

    //测试ActorDAO 对actor表crud操作
    @Test
    public void testActorDAO() {

        ActorDAO actorDAO = new ActorDAO();
        //1. 查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("===查询结果===");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //2. 查询单行记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 6);
        System.out.println("====查询单行结果====");
        System.out.println(actor);

        //3. 查询单行单列
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 6);
        System.out.println("====查询单行单列值===");
        System.out.println(o);

        //4. dml操作  insert ,update, delete
        int update = actorDAO.update("insert into actor values(null, ?, ?, ?, ?)", "张无忌", "男", "2000-11-11", "999");

        System.out.println(update > 0 ? "执行成功" : "执行没有影响表");




    }

    @Test
    public void goodsDAOTest(){
        GoodsDAO goodsDAO = new GoodsDAO();
        List<Goods> goods = goodsDAO.queryMulti("select * from goods01 where Field >= ?", Goods.class, 10);
        for(Goods goods1 : goods){
            System.out.println(goods1);
        }


        System.out.println();
        Goods goods1 = goodsDAO.querySingle("select * from goods01 where Field = ?", Goods.class, 20);
        System.out.println(goods1);

        System.out.println();
        Object o = goodsDAO.queryScalar("select goods_name from goods01 where Field = ?", 10);
        System.out.println(o);

//        dml
        int rows = goodsDAO.update("insert into goods01 values(?,?,?)", 666, "苹果手机", 5050);
        System.out.println(rows);
        List<Goods> goods2 = goodsDAO.queryMulti("select * from goods01 where Field >= ?", Goods.class, 10);
        for(Goods good2 : goods2){
            System.out.println(good2);
        }

        int rows1 = goodsDAO.update("delete from goods01 where Field = ?", 10);
        System.out.println(rows);
        List<Goods> goods3 = goodsDAO.queryMulti("select * from goods01 where Field >= ?", Goods.class, 10);
        for(Goods good2 : goods3){
            System.out.println(good2);
        }

        int rows2 = goodsDAO.update("update goods01 set price = ? where Field = ?", 10000,20);
        System.out.println(rows);
        List<Goods> goods4 = goodsDAO.queryMulti("select * from goods01 where Field >= ?", Goods.class, 10);
        for(Goods good2 : goods4){
            System.out.println(good2);
        }




    }


}
