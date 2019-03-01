package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.Detail;
import team.redrock.weiBo.dao.DetailDao;
import team.redrock.weiBo.dao.HotWeiboDao;
import team.redrock.weiBo.service.impl.DetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DetailPage",value = "/detailPage")

// 微博的详情页
public class DetailPage extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        int id = Integer.parseInt((String) session.getAttribute("id"));
        String userID = String.valueOf(session.getAttribute("userID"));
        String json = null;

        HotWeiboDao hotWeiboDao = new HotWeiboDao();
        hotWeiboDao.insertHotWeibo(id);

        DetailDao detailDao = new DetailDao();
        DetailService detailService = new DetailService();
        Detail detail = detailDao.findDetatilWeibo(id,userID);
        json = detailService.createDetail(detail);

        response.getWriter().write(json);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
