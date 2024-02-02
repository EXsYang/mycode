package com.hspedu1.service.Impl;

import com.hspedu1.entity.Goods;
import com.hspedu1.hspspringmvc1.annotation.Service;
import com.hspedu1.service.GoodsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-07-20:19
 * @description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {


    @Override
    public List<Goods> goodsList() {

        // 模拟数据
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods(100,"台灯"));
        goods.add(new Goods(200,"沙发"));
        goods.add(new Goods(300,"桌子"));
        goods.add(new Goods(400,"自行车"));


        return goods;
    }

    @Override
    public List<Goods> goodsJson(String goodsName) {
        // 模拟数据
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods(100,"台灯"));
        goods.add(new Goods(200,"沙发"));
        goods.add(new Goods(300,"桌子"));
        goods.add(new Goods(400,"自行车"));
        goods.add(new Goods(500,"沙发子"));

        // 创建新的集合 用于返回满足条件的数据的 集合
        ArrayList<Goods> goodsArrayList = new ArrayList<>();

        for (Goods good : goods) {
            // 注意下面这里不要写反 写反了不对
            if (good.getName().contains(goodsName)){
                goodsArrayList.add(good);
            }
        }
        return goodsArrayList;
    }
}
