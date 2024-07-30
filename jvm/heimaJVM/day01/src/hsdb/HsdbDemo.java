package hsdb;

import java.io.IOException;

/**
 * hsdb测试案例
 *
 * jps
 * java -cp sa-jdi.jar sun.jvm.hotspot.HSDB
 *
 */
public class HsdbDemo {
    // public static final int i = 2;
    public static final int i = 0;
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        HsdbDemo hsdbDemo = new HsdbDemo();
        System.out.println(i);
        System.in.read(); //这行代码的作用是让程序不会结束，阻塞在这。
    }
}



