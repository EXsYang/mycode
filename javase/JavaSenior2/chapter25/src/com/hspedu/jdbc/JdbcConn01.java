package com.hspedu.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author yangda
 * @description: 连接mysql的5种方式
 * @create 2023-04-17-17:30
 */
public class JdbcConn01 {
    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();//创建driver对象，com.mysql.jdbc.Driver;
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        //将 用户名和密码放入到Properties 对象
        Properties properties = new Properties();
        //说明 user 和 password 是规定好的，后面的值根据实际情况写
        properties.setProperty("user", "root");
        properties.setProperty("password", "hsp");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);

    }

    @Test
    public void connect02() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //使用反射加载Driver类，动态加载，更加的灵活，减少依赖性
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/hsp_db02";

        Properties properties1 = new Properties();
        properties1.setProperty("user", "root");
        properties1.setProperty("password", "hsp");

        Connection connect1 = driver.connect(url, properties1);
        System.out.println("方式二：" + connect1);


    }

    @Test
    public void connect03() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //使用反射加载Driver类，动态加载，更加的灵活，减少依赖性
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //创建url,user,password
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        String user = "root";
        String password = "hsp";

        DriverManager.registerDriver(driver);//注册Driver驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第三种方式：" + connection);


    }

    //方式4: 使用Class.forName 自动完成注册驱动，简化代码
    //这种方式获取连接是使用的最多，推荐使用
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        //创建url,user,password
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        String user = "root";
        String password = "hsp";
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("第四种方式：" + connection);

    }

    //方式5 , 在方式4的基础上改进，增加配置文件，让连接mysql更加灵活
    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        //通过Properties对象获取配置文件的信息
        Properties properties1 = new Properties();
        properties1.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties1.getProperty("user");
        String password = properties1.getProperty("password");
        String driver = properties1.getProperty("driver");
        String url = properties1.getProperty("url");

        Class.forName(driver);//建议写上
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式5 " + connection);


    }


}
