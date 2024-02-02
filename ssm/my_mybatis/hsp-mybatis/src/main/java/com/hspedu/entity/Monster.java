package com.hspedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;






import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

    /**
     * @author yangda
     * @create 2023-10-19-19:37
     * @description: Monster 和 monster表有映射关系
     *
     * 注意：要使用lombok需要安装 lombok插件(plugin) 否则使用不了
     *
     * 老师解读
     * @Getter 就会给所有属性 生成对应的getter
     * @Setter 就会给所有属性 生成对应的setter
     * @ToString 生成 toString...
     * @NoArgsConstructor 生成无参构造器
     * @AllArgsConstructor 生成要给全参构造器
     * @Data 注解 默认生成的有无参构造器
     * @Data和@AllArgsConstructor 一起使用会覆盖掉@Data中生成的无参构造器 要想使用无参构造器需要再加上@NoArgsConstructor
     *
     * 如何选择主要还是看自己需要
     *
     *
     *
     */
    //@Data
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Monster {

        private Integer id;
        private Integer age;
        private String name;
        private String email;
        private Date birthday;
        private double salary;
        private Integer gender;

    }

