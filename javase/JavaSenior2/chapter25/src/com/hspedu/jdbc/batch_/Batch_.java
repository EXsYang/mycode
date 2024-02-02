package com.hspedu.jdbc.batch_;

import com.hspedu.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yangda
 * @description:
 * @create 2023-04-28-19:06
 */
public class Batch_ {
    @Test
    public void noBatch() throws Exception {
        //得到连接
        Connection connection = JDBCUtils.getConnection();
        //组织sql
        String sql = "insert into admin2 values(null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1,"tom" + i);
            preparedStatement.setString(2,"444");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方式耗时：" + (end - start));//170197
        //释放资源,关闭连接
        JDBCUtils.close(null,preparedStatement,connection);
    }
    @Test
    public void useBatch() throws Exception {
        //得到连接
        Connection connection = JDBCUtils.getConnection();
        //组织sql
        String sql = "insert into admin2 values(null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1,"tom" + i);
            preparedStatement.setString(2,"444");
            //将 sql 语句加入到批处理包中 -> 看源码
               /*
            //1. //第一就创建 ArrayList - elementData => Object[]
            //2. elementData => Object[] 就会存放我们预处理的sql语句
            //3. 当elementData满后,就按照1.5扩容
            //4. 当添加到指定的值后，就executeBatch
            //5. 批量处理会减少我们发送sql语句的网络开销，而且减少编译次数，因此效率提高
            public void addBatch() throws SQLException {
                synchronized(this.checkClosed().getConnectionMutex()) {
                    if (this.batchedArgs == null) {

                        this.batchedArgs = new ArrayList();
                    }

                    for(int i = 0; i < this.parameterValues.length; ++i) {
                        this.checkAllParametersSet(this.parameterValues[i], this.parameterStreams[i], i);
                    }

                    this.batchedArgs.add(new PreparedStatement.BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull));
                }
            }

             */
            preparedStatement.addBatch();
            //当有1000条记录时，批量执行
            if ((i + 1) % 1000 == 0){//满1000条sql
                preparedStatement.executeBatch();//执行批处理包中的sql语句
                //清空一把
                preparedStatement.clearBatch();//清空批处理包中的sql语句
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("批处理方式耗时：" + (end - start));//批处理方式耗时：578
        //释放资源,关闭连接
        JDBCUtils.close(null,preparedStatement,connection);
    }
}
