package hsp.java;

/**
 * @author yangda
 * @create 2022-11-01-19:49
 */
public class InnerClassExercise {
    public static void main(String[] args) {

        //基于类的匿名内部类
        CellPhone cellPhone = new CellPhone();



        //基于接口的匿名内部类
//        cellPhone.alarmclock(new Bell() {//
//            @Override
//            public void ring() {
//                System.out.println("懒猪起床了");
//            }
//        });

        new CellPhone(){
            public void alarmclock(Bell bell){//闹钟功能
                //bell.ring();
                System.out.println("重写了");
            }
        }.alarmclock(new Bell() {
            @Override
            public Bell ring() {
                System.out.println("闹铃响了！！！");
                return new Bell() {
                    @Override
                    public Bell ring() {
                        System.out.println(333);
                        return new Bell() {
                            @Override
                            public Bell ring() {
                                return null;
                            }
                        };
                    }
                };
                };
            }.ring()
        );//形参位置    emmm




        //连续匿名内部类
//        new CellPhone() {
//            public void alarmclock(Bell bell){//闹钟功能
//                //为什么这里不能重写
//                bell.ring();
//            }
//        }.alarmclock(new Bell() {
//            @Override
//            public void ring() {
//                System.out.println("小伙伴上课了");
//            }
//        });
    }
}

//测试手机类的闹钟功能，通过匿名内部类（对象）作为参数打印：懒猪起床了
//在传入另一个匿名内部类（对象），打印：小伙伴上课了
interface Bell{
    Bell ring();
}

class CellPhone{
    public void alarmclock(Bell bell){//闹钟功能
        bell.ring();
    }
}