package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.User;
import team.redrock.weiBo.dao.JDBC;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//status 200
@WebServlet(name = "Login",value="/login")

//登录 接口
public class Login extends HttpServlet {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        conn = JDBC.getConnection();
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        try {
            pstmt = conn.prepareStatement("select * from user where userID = ? and password = ?");
            pstmt.setString(1,userID);
            pstmt.setString(2,password);
            resultSet = pstmt.executeQuery();
            RequestDispatcher rdd = null;
            if (!resultSet.next()) {
                request.setAttribute("msg", "用户名或者密码错误");
                request.getSession().setAttribute("loginflag", "login_error"); //登录失败，将登录状态改为失败
                response.sendRedirect("http://localhost:8666/signIn1.0/signIn.html");   //失败后跳转到  登录界面
            }else {
                request.setAttribute("msg","用户名和密码均正确");
                //如果登录成功，则在用户的session对象中保存一个flag，值为login_success
                request.getSession().setAttribute("loginflag", "login_success");
                //并且在session中保存 u_name和userID用于验证身份
                session.setAttribute("u_name", resultSet.getString("u_name"));
                session.setAttribute("userID",userID);
                response.sendRedirect("http://localhost:8666/homePageSignIn/homePageSignIn.html");
//                rdd = request.getRequestDispatcher("../../../../../webapp/homePageSignIn/homePageSignIn.html");
//                rdd.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
