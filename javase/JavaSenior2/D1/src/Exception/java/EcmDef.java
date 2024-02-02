package Exception.java;


import java.util.Scanner;

/**
 * @author yangda
 * @create 2022-11-02-17:44
 */
public class EcmDef {
    //接收命令行的两个参数，要求不能输入负数，计算两数相除

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int i = scanner.nextInt();
//        int j = scanner.nextInt();

        String i = scanner.next();
        String j = scanner.next();
        EcmDef e = new EcmDef();
        Object ecm = 0;

        try {
            ecm = e.ecm(i, j);
        } catch (Ecdef ecdef) {
            ecdef.printStackTrace();
        }


        System.out.println(ecm);
    }
    public Object ecm(Object a,Object b) throws Ecdef,ArithmeticException {
        if (a instanceof String || b instanceof String){
            String aa = (String)a;
            String bb = (String)b;

            int i = Integer.parseInt(aa);
            int i1 = Integer.parseInt(bb);
            if(i1 == 0){
                throw new ArithmeticException();
            }
            if (i < 0 || i1 < 0){
                throw new Ecdef("输入的是负数，请重新输入");
            }
            Integer c;
            return c = i / i1;

        }
        if (a instanceof Integer && b instanceof Integer){
            Integer aa = (Integer)a;
            Integer bb = (Integer)b;
            if(bb == 0){
                throw new ArithmeticException();
            }
            if (aa < 0 || bb < 0){
                throw new Ecdef("输入的是负数，请重新输入");
            }
            Integer c = aa / bb;
            return c;
        }
        throw new RuntimeException("出错");

    }
}
class Ecdef extends Exception{//自定义异常类，输入的是负数
    static final long serialVersionUID = -3387516993129948L;

    public Ecdef(){

    }
    public Ecdef(String msg){
        super(msg);

    }


}