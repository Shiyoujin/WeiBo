package team.redrock.weiBo.servlet;
import team.redrock.weiBo.dao.CollectionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "InsertCollection",value = "/insertCollection")

//添加收藏的 接口
public class InsertCollection extends HttpServlet {
    //这里使用两个静态常量 来节省开销
    //收藏成功
    private static final String SUCCESS = "{\"status\":\"10001\"}";
    //收藏失败
    private static final String ERROR = "{\"status\":\"10000\"}";
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //身份验证
        HttpSession session = request.getSession();
        String userID = String.valueOf(session.getAttribute("userID"));
        String u_name = String.valueOf(session.getAttribute("u_name"));
        String pid = request.getParameter("id");
        CollectionDao collectionDao = new CollectionDao();
        String result = ERROR;

        //先判断用户之前 是否收藏过
        //如果收藏成功 就返回 SUCCESS
        if (collectionDao.isCollectionExist(userID,pid))
        {
            if (collectionDao.insertCollection(userID,u_name,pid))
            {
            result = SUCCESS;
            }
        }

       response.getWriter().write(result);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
