package com.hspedu.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
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

        /**
         *
         * required: 英 /rɪˈkwaɪəd/ 美 /rɪˈkwaɪərd/
         * v. 需要;(尤指根据法规)规定;使做(某事);使拥有(某物);依赖;依靠
         * adj. 〈美〉必修的(大学课程)
         * require的过去分词和过去式
         *
         * 对 jdbcTemplate.queryForObject() 方法的说明
         * sql:要执行的sql语句
         * requiredType:要返回的数据类型
         * args: sql语句中要填入?处的参数
         *
         *  @Override
         *  public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args) throws DataAccessException {
         * 	return queryForObject(sql, args, getSingleColumnRowMapper(requiredType));
         *  }
         */

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
        String sql = "UPDATEX goods_amount SET goods_num=goods_num-? Where goods_id=?";
        jdbcTemplate.update(sql, amount, goods_id);
    }
}
