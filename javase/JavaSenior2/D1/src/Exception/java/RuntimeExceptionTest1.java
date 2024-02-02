package Exception.java;

/**
 * @author yangda
 * @description:
 * @create 2023-08-15-21:32
 */
public class RuntimeExceptionTest1 {
    public static void main(String[] args) {
        try {
            String a = null;
            //int a = 4/0 ;
            a.equals("");
        }catch (Exception e){
            //会在控制台输出 Exception in thread "main" java.lang.RuntimeException
            //	at Exception.java.RuntimeExceptionTest1.main(RuntimeExceptionTest1.java:13)
            //throw new RuntimeException();


            throw new RuntimeException(e);


        }

    }
}
