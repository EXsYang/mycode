package com.hspedu.web.datavalid.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yangda
 * @create 2023-10-10-11:17
 * @description:
 */
public class Monster {


    private Integer id;

    //String类型的属性优先使用@NotEmpty 如果是字符串验证空, 建议使用 @NotEmpty
    @NotEmpty(message = "email不能为空")
    private String email;

    /**
     *
     * The annotated element has to be in the appropriate range. Apply on numeric values or string
     * representation of the numeric value.
     *
     * long min() default 0;
     * long max() default Long.MAX_VALUE;
     *
     *  @Range(min = 1,max = 100) 表示后端目标方法接收前端传过来的请求参数/前端提交的,在1-100之间
     *
     */
    @NotNull(message = "age不能为空")
    //@NotEmpty(message = "age不能为空") //不可以在Integer类型的属性上使用@NotEmpty注解 会报错
    @Range(min = 1,max = 100)
    private Integer age;

    /**
     * @NotEmpty 表示name不能为空
     * Assert:断言，生效，明确，肯定 /əˈsɜːrt/
     * Asserts that the annotated string, collection, map or array is not {@code null} or empty {size=0} .
     * @NotEmpty 注解在修饰string, collection, map or array 类型时生效 修饰其他类型不会生效
     */
    //@NotEmpty
    @NotEmpty(message = "name不能为空")
    /**
     * The annotated element must not be {@code null}.
     * Accepts any type.
     *
     *
     */
    //@NotNull
    //@NotNull(message = "name不能为空")
    private String name;

    @NotNull(message = "birthday不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "salary不能为空")
    @NumberFormat(pattern = "###,###.##")
    //下面这里使用包装类！
    //private float salary;
    private Float salary;


    public Monster() {
    }

    public Monster(Integer id, String email, Integer age, String name, Date birthday, Float salary) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
}
