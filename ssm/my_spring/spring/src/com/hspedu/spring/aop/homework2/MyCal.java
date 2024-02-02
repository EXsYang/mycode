package com.hspedu.spring.aop.homework2;

import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description:
 * @create 2023-09-09-22:17
 */


@Component//将Cal对象作为组件，注入到Spring容器
public class MyCal implements Cal{
    @Override
    public int cal1(int n) {

        //int sum = (1 + n) * n / 2;

        int res = 0;
        for (int i=1 ; i <= n ; i++){
            res += i;
        }

        System.out.println("cal1 res= " + res);

        return res;
    }

    @Override
    public int cal2(int n) {

        int res = 1;
        for (int i=1 ; i <= n ; i++){
            res *= i;
        }

        System.out.println("cal2 res~= " + res);

        return res;
    }
}
