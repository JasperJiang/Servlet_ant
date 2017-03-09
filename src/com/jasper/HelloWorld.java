package com.jasper;

// 导入必需的 java 库
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

// 扩展 HttpServlet 类
public class HelloWorld extends HttpServlet {


    public static final String url = "jdbc:mysql://127.0.0.1:3306/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "abc123";
    public Connection conn = null;
    public Statement pst = null;


    private String message;

    public void init() throws ServletException
    {
        // 执行必需的初始化
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            String sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
            pst = conn.createStatement();
            pst.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }



        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy()
    {
        // 什么也不做
    }
}
