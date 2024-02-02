package com.atguigu.map_;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-13:18
 */
public class HashMapSource {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("No1","张无忌");
        hashMap.put("No2","张三");
        hashMap.put("No1","张无忌");
        hashMap.put(new Car(),new Person());

        /* public boolean isEmpty() {
             return size == 0;
            }*/
        hashMap.isEmpty();

        Set set = hashMap.entrySet();
//        System.out.println(set.getClass());//HashMap$EntrySet

        for (Object o : set) {
//            System.out.println(o.getClass());//class java.util.HashMap$Node    EntrySet里面元素的，编译类型是Map.Entry 但是运行类型还是HashMap$Node
            //为了从 HashMap$Node中取出k v 先向下转型
            Map.Entry entry = (Map.Entry)o;
            System.out.println(entry.getKey() + "-" + entry.getValue());
//            System.out.println(entry.getValue());
        }
        Set set1 = hashMap.keySet();
//        System.out.println(set1.getClass());//HashMap$KeySet

        for (Object o : set1) {
            System.out.println("key:" + o);
        }


        Collection values = hashMap.values();
//        System.out.println(values.getClass());//HashMap$Values
        for (Object o : values) {
            System.out.println("value:" + o);
        }

//           tab[i] = newNode(hash, key, value, null);
//        1. k-v 最后是： HashMap$Node node = newNode(hash, key, value, null);
//        2. k-v 为了方便程序员的遍历，还会 创建 EntrySet 集合 ，该集合存放的元素的类型 Entry，而一个Entry
//           对象就有 k,v EntrySet<Entry<K,V>> 即： transient Set<Map.Entry<K,V>> entrySet;
//        3. entrySet 中，定义的类型是Map.Entry , 但是实际上存放的还是 HashMap$Node
//           这是因为 static class Node<K,V> implements Map.Entry<K,V> {
//        4. 当把 HashMap$Node 对象 存放到 entrySet 就方便我们的遍历，因为 Map.Entry 提供了重要的两个方法：
//            K getKey();    V getValue();

        //HashMap 的内部类 Node
//        static class Node<K,V> implements Map.Entry<K,V> {
//            final int hash;
//            final K key;
//            V value;
//            HashMap.Node<K,V> next;

//        public interface Map<K,V> { //Map接口
//        interface Entry<K,V> {      //Map接口里定义的Entry接口







    }
}
class Car{

}

class Person{

}