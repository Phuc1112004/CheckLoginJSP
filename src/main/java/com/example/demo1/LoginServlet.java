package com.example.demo1;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean loginStatus = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getMySQLConnection();
            if (conn == null) {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
            } else {
                String query = "SELECT * FROM accounts WHERE name = ? AND password = ?";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);

                rs = stmt.executeQuery();

                if (rs.next()) {
                    loginStatus = true;
                } else {
                    System.out.println("Đăng nhập không hợp lệ");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
        }

        if (loginStatus) {
            req.setAttribute("username", username);
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        } else {
            req.setAttribute("username", username);
            req.getRequestDispatcher("fail.jsp").forward(req, resp);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}