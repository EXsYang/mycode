package at.sgg;

import com.hspedu.Hello;

/**
 * @author yangda
 * @create 2024-04-17-0:59
 * @description: 测试引入自己的项目打包的jar，
 * 首选要确定java-project-maven已经安装了(在指定的/本地的仓库中)，
 * 在pom.xml文件中写好要引入的项目的 groupId artifactId version 即可导入
 * <dependency>
 * <groupId>com.hspedu</groupId>
 * <artifactId>java-project-maven</artifactId>
 * <version>1.0-SNAPSHOT</version>
 * </dependency>
 */
public class Hi {


    public String Sum2() {

        Hello hello = new Hello();

        String sum = hello.sum(2, 3);

        return sum;
    }

}
