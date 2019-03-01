package team.redrock.weiBo.servlet;
import team.redrock.weiBo.dao.UploadHeadDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@MultipartConfig(location = "C:\\Users\\white matter\\IdeaProjects\\WeiBo\\out\\artifacts\\WeiBo_war_exploded2\\img")  // 或者用 转义 \\
@WebServlet(name = "UploadHead",value = "/uploadPic")

//上传的头像的接口 ，包含更新 user、post表里面 头像图片的地址
public class UploadHead extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userID = String.valueOf(session.getAttribute("userID"));   //交互的时候用

//      String userID = request.getParameter("userID"); //后端接口自己调试
        UploadHeadDao uploadHeadDao = new UploadHeadDao();
        Part part = request.getPart("photo");
        String filename = getFilename(part);
        String url = "../img/" + filename;
        uploadHeadDao.uploadHead(userID,url);
        part.write(filename);
        response.sendRedirect("http://localhost:8666/userHomepage/userHomepage.html");

    }

    public String getFilename(Part part){
        String header = part.getHeader("Content-Disposition");
        String filename = header.substring(header.indexOf("filename=\"") + 10,header.lastIndexOf("\""));
        return filename;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
