package team.redrock.weiBo.servlet;
import team.redrock.weiBo.dao.UpdateU_introDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
@WebServlet(name = "UpdateU_intro",value = "/updateU_intro")

//更新 个性签名的接口
public class UpdateU_intro extends HttpServlet {
    //使用两个静态变量来节省开销
    //修改用户昵称成功
    private static final String SUCCESS = "{\"status\":\"10000\"}";

    private static final String ERROR = "{\"status\":\"10001\"}";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String userID = String.valueOf(session.getAttribute("userID"));
        String newU_intro = request.getParameter("newIntro");
        UpdateU_introDao updateU_introDao = new UpdateU_introDao();
        String result = ERROR;

        if (updateU_introDao.updateU_intro(userID,newU_intro)){
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
