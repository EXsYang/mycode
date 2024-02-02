package com.atguigu.enum_;


/**
 * @author yangda
 * @description:使用enum关键字定义枚举类
 *
 * @create 2022-11-12-9:25
 */
@SuppressWarnings({})
public class EnumTest {
    public static void main(String[] args) {

        Season1 spring = Season1.SPRING;
        System.out.println(spring.toString());//SPRING

        Season1[] values = Season1.values();
        int length = values.length;
        for (int i = 0;i < values.length;i++){
            System.out.println(values[i]);
        }
        Season1 summer = Season1.valueOf("SUMMER");
//        Season1 summer1 = Season1.valueOf("SUMMERe1");//.IllegalArgumentException: No enum constant
        System.out.println(summer);
        System.out.println("******************");
        Season2 autumn = Season2.AUTUMN;
//        System.out.println(autumn.show());Cannot resolve method 'println(void)'
        autumn.show();
        System.out.println();
        Season2[] values1 = Season2.values();
        for (int i = 0; i < values1.length; i++) {
            values1[i].show();
        }




        //***************测试length：**********************
//        Season1[] values = Season1.values();
//        int length = values.length;
        int[] ints = new int[2];
//        ints.length;对象调用属性需要显示的声明一个变量接收一下
        int i = ints.length;
        String[] string = new String[3];
        int s = string.length;
        //基本数据类型数组、String类型数组、对象数组长度都是属性
        //数组的长度是属性

        String s1 = "dsfsf";
        s1.length();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.length();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.length();//对象调用方法可以不用接收
//        String、StringBuffer、StringBuilder的长度是方法
    }

}

enum Season1 {

    SPRING("春天", "春天来了"),

    SUMMER("夏天","夏天来了"),

    AUTUMN("秋天","秋天来了"),

    WINTER("冬天","冬天来了");


    private String seaName;
    private String seaDescribe;

    //私有化构造器
    private Season1(String seaName, String seaDescribe) {
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
        int i;
//        System.out.println(i);//Variable 'i' might not have been initialized,局部变量用的时候才会报错，只声明不会报错
        return seaDescribe;
    }

    public void setSeaDescribe(String seaDescribe) {
        this.seaDescribe = seaDescribe;
    }

//    @Override
//    public String toString() {
//        return "Season{" +
//                "seaName='" + seaName + '\'' +
//                ", seaDescribe='" + seaDescribe + '\'' +
//                '}';
//    }
}
enum Season2 implements Info{

    //情况二：
    SPRING("春天", "春天来了"){
        @Override
        public void show() {
            System.out.println("春天show");
        }
    },

    SUMMER("夏天","夏天来了"){
        @Override
        public void show() {
            System.out.println("夏天show");
        }
    },

    AUTUMN("秋天","秋天来了"){
        @Override
        public void show() {
            System.out.println("秋天show");
        }
    },

    WINTER("冬天","冬天来了"){
        @Override
        public void show() {
            System.out.println("冬天show");
        }
    };


    private String seaName;
    private String seaDescribe;

    //私有化构造器
    private Season2(String seaName, String seaDescribe) {
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

    //情况一：
//    @Override
//    public void show() {
//        System.out.println("直接在实现接口的枚举类中重写");
//    }

//    @Override
//    public String toString() {
//        return "Season{" +
//                "seaName='" + seaName + '\'' +
//                ", seaDescribe='" + seaDescribe + '\'' +
//                '}';
//    }
}
interface Info{
//     show();//Invalid method declaration; return type required
            // 无效的方法声明; 返回类型要求
    void show();
}