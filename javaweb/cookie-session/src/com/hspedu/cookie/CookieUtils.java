package com.hspedu.cookie;

import javax.servlet.http.Cookie;

/**
 * @author yangda
 * @description:
 * @create 2023-06-07-13:42
 */
public class CookieUtils {

    public static Cookie readCookieByName(String name, Cookie[] cookies) {
        if (name == null || "".equals(name) || cookies == null || cookies.length == 0) {
            System.out.println("读不了，cookie名为空或cookies为空");
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                System.out.println("找到了， value= " + cookie.getValue());
                return cookie;
            }
        }

        return null;


    }

}
