package com.hspedu.spring.test;

import com.hspedu.spring.bean.Monster;
import com.hspedu.spring.hspapplicationcontext.HspApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-08-31-18:51
 */
public class HspApplicationContextTest {
    public static void main(String[] args) throws Exception {
        //HspApplicationContext hspApplicationContext = new HspApplicationContext("beans.xml");
        //
        //Object monster01 = hspApplicationContext.getBean("monster01");
        //System.out.println("monster01=" + monster01);

        HspApplicationContext ioc =
                new HspApplicationContext("beans.xml");

        Monster monster01 = (Monster)ioc.getBean("monster01");

        System.out.println("monter01=" + monster01);
        System.out.println("monster01.name=" + monster01.getName());
        System.out.println("ok");

    }
}
