package team.redrock.weiBo.servlet;

import team.redrock.weiBo.dao.SendChildDao;
import team.redrock.weiBo.dao.U_pic;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@MultipartConfig(location = "C:\\Users\\white matter\\IdeaProjects\\WeiBo\\out\\artifacts\\WeiBo_war_exploded2\\img")
@WebServlet(name = "SendChild",value = "/sendChild")

//发表评论、回复的接口
public class SendChild extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();

        Date now = new Date();

        String userID = String.valueOf(session.getAttribute("userID"));

        String u_name = String.valueOf(session.getAttribute("u_name"));

        String p_id = request.getParameter("id");   //获得微博的id,作为 child的 父节点 pic

        String content = request.getParameter("content");

        String p_times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);

        String g_number = "0";

        String c_number = "0";

        U_pic u_pic = new U_pic();

        String o_opic = u_pic.findU_pic(userID);

//        Part part = request.getPart("photo");

//        String filename = getFilename(part);

//        String p_image = "../img/" + filename;

//        part.write(filename);

        SendChildDao sendChildDao = new SendChildDao();

        sendChildDao.sendChild(userID,p_id,p_times,u_name,content,o_opic,g_number,c_number); //发表评论回复

        String newCommentNumber = sendChildDao.newCommentNumber(Integer.parseInt(p_id));  //获取评论 新的评论数

        sendChildDao.updateCommentNumber(newCommentNumber, Integer.parseInt(p_id));       //更新 c_number 字段


        }


//    public String getFilename(Part part) {
//        String header = part.getHeader("Content-Disposition");
//        String filename =header.substring(header.indexOf("filename=\"") + 10,header.lastIndexOf("\""));
//        return filename;
//
//
//
//    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
