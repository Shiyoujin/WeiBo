package team.redrock.weiBo.servlet;
import team.redrock.weiBo.dao.UpdateuU_nameDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet(name = "UpdateU_name",value = "/updateU_name")

//更新个人昵称的接口，包含更新 post、mutuality、collection、user四张表里面的 u_name
public class UpdateU_name extends HttpServlet {
    //这里使用 两个静态变量来节省开销
    //修改用户昵称成功
    private static final String SUCCESS ="{\"status\":\"10000\"}";

    private static final String ERROR = "{\"status\":\"10001\")";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String userID =String.valueOf(session.getAttribute("userID"));
        String newU_name =request.getParameter("newName");
//        String u_intro =request.getParameter("u_intro");
        UpdateuU_nameDao updateuU_nameDao = new UpdateuU_nameDao();
        String result = ERROR;

        if (updateuU_nameDao.updateU_name(newU_name,userID))
        {
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
