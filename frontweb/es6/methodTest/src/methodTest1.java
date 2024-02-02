import java.io.File;

/**
 * @author yangda
 * @description:
 * @create 2023-08-24-10:03
 */
public class methodTest1 {
    public static void main(String[] args) {
        System.out.println(m1("2"));
    }

    private String n;

    /**
     * 这是一个方法注释
     * @param n
     */
    private static String m1(String n){
        System.out.println(n);
        File file = new File("C:\\Users\\yangd\\Desktop\\lifecycle.png");
        if(file.exists()){
            System.out.println("该图片存在");
            file.delete();
            System.out.println("删除成功");
        }
        return n;
    }
}
