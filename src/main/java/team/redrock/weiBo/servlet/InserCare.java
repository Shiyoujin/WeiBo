package team.redrock.weiBo.servlet;
import team.redrock.weiBo.dao.MutualityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;


@WebServlet(name = "InsertCare",value = "/insertCare")

//添加关注的接口
public class InserCare extends HttpServlet {
    //这里使用两个静态常量来 节省开销
    //关注成功
    private static final String SUCCESS = "{\"status\":\"10001\"}";
    //关注失败
    private static final String ERROR = "{\"status\":\"10000\"}";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //身份验证
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
//        String userID = request.getParameter("userID");
//        String u_name = request.getParameter("u_name");
        String userID = String.valueOf(session.getAttribute("userID"));

        String u_name = String.valueOf(session.getAttribute("u_name"));

        String follow_id = request.getParameter("follow_id"); //关注人的id
        MutualityDao mutualityDao = new MutualityDao();
        String result = ERROR;
        //需要先判断 用户之前是否关注过，
        //  为关注，插入数据库， 返回SUCCESS
        if (mutualityDao.isCare(userID,follow_id)){
        try {
            if (mutualityDao.newFollow(userID, follow_id)) {
                result = SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().write(result);

        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
