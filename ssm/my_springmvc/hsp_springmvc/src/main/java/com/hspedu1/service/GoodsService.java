package com.hspedu1.service;

import com.hspedu1.entity.Goods;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-07-20:18
 * @description:
 */
public interface GoodsService {

    // 定义方法 返回Goods集合
    public List<Goods> goodsList();

    // 定义方法 返回指定名称的ArrayList集合
    public List<Goods> goodsJson(String goodsName);


}
