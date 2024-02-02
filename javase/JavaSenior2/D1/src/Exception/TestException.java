package Exception;

import org.junit.Test;

/**
 * @author yangda
 * @description:
 * @create 2023-09-11-19:15
 */
public class TestException {

    @Test
    public void m1() {

        StringBuilder result = (StringBuilder) k1("222");

        System.out.println("result= " + result);


    }

    public Object k1(String str) {

        //测试返回值 返回后下面的代码是否还会执行

        try {
            StringBuilder stringBuilder = new StringBuilder(str);
            return stringBuilder;// 这里返回了 下面就不会再执行了！！！
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        return null;

    }


    // 编写方法 返回容器中的对象
    public Object getBean(String name) {


        // 抛出一个异常 说明传进来的name 不存在 是瞎传了一个name
        // 抛出异常可以代替返回值 就不用在返回了 return
        throw new NullPointerException("没有该bean");
        //
        //return null;
    }


}
