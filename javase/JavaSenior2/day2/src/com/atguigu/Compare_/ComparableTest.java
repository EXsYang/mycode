package com.atguigu.Compare_;

import java.util.Arrays;

/**
 * @author yangda
 * @description:自然排序：给对象比较大小，自定义类去实现Comparable接口，重写compareTo()方法
 * @create 2022-11-11-16:19
 */
public class ComparableTest {
    public static void main(String[] args) {

        Goods goods1 = new Goods("dec",233);
        Goods goods2 = new Goods("ccv",23);
        Goods goods3 = new Goods("dcv",23);

        Goods[] goods = new Goods[4];
        goods[0] = goods1;
        goods[1] = goods2;
        goods[3] = goods3;
        goods[2] = new Goods("aaae",3);

        //排序借助了Arrays.sort()
        Arrays.sort(goods);//.ClassCastException: com.atguigu.Comparable_.Goods cannot be cast to java.lang.Comparable
        System.out.println(Arrays.toString(goods));//[Goods{name='毛巾', price=3}, Goods{name='裤衩', price=23}, Goods{name='校服', price=233}]


    }
}
class Goods implements Comparable{
    String name;
    int price ;

    public Goods(String name,int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this == o){
            return 0;
        }
        if (o instanceof Goods) {
            Goods o1 = (Goods) o;
            //方式一：
            if (this.price > o1.price){
                return 1;
            }else if (this.price < o1.price){
                return -1;
            }else {
//                return 0;
//                this.name.compareTo(o1.name);//这里没返回，相当于比较完大小什么也没干
               return this.name.compareTo(o1.name);//价格相同，按照名称从低到高进行排序

            }
//            方式二：
//            return Integer.compare(this.price,o1.price);//Unreachable statement     遥不可及的声明
        }
        throw new RuntimeException("传入的数据类型不对");
//        return 0;
    }
}