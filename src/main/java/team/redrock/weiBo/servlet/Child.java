package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.Detail;
import team.redrock.weiBo.dao.DetailDao;
import team.redrock.weiBo.service.impl.CareService;
import team.redrock.weiBo.service.impl.ChildService;
import team.redrock.weiBo.service.impl.DetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Child",value = "/child")
//展示评论 回复 而组装的json
public class Child extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));

        DetailDao detailDao = new DetailDao();

        ChildService childService = new ChildService();

        List<team.redrock.weiBo.been.Child> childList = detailDao.findChild(id);

        String json = childService.createChildJson(childList);

        response.getWriter().write(json);   //展示评论 回复 而组装的json

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
