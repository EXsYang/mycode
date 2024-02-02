package Test_.java;

/**
 * @author yangda
 * @create 2022-11-02-20:50
 */
public class AA {
    public static void main(String[] args) {
        N n = new N();
        n.a(3);
    }
}
class M{
    private int age = 1;
    public void a(int a){
        System.out.println("父类的方法" + a);
        System.out.println(age);
    }
}
class N extends M{
    private int age = 2;
    public void a(int a){
        System.out.println("子类的方法：" + a);
        System.out.println(age);
    }
}
