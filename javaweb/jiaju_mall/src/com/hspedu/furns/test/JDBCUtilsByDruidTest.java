package com.hspedu.furns.test;

import com.hspedu.furns.utils.JDBCUtilsByDruid;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yangda
 * @description:
 * @create 2023-07-11-22:03
 */
public class JDBCUtilsByDruidTest {

    @Test
    public void connectionTest() throws SQLException {


        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println("connection= " + connection);
        JDBCUtilsByDruid.close(null,null,connection);


    }
}
