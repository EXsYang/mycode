package com.atguigu.exer;

import java.util.ArrayList;

/**
 * @author yangda
 * @description:
 * @create 2022-11-17-9:28
 */
public class Homework01 {
    public static void main(String[] args) {
        News n1 = new News("新闻一：123456789101112");
        News n2 = new News("新闻二：123456789101112");
        News n3 = new News("新闻三：123");

        ArrayList list = new ArrayList();

        list.add(n1);
        list.add(n2);
        list.add(n3);

        for (int i = list.size() - 1; i >= 0; i--) {
//            System.out.println(list.get(i));
            News news = (News) list.get(i);
            if (news.getTitle().length() > 15){
                news.setTitle(news.getTitle().substring(0,15) + "...");
            }
            System.out.println(list.get(i));
        }



    }
}
class News{

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}