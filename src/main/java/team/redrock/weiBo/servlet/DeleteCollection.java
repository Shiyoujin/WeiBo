package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.Collection;
import team.redrock.weiBo.dao.CollectionDao;

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

@WebServlet(name = "DeleteCollection",value = "/DeleteCollection")

//取消收藏的接口
public class DeleteCollection extends HttpServlet {
    //这里使用两个静态常量 来节省开销
    //取消收藏成功
    private static final String SUCCESS = "{\"status\":\"10000\"}";
    private static final String ERROR = "{\"status\":\"10001\")}";
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先进行 身份验证
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String userID = String.valueOf(session.getAttribute("userID"));

        String pid = request.getParameter("id");
        CollectionDao collectioncoDao =new CollectionDao();
        String result =ERROR;
        //如果取消收藏成功 就返回SUCCESS

            if (collectioncoDao.deleteCollection(userID,pid)){
                result = SUCCESS;
            }


        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );

        writer.write(result);
        writer.flush();
        writer.close();
    }



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
