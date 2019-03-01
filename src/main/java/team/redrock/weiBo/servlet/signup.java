package team.redrock.weiBo.servlet;

import team.redrock.weiBo.dao.JDBC;
import team.redrock.weiBo.dao.UpdateU_introDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//status 200
@WebServlet("/signup")

//注册 的 接口
public class signup extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String u_sex = request.getParameter("u_sex");
        String u_name = request.getParameter("u_name");
        //设置注册时 默认头像
        String u_pic = "./img/1da8362e955db67c3a2b1e09fde69049.jpeg";



        RequestDispatcher rd = null;
        JDBC jdbc = new JDBC();
        //更新个性签名
        UpdateU_introDao updateU_introDao =new UpdateU_introDao();
        //注册检验用户名和用户ID是否存在
        try {
            boolean isExist = false;
            isExist = new JDBC().checkRegiser(userID,u_name);
            if (isExist) {   //true 代表不存在
                //用户名\用户ID没有重复
                //插入数据库
                boolean isSuccess = jdbc.regiserUser(userID, password,u_sex,u_name,u_pic);
                //新注册 默认个性签名
                updateU_introDao.updateU_intro(userID,"这个人很懒，没有什么简介");

                if (isSuccess) {
                    //插入数据库成功
                    //跳转到登录页
                    response.sendRedirect("http://localhost:8666/signIn1.0/signIn.html");
                    //            rd =request.getRequestDispatcher("signIn.html");
                    //            rd.forward(request,response);
                } else if (!isSuccess) {
                    //注册时 插入数据库没有成功
                    //跳转到注册界面
                    response.sendRedirect("http://localhost:8666/signUp1.0/signUp.html");
                }
            } else if (!isExist) {
                //如果 userID或u_name 重复
                //跳转到注册界面
              response.sendRedirect("http://localhost:8666/signUp1.0/signUp.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

