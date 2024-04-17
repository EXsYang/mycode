package at.sgg;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yangda
 * @create 2024-04-17-1:06
 * @description:
 */
public class TestHi {

    @Test
    public void test01(){

        Hi hi = new Hi();
        String sum2 = hi.Sum2();

        Assert.assertEquals("sum=5",sum2);

    }
}
