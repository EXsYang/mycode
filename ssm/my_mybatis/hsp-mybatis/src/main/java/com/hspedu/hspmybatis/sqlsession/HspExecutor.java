package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.entity.Monster;
import jdk.nashorn.internal.objects.annotations.Where;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yangda
 * @create 2023-10-19-20:02
 * @description:
 */
public class HspExecutor implements Executor {

    //定义属性
    private HspConfiguration hspConfiguration = new HspConfiguration();

    /**
     * 根据 sql 查找结果
     *
     * @param sql
     * @param parameter
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(String sql, Object parameter) {

        Connection connection = null;
        //选择接口的PreparedStatement import java.sql.PreparedStatement;
        PreparedStatement preparedStatement = null;
        //查询返回的结果集
        ResultSet resultSet = null;

        try {
            //得到连接
            connection = getConnection();
            //得到预处理对象 preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数, 如果参数多, 可以使用数组处理.
            //设置预处理对象中的?, 给 ? 赋值
            preparedStatement.setString(1,parameter.toString());
            //preparedStatement.setInt(1,(int)parameter);

            //执行查询操作 返回结果集
            resultSet = preparedStatement.executeQuery();

            // 创建monster对象 用于保存数据库返回来的信息
            //把resultSet数据封装到对象-monster
            //老师说明: 这里老师做了简化处理
            //认为返回的结果就是一个monster记录
            //完善的写法是一套反射机制.
            Monster monster = new Monster();

            //ResultSet对象保持一个光标指向其当前的数据行。
            //最初，光标位于第一行之前。 next方法将光标移动到下一行，
            //并且由于在ResultSet对象中没有更多行时返回false ，
            //因此可以在while循环中使用循环来遍历结果集。
            //遍历结果集, 把数据封装到monster对象
            while (resultSet.next()){ // 结果集中下一行不为空返回true // 让光标向后移动，如果没有更多行，则返回false
                //resultSet.getInt("id"); //获取的是当前行的 列名为"id"的列
                //将结果集返回的信息封装到一个monster对象中
                monster.setId(resultSet.getInt("id"));
                monster.setAge(resultSet.getInt("age"));
                monster.setName(resultSet.getString("name"));
                monster.setEmail(resultSet.getString("email")); // 通过列名来获取值, 推荐
                monster.setBirthday(resultSet.getDate("birthday"));
                monster.setSalary(resultSet.getDouble("salary"));
                monster.setGender(resultSet.getInt("gender"));

            }




            //返回monster对象
            return (T)monster;

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

        // 上面封装时出错 返回null
        return null;
    }

    //编写方法,通过HspConfiguration对象，返回连接
    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = hspConfiguration.build("hsp-mybatis.xml");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }
}
