package arthas;

import java.io.IOException;

/**
 * 启动程序类加载器案例
 * `sc`指令查看 JVM 已加载的类信息
 * 在arthas中使用`sc -d java.lang.String`指令查看，
 * 会发现class-loader那一行什么也不显示，即为启动类加载器加载的
 *
 * `sc`指令的[d]选项，输出当前类的详细信息，
 * 包括这个类所加载的原始文件来源、类的声明、加载的 ClassLoader 等详细信息。
 * 如果一个类被多个 ClassLoader 所加载，则会出现多次
 */
public class BootstrapClassLoaderDemo {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);

        System.in.read();
    }
}
