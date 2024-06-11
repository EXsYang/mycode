package com.atguigu.java1;

import java.util.Date;

/**
 * @author shkstart
 * @create 2020 下午 6:13
 */
public class LocalVariablesTest {
    private int count = 0;

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    //练习：
    public static void testStatic(){
        LocalVariablesTest test = new LocalVariablesTest();
        Date date = new Date();
        int count = 10;
        System.out.println(count);
        //因为this变量不存在于当前方法的局部变量表中！！
//        System.out.println(this.count);
    }

    //关于Slot的使用的理解
    public LocalVariablesTest(){
        this.count = 1;
    }

    public void test1() {
        Date date = new Date();
        String name1 = "atguigu.com"; //引用类型占据一个slot

        // 引用类型的变量info会在 局部变量表 中占据一个索引位置
        // String info = test2(date, name1);

        // 如果将info的声明删掉，没有使用变量接收 test2(date, name1);的返回值
        // ,则在局部变量表中就不再有info变量了,
        // 相当于这里的值没有进行保存，就不能再使用了
        // 因为没有在局部变量表中给它分配空间
        // ,也就是说不能在当前方法再次引用/使用这里的返回值了,
        test2(date, name1);

        System.out.println(date + name1);
    }

    public String test2(Date dateP, String name2) {
        dateP = null;
        name2 = "songhongkang";
        double weight = 130.5;//占据两个slot
        char gender = '男';
        return dateP + name2;
    }

    public void test3() {
        this.count++; //这里test3方法的局部变量表中只有一个 this 占据slot
        //count 不是局部变量!!! 而是成员变量/属性

        /**
         * 成员变量 (count)：在Java中，成员变量（也称为字段或属性）
         * 随对象实例一起存储在堆内存中。
         * 它们与对象的存在周期一致，并且可以通过对象的引用来访问。
         * 在这里，count 是 LocalVariablesTest 类的实例变量。
         *
         * this 关键字：在实例方法中，this 关键字用于指向当前对象的引用。
         * 在test3方法中，this 是一个局部变量，存储在方法的局部变量表中，
         * 且仅在该方法的执行过程中有效。
         *
         * 关于count的访问：通过 this.count++，
         * 你正通过当前对象的引用 (this) 访问其实例变量 count。
         * 因此，this.count++ 实际上涉及对堆中的对象数据的操作。
         *
         * 因此，你的注释是正确的，它准确地描述了在test3方法中，
         * this存在于局部变量表中，而count是随对象存储在堆中的成员变量。
         * 这种区分对于理解Java内存管理是非常重要的。
         */
    }

    //栈帧中的局部变量表的槽位slot是可以重用的,
    // 如果一个局部变量过了其作用域,
    // 那么在其作用域之后声明的新的局部变量就很有可能会复用过期局部变量的槽位,从而达到节省资源的目的。
    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        //变量c使用之前已经销毁的变量b占据的slot的位置
        int c = a + 1;
    }

    /*
    变量的分类：按照数据类型分：① 基本数据类型  ② 引用数据类型
                按照在类中声明的位置分：① 成员变量：在使用前，都经历过默认初始化赋值
                                                类变量： linking的prepare阶段：给类变量默认赋值  ---> initial阶段：给类变量显式赋值即静态代码块赋值 和 直接在声明时显示赋值

                                                实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值
                                       ② 局部变量：在使用前，必须要进行显式赋值的！否则，编译不通过
     */
    public void test5Temp(){
        int num;
        //System.out.println(num);//错误信息：变量num未进行初始化
    }

}
