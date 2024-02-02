package com.hspedu.entity;

/**
 * @author yangda
 * @create 2023-11-03-12:40
 * @description: 双向映射 一对多，多对一
 */
public class Pet {

    private Integer id;
    private String nickname;
    // 一个宠物对应一个主人
    private User user;

    public Pet() {
    }

    public Pet(Integer id, String nickname, User user) {
        this.id = id;
        this.nickname = nickname;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 会出现栈溢出 StackOverflowError
    // 原因:
    // 在UserMapper.xml 的 resultMap 中使用  collection 标签 如果没有指定 集合的javaType
    // mybatis底层会 进行类型推断 默认封装为'class java.util.ArrayList'
    // ArrayList 直接输出 也会调用集合中元素的toString 方法
    // 如果是双向的映射，需要把其中一个toString方法拿掉，就可以解决栈溢出问题

    // @Override
    // public String toString() {
    //     return "Pet{" +
    //             "id=" + id +
    //             ", nickname='" + nickname + '\'' +
    //             ", user=" + user +
    //             '}';
    // }
}
