package com.hspedu.jdbc.preparedstatement_;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author 韩顺平
 * @version 1.0
 * 演示PreparedStatement使用
 */
@SuppressWarnings({"all"})
public class PreparedStatement_ {
    public static void main(String[] args) throws Exception {

        //看 PreparedStatement类图

        Scanner scanner = new Scanner(System.in);

        //让用户输入管理员名和密码
        System.out.print("请输入管理员的名字: ");  //next(): 当接收到 空格或者 '就是表示结束
        String admin_name = scanner.nextLine(); // 老师说明，如果希望看到SQL注入，这里需要用nextLine
        System.out.print("请输入管理员的密码: ");
        String admin_pwd = scanner.nextLine();

        //通过Properties对象获取配置文件的信息

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //1. 注册驱动
        Class.forName(driver);//建议写上

        //2. 得到连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //3. 得到PreparedStatement
        //3.1 组织SqL , Sql 语句的 ? 就相当于占位符
        String sql = "select name , pwd  from admin where name =? and pwd = ?";
        //3.2 preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.3 给 ? 赋值
        // 在下面的两个setString 方法中才是解决注入问题的！！ set时会检查sql语句是不是有问题
        // 就进行了一个预处理 这样效率就会提升
        // parameterIndex - 第一个参数是1，第二个是2，...
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //4. 执行 select 语句使用  executeQuery
        //   如果执行的是 dml(update, insert ,delete) executeUpdate()
        //   这里执行 executeQuery ,不要在写 sql

        ResultSet resultSet = preparedStatement.executeQuery();
//        ResultSet resultSet = preparedStatement.executeQuery(sql); //相当于下一行所写：
//        ResultSet resultSet = preparedStatement.executeQuery("select name , pwd  from admin where name =? and pwd = ?";);

        //ResultSet对象保持一个光标指向其当前的数据行。
        //最初，光标位于第一行之前。 next方法将光标移动到下一行，
        //并且由于在ResultSet对象中没有更多行时返回false ，
        //因此可以在while循环中使用循环来遍历结果集。
        if (resultSet.next()) { //如果查询到一条记录，则说明该管理存在
            System.out.println("恭喜， 登录成功");
        } else {
            System.out.println("对不起，登录失败");
        }

        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();


    }
}
