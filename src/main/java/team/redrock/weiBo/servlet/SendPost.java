package team.redrock.weiBo.servlet;

import team.redrock.weiBo.dao.PostDao;
import team.redrock.weiBo.dao.U_pic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SendPost",value = "/sendPost")

//发表微博的 接口
public class SendPost extends HttpServlet {
    //这里使用两个静态常量来 节省开销
    //关注成功
    private static final String SUCCESS = "{\"status\":\"10001\"}";
    //关注失败
    private static final String ERROR = "{\"status\":\"10000\"}";
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");

        //身份验证

        HttpSession session = request.getSession();
        Date now = new Date();
        PostDao postDao = new PostDao();


        String p_id = "0";           //标识这是 发送的微博 p_id = 0
        String content = request.getParameter("content");
        String userID = String.valueOf(session.getAttribute("userID"));
        String u_name = String.valueOf(session.getAttribute("u_name"));
//        String userID = request.getParameter("userID");
//        String u_name = request.getParameter("u_name");
        String p_times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
//        String p_times = request.getParameter("p_times");
        String g_number = "0";

        String c_number = "0";

        U_pic u_pic = new U_pic();  //根据 userID 查询用户的头像

        String o_opic = u_pic.findU_pic(userID);


//        Part part = request.getPart("photo");   //自己需要前端设置参数 对应上，先默认 photo

//        String filename = getFilename(part);

//        String p_image = "C:/Users/white matter/IdeaProjects/WeiBo/src/main/webapp/UserPic"+filename;

        //发送微博
        String result = ERROR;

        try {
            if(postDao.sendPost(p_id,content,userID,u_name,p_times,g_number,c_number,o_opic))
                result = SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
        } {

        }
        
        response.getWriter().write(result  + p_times);
    }

//    public String getFilename(Part part){
//        String header = part.getHeader("Content-Disposition");
//        String filename = header.substring(header.indexOf("filename=\"") + 10,header.lastIndexOf("\""));
//        return filename;
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
