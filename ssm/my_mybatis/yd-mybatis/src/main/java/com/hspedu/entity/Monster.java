package com.hspedu.entity;

import lombok.*;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-10-27-13:05
 * @description:
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
