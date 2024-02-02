package com.hspedu.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @description:
 * @create 2023-06-27-22:31
 */
public class JavaJson {
    public static void main(String[] args) {
        // javabean  json对象 互相转换

        //创建一个gson对象，做为一个工具对象使用
        Gson gson = new Gson();

        //演示javabean 和 json字符串的转换
        // 创建 Book对象
        Book book = new Book(100, "零基础学Java");

        //1. 演示把javebean -> json字符串
        String strBook = gson.toJson(book);
        System.out.println("strBook= " + strBook); //strBook= {"id":100,"name":"零基础学Java"}

        //2. json字符串->javabean
        //老韩解读
        //(1) strBook 就是 json字符串
        //(2) Book.class 指定将 json字符串转成 Book对象
        //(3) 底层是反射机制
        Book book2 = gson.fromJson(strBook, Book.class);
        System.out.println("book2= " + book2);//book2= Book{id=100, name='零基础学Java'}


        //3. 演示把list对象 -> json字符串
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(200, "天龙八部"));
        bookList.add(new Book(300, "三国演义"));

        //老韩解读, 因为把对象,集合转成字符串, 相对比较简单
        //底层只需要遍历, 按照json格式拼接返回即可
        String strBookList = gson.toJson(bookList);
        System.out.println("strBookList= " + strBookList);

        //4. 演示把json字符串 -> list对象
        //老师解读
        //(1) 如果需要把json字符串 转成 集合这样复杂的类型, 需要使用gson提供的一个类
        //(2) TypeToken , 是一个自定义泛型类, 然后通过TypeToken来指定我们需要转换成的类型
        /*
        com.google.gson.reflect

        public class TypeToken<T> {
            final Class<? super T> rawType;
            final Type type;
            final int hashCode;

            protected TypeToken() {
                this.type = getSuperclassTypeParameter(this.getClass());
                this.rawType = Types.getRawType(this.type);
                this.hashCode = this.type.hashCode();
            }
         */
        //老师解读
        //(1) 返回类型的完整路径java.util.List<com.hspedu.json.Book>
        //(2) gson的设计者，需要得到类型的完整路径，然后进行底层反射
        //(3) 所以gson 设计者就提供TypeToken, 来搞定.

        //二说 TypeToken , 为甚要加 {}

        //(1) 如果我们  new TypeToken<List<Book>>()  提示
        // 'TypeToken()' has protected access in 'com.google.gson.reflect.TypeToken'
        //(2) 因为TypeToken 的无参构造器是protected ， 而 new TypeToken<List<Book>>() 就是调用其无参构造器
        //(3) 根据java基础, 如果一个方法是protected ,而且不在同一个包， 是不能直接访问的, 因此报错
        //(4) 为什么 new TypeToken<List<Book>>(){} 使用就可以，这里就涉及到匿名内部类的知识.
        //(5) 当 new TypeToken<List<Book>>(){} 其实这个类型就是不是 TypeToken 而是一个匿名内部类(子类)
        //(6) 而且这个匿名内部类是有自己的无参构造器(隐式), 根据java基础规则 当执行子类的无参构造器时, 默认super();

        //三说 TypeToken->举一个例子.[对java基础回顾.]
        Type type = new TypeToken<List<Book>>() {
        }.getType();

        List<Book> bookList2 = gson.fromJson(strBookList, type);
        System.out.println("bookList2= " + bookList2);
        System.out.println("bookList2运行类型= " + bookList2.getClass());//class java.util.ArrayList

        //5 map => json字符串
        HashMap<String, Book> bookHashMap = new HashMap<>();
        bookHashMap.put("k1", new Book(600, "史记"));
        bookHashMap.put("k2", new Book(700, "春秋"));

        String strBookHashMap = gson.toJson(bookHashMap);
        System.out.println("strBookHashMap= " + strBookHashMap);


        //6 json字符串 => map
        Type type2 = new TypeToken<Map<String, Book>>() {
        }.getType();
        System.out.println("type2= "+type2);
        Map<String, Book> bookHashMap2 = gson.fromJson(strBookHashMap, new TypeToken<Map<String, Book>>() {
        }.getType());
        System.out.println("bookHashMap2= " + bookHashMap2);
        System.out.println("bookHashMap2运行类型= " + bookHashMap2.getClass());//class com.google.gson.internal.LinkedTreeMap

    }
}
