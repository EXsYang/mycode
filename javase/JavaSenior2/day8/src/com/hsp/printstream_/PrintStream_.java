package com.hsp.printstream_;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @author yangda
 * @description: PrintStream (字节打印流/输出流)
 *
 *
 *
 *
 *
 *
 * @create 2022-11-29-9:15
 */
public class PrintStream_ {
    public static void main(String[] args) throws IOException {

        PrintStream out = System.out;

        //在默认情况下，PrintStream 输出数据的位置是，显示器 标准输出
        /*
         public void print(String s) {
            if (s == null) {
                s = "null";
            }
            write(s);//
        }
         */

        out.print("杨达在写代码A");
        /*
            Java中PrintStream类的write(int)方法用于在流上写入指定的字节值。
            使用作为整数值传递的字节值的ASCII值指定此字节值。该整数值用作参数。
            用法:
            public void write(int ascii)
            参数：此方法接受强制性参数ascii，该参数是要写入流中的字节值的ASCII值。
            返回值：此方法不返回任何值。
         */
        //因为print() 底层调用的是 write(),所以可以直接调用write() 进行打印(输出)
        out.write(97);//需要关闭流，或者刷新,
    /*    public void write(int b) {
            try {
                synchronized (this) {
                    ensureOpen();
                    out.write(b);
                    if ((b == '\n') && autoFlush) //write(int)只有传入的是个换行符，才自动刷新，不然都需要手动关流或刷新
                        out.flush();
                }
            }*/
        out.write("杨达正在写代码奥".getBytes());
        /*public void write(byte buf[], int off, int len) {
            try {
                synchronized (this) {
                    ensureOpen();
                    out.write(buf, off, len);
                    if (autoFlush)
                        out.flush();//这里调用了刷新
                }
            }*/

//        out.flush();
        out.close();

        //我们可以去修改打印流输出的位置/设备

        System.setOut(new PrintStream("e:/tt.txt"));//这里会自动生成一个文件
        System.out.println("重新设置了位置奥");//不用关流
        /*如果您关闭它，您将无法再写入控制台，因此让我们在进程终止时将此任务留给 VM。
        您应该只关闭您拥有或手动创建的流。 System.out 不在你的控制范围内，所以留给创建者来处理吧。
         */
        System.setOut(System.out);
        System.out.println("我再");
        System.out.println("我再");

        System.setOut(out);
        System.out.println("再键");//不好使！！
        System.out.println("再键");
        System.out.println("再键");

//        System.out.flush();
//        out.close();










    }
}
