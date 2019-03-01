package team.redrock.weiBo.servlet;

import team.redrock.weiBo.dao.GreatDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Great",value = "/great")

//点赞接口   点赞、取消点赞 合二为一
public class Great extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String userID = String.valueOf(session.getAttribute("userID"));
        int id = Integer.parseInt((String) session.getAttribute("id"));


        String result= request.getParameter("status");
        GreatDao greatDao = new GreatDao();


        if (result.equals("great")){    //此处不能用 ==
            greatDao.insertGreat(id,userID);
        String number = greatDao.greatNumber(id);
        greatDao.updateG_number(number,id);
            if (greatDao.insertGreat(id,userID)){

            }
            }else if (result.equals("cancel") )
            {
                try {
                    greatDao.deleteGreat(id,userID);
                    String newGreatNumber = greatDao.greatNumber(id);
                    greatDao.updateG_number(newGreatNumber,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
