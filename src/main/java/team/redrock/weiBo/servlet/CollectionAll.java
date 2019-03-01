package team.redrock.weiBo.servlet;

import team.redrock.weiBo.been.Collection;
import team.redrock.weiBo.dao.CollectionDao;
import team.redrock.weiBo.service.impl.CollectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet(name = "CollectionAll",value = "/collectionAll")
//收藏列表组装的 json
public class CollectionAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String userID = String.valueOf(session.getAttribute("userID"));

        CollectionDao collectionDao =new CollectionDao();
        CollectionService collectionService =new CollectionService();
        List p_idList = collectionDao.findUerCollectionP_id(userID);  //获得 收藏 微博id的集合
        List<Collection> list = collectionDao.findCollectionWeibo(p_idList); //获得 所有收藏 信息
        String collectionNumber = collectionDao.findCollectionNumber(userID);  //获得用户收藏条数
        String json = collectionService.createCollectionJson(list,collectionNumber); //组装 json


        response.getWriter().write(json);  //收藏列表组装的 json


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
