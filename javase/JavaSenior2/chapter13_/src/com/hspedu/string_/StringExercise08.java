package com.hspedu.string_;

/**
 * @author 韩顺平
 * @version 1.0
 * 测试:只要其中有一个是变量，结果就在堆中。
 *
 * 1. 常量与常量的拼接结果在常量池，原理是编译期优化
 * 2. 常量池中不会存在相同内容的常量。
 * 3. 只要其中有一个是变量，结果就在堆中。变量拼接的原理是StringBuilder
 * 4. 如果拼接的结果调用intern()方法，则主动将常量池中还没有的字符串对象放入池中，并返回此对象地址。
 */
public class StringExercise08 {
    public static void main(String[] args) {
        String a = "hello"; //创建 a对象
        String b = "abc";//创建 b对象
        //老韩解读
        //1. 先 创建一个 StringBuilder sb = StringBuilder()
        //2. 执行  sb.append("hello");
        //3. sb.append("abc");
        //4. String c= sb.toString()
        /**
         * @Override
         *     public String toString() {
         *         // Create a copy, don't share the array
         *         return new String(value, 0, count); //使用new String()的方式创建的String对象
         *     }
         */
        //最后其实是 c 指向堆中的对象(String) value[] -> 池中 "helloabc"
        String c = a + b;
        String d = "helloabc";
        System.out.println(c == d);//真还是假? 是false
        String e = "hello" + "abc";//直接看池， e指向常量池
        System.out.println(d == e);//真还是假? 是true
    }
}
