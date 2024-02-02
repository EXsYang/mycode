
package hsp.java;

/**
 * @author yangda
 * @create 2022-11-01-13:26
 */
public class Interface_ {

//    default void B() {
//        //Extension methods can only be used within an interface
//        //扩展方法只能在接口中使用
//
//    }
public static void main(String[] args) {
    T t = new T();
    t.a();
}

}

interface default_ {
    default void a() {//Interface abstract methods cannot have body
        System.out.println("默认方法");
    }
    //public abstract void b();
    //Modifier 'abstract' is redundant for interface methods
    //修饰符'abstract'对于接口方法来说是多余的
    //Modifier 'public' is redundant for interface methods
    // 修饰符'public'对于接口方法来说是多余的
}

interface default_1 {
    default void a() {//Interface abstract methods cannot have body
        System.out.println("默认方法");
    }
}

class X implements default_, default_1 {
    //当T实现的两个接口中，默认方法（Extension methods扩展方法）同名时会报如下的错
//hsp.java.X inherits unrelated defaults for a() from types hsp.java.default_ and hsp.java.default_1
//hp .java. X从类型hp .java.default_和hp .java.default_1继承a()不相关的默认值
//解决方法：实现接口方法
    @Override
    public void a() {

    }
}

interface abstract_ {
    void a();
}
interface abstract_1 {
    void a();

}

class T implements abstract_, abstract_1 {
    //当T实现的两个接口中，抽象方法同名时会报如下的错
//Class 'T' must either(两个选一个、或者） be declared(声明) abstract
// or implement abstract method 'b()' in 'default_'
    @Override
    public void a() {
        System.out.println("LLL");
    }

}