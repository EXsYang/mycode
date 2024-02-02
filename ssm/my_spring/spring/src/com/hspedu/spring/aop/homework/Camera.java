package com.hspedu.spring.aop.homework;

import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description:
 * @create 2023-09-08-22:51
 */

@Component //将Camera注入到spring容器 使用@Service 也行 只要注入到容器中就行
//但是 该类不是Service类 使用Component更好 把Camera对象当做一个组件注入容器
public class Camera implements UsbInterface{
    @Override
    public void work() {
        System.out.println(" Camera类 work() 方法执行");
    }
}
