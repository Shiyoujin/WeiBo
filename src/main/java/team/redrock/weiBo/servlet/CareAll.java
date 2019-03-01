package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.Care;
import team.redrock.weiBo.dao.MutualityDao;
import team.redrock.weiBo.service.impl.CareService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CareAll",value = "/careAll")
//提供关注 列表的json
public class CareAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String userID = String.valueOf(session.getAttribute("userID"));

        MutualityDao mutualityDao = new MutualityDao();
        CareService careService = new CareService();
        List<String> list = mutualityDao.findCareId(userID);
        List<Care> listCare = mutualityDao.findCareMessage(list);
        String json = careService.createCareMessageJson(listCare);

        response.getWriter().write(json);    //提供关注 列表的json





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
