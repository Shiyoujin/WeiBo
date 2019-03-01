package team.redrock.weiBo.servlet;

import javafx.geometry.Pos;
import team.redrock.weiBo.been.Care;
import team.redrock.weiBo.been.Post;
import team.redrock.weiBo.dao.*;
import team.redrock.weiBo.service.impl.MutualityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//status200
@WebServlet(name = "PersonNumber",value = "/personNumber")

//用户登录后， 主页展示数据 所需要的 json
public class PersonNumber extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=utf-8");   因为涉及 图片上传，故不能如此设置
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String userID = String.valueOf(session.getAttribute("userID"));

        String  json;

        WeiboDao weiboDao = new WeiboDao();

        MutualityDao mutualityDao = new MutualityDao();

        MutualityService mutualityService = new MutualityService();

        PostDao postDao = new PostDao();

        JDBC jdbc = new JDBC();

        String u_name = jdbc.findU_name(userID); //查询 u_name

        U_pic u_picc = new U_pic();  //查询用户头像

        List<Post> listU = new ArrayList<>();  //用户自己微博的集合

        List list;     //得到关注的人 id集合

        List<Care> listC;    //关注的人微博集合

        List<String> listM;  //该用户 关注人数，粉丝人数

        try {
            listU =weiboDao.findUserWeibo("0",userID); //得到 用户 post 集合
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String u_intro = jdbc.findUserIntro(userID);

        String u_pic = u_picc.findU_pic(userID);                      //获取 用户的头像

        String postNumber = postDao.findPostNumber(userID,"0");   //获取用户发 微博的数量

        list = mutualityDao.findCareId(userID);      //得到关注的人id 集合

        listC = mutualityDao.findCareWeibo(list,"0");  //得到关注的人 care 集合

        listM = mutualityDao.numberFollow(userID);   //得到该用户 关注、粉丝人数的 集合

        json = mutualityService.createWeibojson(listU,listC,listM,u_name,postNumber,u_pic,u_intro);  //组装 登录后 主页 的json

        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
