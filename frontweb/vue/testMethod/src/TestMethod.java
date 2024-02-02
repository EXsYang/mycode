/**
 * @author yangda
 * @description:
 * @create 2023-08-27-11:33
 */
public class TestMethod {
    private static int i;

    public static void main(String[] args) {
        TestMethod t = new TestMethod();
        t.add();
        System.out.println(TestMethod.i);
    }
    public void add(){
         //i++;
         //i++;
         //i++;   // 会改变原来的值

        int b = i + 2;
    }
}
