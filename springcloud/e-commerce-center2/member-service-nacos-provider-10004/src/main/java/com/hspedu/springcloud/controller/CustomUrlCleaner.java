package com.hspedu.springcloud.controller;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @create 2024-01-05-22:42
 * @description: 资源清理/清洗，统一处理
 */
@Component
public class CustomUrlCleaner implements UrlCleaner {


    @Override
    public String clean(String originUrl) { //资源清理  /member/get/1

        /**
         * StringUtils 是apache的工具类=> org.apache.commons.lang.StringUtils
         *
         * public static boolean isBlank(String str) {
         *         int strLen;
         *         if (str != null && (strLen = str.length()) != 0) {
         *             for(int i = 0; i < strLen; ++i) { //遍历
         *                 if (!Character.isWhitespace(str.charAt(i))) {
         *                 //不是空格才进来，直接返回false，表示不是Blank 循环结束
         *                 //即这个字符串中只要有一个不是空格就判断结束，证明not blank
         *                     return false;
         *                 }
         *             }
         *          //for循环走完了，也没找到字符串中有一个字符不为空格，即都为空格，返回true,即isBlank
         *             return true;
         *         } else {
         *             return true;
         *         }
         *     }
         */
        //isBlank()方法就是判断 originUrl!=null && 有长度 && originUrl 不是全部为空格
        if (StringUtils.isBlank(originUrl)){
            //如果是isBlank 就按照原来的值直接返回 不做处理
            return originUrl;
        }

        if (originUrl.startsWith("/member/get")){ //如果uri originUrl 是以/member/get开头,进行处理

            //1.如果请求的接口 是/member/get 开头的，比如/member/get/1 , /member/get/10...
            //2.给sentinel 返回的资源名为 /member/get/*
            //3.在sentinel 对 /member/get/* 添加流控规则即可
            return "/member/get/*";
        }

        //将来如果请求的是 order/get/2 之类的 就不做处理，按照原来的值放行
        return originUrl;
    }
}
