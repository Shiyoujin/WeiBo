package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.SearchObjcet;
import team.redrock.weiBo.dao.SearchDao;
import team.redrock.weiBo.service.impl.HotSearchService;
import team.redrock.weiBo.service.impl.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HotSearch",value = "/hotSearch")
//热搜排行的 json  content 和 number
public class HotSearch extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        SearchDao searchDao = new SearchDao();
        List<SearchObjcet> list = searchDao.findSearchAll();
        HotSearchService hotSearchService = new HotSearchService();
        String json = hotSearchService.createHotSearch(list);
        response.getWriter().write(json);    //热搜排行的 json  content 和 number

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
