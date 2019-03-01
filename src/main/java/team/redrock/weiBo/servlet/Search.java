package team.redrock.weiBo.servlet;
import team.redrock.weiBo.been.SearchObjcet;
import team.redrock.weiBo.dao.SearchDao;
import team.redrock.weiBo.service.impl.SearchService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

//status 200
@WebServlet(name = "Search",value = "/search")

//包含 模糊查询 用户昵称 和微博内容的，也包含了 搜索一次相应在热搜表 search中增加一次搜索次数 搜索接口
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String content =request.getParameter("content");

        SearchDao searchDao = new SearchDao();
        searchDao.insertSearch(content);   //每搜索一次 更新 search表，热搜功能

        HashMap<String,List<SearchObjcet>> hashMap = new SearchDao().searchWeiboUser(content);
        String json = new SearchService().createSearchJson(hashMap);

       response.getWriter().write(json);
    }
}
