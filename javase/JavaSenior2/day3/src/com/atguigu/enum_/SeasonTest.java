package com.atguigu.enum_;

/**
 * @author yangda
 * @description:自定义枚举类
 * @create 2022-11-12-8:55
 */
public class SeasonTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring.toString());//Season{seaName='春天', seaDescribe='春天来了'}


    }
}
/*
自定义枚举类

 */
class Season{

    public static final Season SPRING = new Season("春天","春天来了");
    public static final Season SUMMER = new Season("夏天","夏天来了");
    public static final Season AUTUMN = new Season("秋天","秋天来了");
    public static final Season WINTER = new Season("冬天","冬天来了");


    private String seaName;
    private String seaDescribe;

    //私有化构造器
    private Season(String seaName,String seaDescribe){
        this.seaName = seaName;
        this.seaDescribe = seaDescribe;
    }

    public String getSeaName() {
        return seaName;
    }

    public void setSeaName(String seaName) {
        this.seaName = seaName;
    }

    public String getSeaDescribe() {
        return seaDescribe;
    }

    public void setSeaDescribe(String seaDescribe) {
        this.seaDescribe = seaDescribe;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seaName='" + seaName + '\'' +
                ", seaDescribe='" + seaDescribe + '\'' +
                '}';
    }
}