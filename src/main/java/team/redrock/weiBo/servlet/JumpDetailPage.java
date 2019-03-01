package team.redrock.weiBo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpExchange;
import java.io.IOException;

@WebServlet(name = "JumpDetailPage",value = "/jumpDetailPage")

//跳转到 微博的详情页
public class JumpDetailPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        session.setAttribute("id",id);   //跳转详情页时  保存一个 微博id

        response.sendRedirect("http://localhost:8666/details/details.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
