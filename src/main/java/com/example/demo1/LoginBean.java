package com.example.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBean {
    public boolean checkLogin(String usr,String pwd) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getMySQLConnection();
            if (conn == null) {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return false;
            } 

            String query = "SELECT * FROM accounts WHERE name = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usr);
            stmt.setString(2, pwd);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                System.out.println("Đăng nhập không hợp lệ");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static void main(String[] args) throws SQLException {
        LoginBean bean = new LoginBean();
        String username = "phuc2";
        String password = "12345678";


    boolean loginStatus = bean.checkLogin(username, password);


        if (loginStatus) {
        System.out.println("Đăng nhập thành công!");
    } else {
        System.out.println("Tên người dùng hoặc mật khẩu không đúng.");
    }
}
}
