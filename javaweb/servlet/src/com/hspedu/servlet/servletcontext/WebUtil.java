package com.hspedu.servlet.servletcontext;

import javax.servlet.ServletContext;

/**
 * @author yangda
 * @description:
 * @create 2023-05-24-23:21
 */
public class WebUtil {
    public static Integer visitCount(ServletContext servletContext){
        Object visit_count = servletContext.getAttribute("visit_count");

        if (visit_count == null) {
            servletContext.setAttribute("visit_count", 1);
            visit_count = 1;
        } else {
            visit_count = Integer.parseInt(visit_count + "") + 1;
            servletContext.setAttribute("visit_count", visit_count);
        }

        return Integer.parseInt(visit_count + "");


    }


}
