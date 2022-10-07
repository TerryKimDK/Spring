package com.test.dBUtil;

import java.sql.*;

public class DBUtil {

    private static DBUtil instance;
    private Connection conn;
    public DBUtil(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("오류 : " + e.getStackTrace());
        }
    }

    // 접속종료
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        close(conn);
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            close(conn, pstmt);
        }
    }
    public static DBUtil getInstance(){
        if(instance == null) {
            instance = new DBUtil();
        }
        return instance;

    }
    public Connection getConnection() {
        return this.conn;
    }
}