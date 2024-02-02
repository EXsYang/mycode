package com.hsp.exer;

import org.junit.Test;

import java.util.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-19-19:06
 */
public class Homework01 {
   @Test
    public void test1(){
       DAO<Object> dao = new DAO<>();

       User tom = new User(1, 22, "tom");
       User jack = new User(2, 33, "jack");
       User hsp = new User(3, 44, "韩顺平");
       User yang = new User(4, 24, "杨达");
       User rose = new User(5, 54, "rose");
       dao.save("4",tom);
       dao.save("3",jack);
       dao.save("2",hsp);
       dao.save("1",yang);//添加顺序，和取出顺序不同

       System.out.println(dao.get("3"));

       dao.update("2",rose);

       System.out.println(dao.list());

       dao.delete("1");
       System.out.println(dao.list());


   }
}
class DAO<T>{

    Map<String,T> map = new HashMap<>();

    public void save(String id,T entity){
        //保存T类型的对象到Map 成员变量中
        map.put(id,entity);



    }
    public T get(String id){
        //从map中获取id对应的对象

        return  map.get(id);
    }
    public void update(String id,T entity){
        //替换 map 中 key为id的内容，改为entity 对象
        map.put(id,entity);

    }
    public List<T> list(){
        //返回map 中存放的所有的T对象
        List<T> ts = new ArrayList<>();
        Collection<T> values = map.values();
        for (T t : values) {
                ts.add(t);
        }


        return ts;
    }
    public void delete(String id){
        //删除指定 id 对象
        map.remove(id);
    }


}


class User{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
        return "\nUser{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }


}