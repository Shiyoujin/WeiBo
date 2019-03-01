package team.redrock.weiBo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Logout",value = "/logout")

//退出登录接口
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;  //？？？？？？？？？

    public Logout(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 自己设置注销 session的值
        request.getSession().invalidate();
        //将网页重定向到首页
        response.sendRedirect(request.getContextPath() + "/homePage/homePage.html");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);


    }




}
