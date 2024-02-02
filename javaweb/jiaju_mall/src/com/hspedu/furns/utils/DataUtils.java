package com.hspedu.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author yangda
 * @description:
 * @create 2023-07-21-17:40
 */
public class DataUtils {

    // 将方法封装到静态方法中 方便使用
    public static <T> T copyParamsToBean(Map map, T bean){


        try {
            // 这里底层进行反射使用的是空参构造器
            BeanUtils.populate(bean,map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    //delete 传入id时 为了防止接收的id 不是一个数字的字符串 比如"hello"
    //工具方法, 将字符串转成整数，否则就返回一个默认值
    public static int parseInt(String val,int defaultVal){
        //int parseInt;
        try {
            //parseInt = Integer.parseInt(val);
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println(val + " 输入的数据格式不正确...");
            return defaultVal;
        }

        //return parseInt; //这种返回方式不够简洁，可以直接在转换完后直接返回！
    }


}
