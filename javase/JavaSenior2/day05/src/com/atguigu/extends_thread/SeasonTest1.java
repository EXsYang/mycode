package com.atguigu.extends_thread;

/**
 * @author yangda
 * @create 2022-06-06-20:39
 */
public class SeasonTest1 {

}

//使用enum关键字定义枚举类
//说明：定义的枚举类默认继承于java.lang.Enum类
enum Season1{
        //1.提供当前枚举类的对象，多个对象之间用"," 隔开，末尾对象";"结束
    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","冰天雪地");

    //2.声明Season对象的属性：private final 修饰
    private final String seasonName;
    private final String seasonDesc;

    //3.私有化构造器，并给对象属性赋值
    private Season1(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

//4.其他诉求1：获取枚举类对象属性
    public String getSeasonName(){
        return seasonName;
    }
    public String getSeasonDesc(){
        return seasonDesc;
    }
//4.其他诉求2：提供toString()


//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }
}