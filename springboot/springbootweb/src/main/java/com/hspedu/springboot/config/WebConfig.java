package com.hspedu.springboot.config;

import com.hspedu.springboot.bean.Car;
import com.hspedu.springboot.bean.Monster;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yangda
 * @create 2023-12-06-15:57
 * @description: 标注 @Configuration(proxyBeanMethods = false)
 * 1. 表示一个配置类
 * 2. proxyBeanMethods = false , 使用Lite模式
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig {


    /**
     * 注入 bean WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        /**
         * GenericConverter-ConvertiblePair类中的代码 通用转换器类
         * 它有一个源类型和一个目标类型
         * 源类型就是前端传过来的类型, 在这里前端传过来的
         * 就是 "保时捷,66666.6" 底层会把这些参数都看成String类型 然后在进行转换
         * 目标类型就是要转换成的类型, 这里的目标类型就是Car
         * Create a new source-to-target pair.
         * @param sourceType the source type
         * @param targetType the target type
         */
        // public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
        //     Assert.notNull(sourceType, "Source type must not be null");
        //     Assert.notNull(targetType, "Target type must not be null");
        //     this.sourceType = sourceType;
        //     this.targetType = targetType;
        // }


        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                /**
                 * 解读：
                 * 1. 在addFormatters方法中，增加一个自定义转换器
                 * 2. 增加自定义转换器 String -> Car
                 * 3. 增加的自定义转换器会注册到 converters 容器中
                 * 4. converters 底层结构是 ConcurrentHashMap 内置有124个转换器(springboot 2.5.3)
                 * 5. 通过debug可以看到
                 *
                 *  void addConverter(Converter<?, ?> converter); 该方法是注册自定义转换器
                 *  Converter<S, T> converter: 转换器类 要创建的自定义转换器
                 *  T convert(S source); 要实现的方法
                 */


                /**
                 * 直接传入一个匿名内部类的匿名对象 Converter
                 * 注意在创建自定义转换器 new Converter<String, Car>() 时
                 * <String, Car> 第一个位置即源类型，第二个位置为目标类型
                 */
                registry.addConverter(new Converter<String, Car>() {
                    @Override
                    public Car convert(String source) { //source就是传入的字符串,即前端提交的参数 "保时捷,66666.6"
                        //这里就加入你的自定义的转换器业务代码
                        if (!ObjectUtils.isEmpty(source)) {
                            //如果该source字符串不为空 继续进行转换
                            String[] split = source.split(",");
                            //创建目标类型对象 将处理后的字符串填入该对象中
                            Car car = new Car();
                            car.setName(split[0]);
                            car.setPrice(Double.parseDouble(split[1]));
                            return car;
                        }
                        //这里需要返回值,因为有可能代码没有进入if语句,返回null
                        return null;
                    }
                });


                // //换一种写法来注册自定义转换器-方便理解
                //
                // //1. 先创建自定义转换器converter 【第1个自定义转换器】
                // Converter<String, Car> converter = new Converter<String, Car>() {
                //     @Override
                //     public Car convert(String source) { //source就是传入的字符串,即前端提交的参数 "保时捷,66666.6"
                //         System.out.println("走的自定义转换器 String->Car converter");
                //         //这里就加入你的自定义的转换器业务代码
                //         if (!ObjectUtils.isEmpty(source)) {
                //             //如果该source字符串不为空 继续进行转换
                //             String[] split = source.split(",");
                //             //创建目标类型对象 将处理后的字符串填入该对象中
                //             Car car = new Car();
                //             car.setName(split[0]);
                //             car.setPrice(Double.parseDouble(split[1]));
                //             return car;
                //         }
                //         //这里需要返回值,因为有可能代码没有进入if语句,返回null
                //         return null;
                //     }
                // };
                //
                // // 第2个自定义转换器converter2
                // Converter<String, Monster> converter2 = new Converter<String, Monster>() {
                //     @Override
                //     public Monster convert(String source) { //source就是传入的字符串,即前端提交的参数 "保时捷,66666.6"
                //         System.out.println("走的自定义转换器 String->Monster converter2");
                //         return null;
                //     }
                // };
                //
                // // 第3个自定义转换器converter3
                // Converter<String, Car> converter3 = new Converter<String, Car>() {
                //     @Override
                //     public Car convert(String source) { //source就是传入的字符串,即前端提交的参数 "保时捷,66666.6"
                //         System.out.println("走的自定义转换器 String->Car converter3");
                //         //这里需要返回值,因为有可能代码没有进入if语句,返回null
                //         return null;
                //     }
                // };
                //
                //
                // // 2. 注册自定义转换器 添加转化器到 converters key=[源类型->目标类型]
                // // 可以添加更多/多个自定义转换器
                // // 注意: 因为 converters是hashmap集合 因此key是唯一的
                // // 如果添加了多个相同的key=[源类型->目标类型],就会覆盖之前的自定义转换器
                // // 如果添加了converter和converter3 ,因为hashmap key唯一
                // // ,那么 converter3 覆盖 converter
                // registry.addConverter(converter);
                // registry.addConverter(converter2);
                // registry.addConverter(converter3);
                /**
                 * 如果开发中是按照 <input name="car" value="保时捷,66666.6">
                 * 这样提交的 即 String -> Car , 但是如果没有提供 String -> Car转换器
                 * ,就会报400错误, 默认的转换器搞定不了
                 *
                 * 为什么会知道要把String转换成Car类型呢? 因为 input name="car"
                 * , 这个"car" 和 Monster类的car属性有对应关系, 就可以知道Monster对象
                 *  的car属性的类型
                 */




            }
        };

    }

}
