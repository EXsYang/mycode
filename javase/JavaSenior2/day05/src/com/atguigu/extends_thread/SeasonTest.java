package com.atguigu.extends_thread;

/**
 * @author yangda
 * @create 2022-06-06-20:20
 */
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        System.out.println(season);//Season{seasonName='春天', seasonDesc='春意盎然'}



    }

}
//自定义枚举类
class Season{
    //1.声明Season对象的属性：private final 修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器，并给对象属性赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象，public static final的
    public static final Season SPRING = new Season("春天","春意盎然");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冬天来了");

    //4.其他诉求1   获取枚举类对象的属性


    public String getSeasonDesc() {
        return seasonDesc;
    }


    public String getSeasonName() {
        return seasonName;
    }

    //4.其他诉求2   提供toString()


    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}