package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.entity.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yangda
 * @create 2023-10-27-13:13
 * @description:
 */
public class HspExecutor implements Executor {

    //定义属性 需要从hspConfiguration拿到数据库相关的连接 使用build()方法
    private HspConfiguration hspConfiguration = new HspConfiguration();

    /**
     * 根据 sql 查找结果
     * @param sql 这里做了简化 原生的 statement 指的是要执行的目标方法
     * @param parameter
     * @param <T> 返回数据库查询回来的结果 将返回的结果集封装成monster对象
     * @return
     */
    @Override
    //public <T> T query(String statement, Object parameter) {
    public <T> T query(String sql, Object parameter) {

        Connection connection = null;
        //选择接口的PreparedStatement import java.sql.PreparedStatement;
        PreparedStatement preparedStatement = null;
        //查询返回的结果集
        ResultSet resultSet = null;

        try {

            connection = getConnection();
            // 通过连接 拿到 预处理对象 preparedStatement

            preparedStatement = connection.prepareStatement(sql);

            // 给sql 中的 ? 赋值
            // 假设只有一个问号 sql语句 是 select * from monster where id = ?
            // 在下面的两个setString 方法中才是解决注入问题的！！ set时会检查sql语句是不是有问题
            // 就进行了一个预处理 这样效率就会提升
            // parameterIndex - 第一个参数是1，第二个是2，...
            preparedStatement.setString(1,String.valueOf(parameter));

            resultSet = preparedStatement.executeQuery();

            // 简化 认为返回的结果集 就是一个Monster对象
            // 将其封装成Monster对象 实际肯定是要用反射的
            Monster monster = new Monster();

            // 使用结果集 resultSet 的 next() 方法 判断 结果集中是否还有下一行
            // 让光标向后移动，如果没有更多行，则返回false 当调用next方法返回false时，光标位于最后一行之后。
           while (resultSet.next()){

               /* private Integer id;
                    private Integer age;
                    private String name;
                    private String email;
                    private Date birthday;
                    private double salary;
                    private Integer gender;*/

               monster.setId(resultSet.getInt("id"));
               monster.setAge(resultSet.getInt("age"));
               monster.setName(resultSet.getString("name"));
               monster.setEmail(resultSet.getString("email"));
               monster.setBirthday(resultSet.getDate("birthday"));
               monster.setSalary(resultSet.getDouble("salary"));
               monster.setGender(resultSet.getInt("gender"));


           }

           return (T) monster;


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {

            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (connection != null){
                    connection.close();
                }


            } catch (Exception throwables) {
                throwables.printStackTrace();
            }


        }


        return null;
    }

    //编写方法,通过HspConfiguration对象，返回连接
    public Connection getConnection(){
        Connection connection = null;

        try {
            // 拿到数据库连接
            connection = hspConfiguration.build("yd_config.xml");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
