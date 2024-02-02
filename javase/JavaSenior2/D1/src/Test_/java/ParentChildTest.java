package Test_.java;

/**
 * @author yangda
 * @create 2022-11-02-22:13
 */
public class ParentChildTest {
    public static void main(String[]args) {

        Parent parent=new Parent();

        parent.printValue();//Parent.printValue(),myValue="+100

        Child child=new Child();

        child.printValue();//Child.printValue(),myValue="+200

        parent = child;//子类对象地址赋给父类对象引用，这里是多态

        parent.printValue();//Child.printValue(),myValue="+200

        parent.myValue++;//这里是子类加1  200

        parent.printValue();//Child.printValue(),myValue="+201

        ((Child)parent).myValue++;//201  后加1

        parent.printValue();//Child.printValue(),myValue="+202

    }

}

class Parent{

    public int myValue=100;

    public void printValue() {

        System.out.println("Parent.printValue(),myValue="+myValue);

    }

}

class Child extends Parent{

    public int myValue=200;

    public void printValue() {

        System.out.println("Child.printValue(),myValue="+myValue);

    }

}
