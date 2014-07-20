/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class JDBCManager {

    private static final String DREVERCLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433; DatabaseName=D01jiangnan";
    private static final String USERNAME = "sa";//默认用户名
    private static final String PASSWORD = "123456";//密码
    protected static Connection conn;
    protected static Statement stmt;
    protected static ResultSet re;
    private static String CurUserName;

    public ResultSet getResult(String sql) {
        try {
            Class.forName(DREVERCLASS);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            re = stmt.executeQuery(sql);
            return re;
        } catch (Exception e) {
            System.out.println("getResult..." + e.toString());
            return null;
        }
    }

    public boolean executeSql(String sql) {
        try {
            Class.forName(DREVERCLASS);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.commit();
            return true;
        } catch (Exception e) {
            System.out.println("executeSql----" + e.toString());
            return false;
        }
    }

    public void setCurUserName(String curUserName) {
        CurUserName = curUserName;
    }

    public String getCurUserName() {
        return CurUserName;
    }

    public void closeJDBC() {
        if (re != null) {   // 关闭记录集   
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {   // 关闭声明   
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {  // 关闭连接对象   
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
