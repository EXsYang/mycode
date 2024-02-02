package com.atguigu.exer2;

import java.util.ArrayList;

/**
 * @author yangda
 * @create 2022-08-21-20:45
 */
public class NewsTest {
    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList();
        arrayList.add(new News("新冠确诊病例超千万，数百万印度教信徒赴恒河“圣浴”引民众担忧"));
        arrayList.add(new News("男子突然想起两个月前钓的鱼还在网兜里，捞起一看赶紧放生"));

        for (int i = arrayList.size() - 1; i >= 0; i--) {

            /*Object o = arrayList.get(i);
            if (o instanceof News) {
                News o1 = (News) o;
                if (o1.getTitle().length() > 15) {
                    o1.setTitle(o1.getTitle().substring(0, 15) + "...");
                }
            }
            System.out.println(arrayList.get(i));*/
           // System.out.println(arrayList.get(i).getClass());//class com.atguigu.exer2.News
            News news = (News) arrayList.get(i);//这里需要转换成News类型，不然还是一个Object类型
            //Object类型的调用不了News类型的get()方法！！！
            System.out.println(processTitle(news.getTitle()));




        }

    }

    public static String processTitle(String title) {
        if (title == null) {
            return "";
        }
        if (title.length() > 15) {
            return title.substring(0, 15) + "...";
        }
        return "";
    }

}

class News {
    private String title;
    private String details;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}