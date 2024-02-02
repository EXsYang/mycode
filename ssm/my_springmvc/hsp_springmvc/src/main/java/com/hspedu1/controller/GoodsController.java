package com.hspedu1.controller;

import com.hspedu1.entity.Goods;
import com.hspedu1.hspspringmvc1.annotation.*;
import com.hspedu1.service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-06-13:32
 * @description:
 */
@Controller
public class GoodsController {

    // 定义属性 之后完成自动装配
    // @AutoWired表示要完成属性的装配
    @Autowired
    private GoodsService goodsService;


    // 定义目标方法
    @RequestMapping(value = "/goods/list")
    //public void goodsList(HttpServletRequest request, HttpServletResponse response,
    //                      @RequestParam(value = "name") String goodsName){
    public String goodsList(HttpServletRequest request, HttpServletResponse response,
                          String goodsName){


        System.out.println("GoodsController goodsList 被调用...");

        //System.out.println("通过设置@RequestParam(value = \"name\") 拿到前端参数 goodsName= " + goodsName);
        System.out.println("使用形参名直接匹配请求参数 goodsName= " + goodsName);

        List<Goods> goods =
                goodsService.goodsList();

        System.out.println("goods= " + goods);

        if ("船长".equals(goodsName)){
            // 测试请求转发
            //return "forward:/login_ok.jsp";
            // 测试请求重定向
            //return "redirect:/login_ok.jsp";
            // 默认情况 请求转发
            return "/login_ok.jsp";
        }else {
            return "forward:/login_error.jsp";
        }


    }


    // 定义目标方法
    @ResponseBody
    @RequestMapping(value = "/goods/json")
    public List<Goods> getJsonByGoodsName(HttpServletRequest request, HttpServletResponse response,
                                 String goodsName){


        System.out.println("GoodsController goodsList 被调用...");

        System.out.println(" goodsName= " + goodsName);

        List<Goods> goods =
                goodsService.goodsJson(goodsName);

        System.out.println("goods= " + goods);

            return goods;



    }



}
