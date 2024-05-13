package com.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getMySQLConnection() throws SQLException {
        Connection conn = null;
        String hostName = "localhost";
        String dbName = "accounts";
        String userName = "root";
        String password = "";

        //Connection String: chuỗi kết nối( thông tin) đến csdl
//        String connURL = "jdbc:mysql://localhost:3306/fptaptech2024";
//        conn = DriverManager.getConnection(connURL, "root", "");
//        return conn;

        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver JDBC MySQL!");
            e.printStackTrace();
            return null;
        }

        String connURL = "jdbc:mysql://"+ hostName + ":3306/" + dbName;
//        conn = DriverManager.getConnection(connURL,"root","");
        conn = DriverManager.getConnection(connURL,userName,password);
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        if(getMySQLConnection() != null) {
            System.out.println("Kết nối thành công!");
        }
    }
}
