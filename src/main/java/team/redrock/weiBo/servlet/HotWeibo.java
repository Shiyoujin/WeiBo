package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.Post;
import team.redrock.weiBo.dao.HotWeiboDao;
import team.redrock.weiBo.service.impl.HotWeiboService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HotWeibo",value = "/hotWeibo")

//微博热门 的 json
public class HotWeibo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HotWeiboDao hotWeiboDao = new HotWeiboDao();
        List<Post> list = hotWeiboDao.findHotPostAll();
        HotWeiboService hotWeiboService = new HotWeiboService();
        String json = hotWeiboService.createHotWeibo(list);

        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
