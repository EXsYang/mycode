package com.hspedu.spring.tx.homework.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author yangda
 * @description:
 * @create 2023-09-18-23:20
 */

@Repository // 将GoodsDao对象 注入到spring容器
public class GoodsDao {


    //@Autowired
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据商品id 返回对应的价格
     * @param id 商品id
     * @return
     */
    public Float queryPriceById(Integer id) {
        String sql = "SELECT price From goods Where goods_id=?";
        Float price = jdbcTemplate.queryForObject(sql, Float.class, id);
        return price;
    }

    /**
     * 减少对应id 账户的余额 同时传进来要减少的多少money
     * @param user_id 账户id
     * @param money 要减少的多少money
     */
    public void updateBalance(Integer user_id, Float money) {
        String sql = "UPDATE user_account SET money=money-? Where user_id=?";
        jdbcTemplate.update(sql, money, user_id);
    }

    /**
     * 减少传进来的商品id的库存 减少的值也传进来
     * @param goods_id 商品id
     * @param amount  减少的库存量
     */
    public void updateAmount(Integer goods_id, int amount) {
        String sql = "UPDATE goods_amount SET goods_num=goods_num-? Where goods_id=?";
        jdbcTemplate.update(sql, amount, goods_id);
    }



    /**
     * 根据商品id 返回对应的价格
     * @param id 商品id
     * @return
     */
    public Float queryPriceById2(Integer id) {
        String sql = "SELECT price From goods Where goods_id=?";
        Float price = jdbcTemplate.queryForObject(sql, Float.class, id);
        return price;
    }

    /**
     * 减少对应id 账户的余额 同时传进来要减少的多少money
     * @param user_id 账户id
     * @param money 要减少的多少money
     */
    public void updateBalance2(Integer user_id, Float money) {
        String sql = "UPDATE user_account SET money=money-? Where user_id=?";
        jdbcTemplate.update(sql, money, user_id);
    }

    /**
     * 减少传进来的商品id的库存 减少的值也传进来
     * @param goods_id 商品id
     * @param amount  减少的库存量
     */
    public void updateAmount2(Integer goods_id, int amount) {
        String sql = "UPDATE goods_amount SET goods_num=goods_num-? Where goods_id=?";
        jdbcTemplate.update(sql, amount, goods_id);
    }


    // 提供一个方法完成淘宝买家购买商品

    // 通过id 查询商品价格在上面有了
    // 减少买家余额 以及 增加卖家余额 同时给淘宝提成 的方法
    public void updateBuyerAndSellerBalance(Integer buyer_id,Integer seller_id,Integer taoBao_id,Float money){
        String sql = "UPDATE buyer SET buyer_money=buyer_money-? Where buyer_id=?";
        String sql2 = "UPDATE seller SET seller_money=seller_money+?*0.9 Where seller_id=?";
        String sql3 = "UPDATEx taoBao SET taoBao_money=taoBao_money+?*0.1 Where taoBao_id=?";

        jdbcTemplate.update(sql, money, buyer_id);

        jdbcTemplate.update(sql2, money, seller_id);

        jdbcTemplate.update(sql3, money, taoBao_id);
    }







}
