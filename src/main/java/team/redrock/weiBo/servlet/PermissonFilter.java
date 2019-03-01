package team.redrock.weiBo.servlet;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//----------------------------------------验证登录状态的Filter--------------------------------------------
//过滤器
public class PermissonFilter implements Filter {
    public FilterConfig filterConfig;
    //配置中的 passUrl
    String passUrl = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        //首先在init方法中读取我们设置的passUrl，保存到变量中。如果当前请求的URL包含在了passURL中，则不对其进行登录校验。
        passUrl = filterConfig.getInitParameter("passUrl");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将参数中的 ServletRequest和ServletResponse 强转为 Http。。。
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //对配置中初始参数进行 逻辑不过滤，方法使用 详情百度
        String []strArray = passUrl.split(";");
        for (String str : strArray) {
            if (str.equals(""))
                continue;
            if (req.getRequestURL().indexOf(str) >= 0) {  //HttpServletRequest的方法
                chain.doFilter(request, response);
                return;
            }
        }

        //获得访问界面的url文件地址
        String servletPath = req.getServletPath();
        //获取session对象
        HttpSession session = req.getSession();
        //获得session对象中的 flag的值，即此时的登录状态，强转为String类型
        String loginflag =(String) session.getAttribute("loginflag");
        /*判断是否是 登录页、首页、登录servlet*/
        if (servletPath !=null &&(servletPath.equals("signIn1.0/signIn.html"))||servletPath.equals("homePage/homePage.html")||servletPath.equals("/login")){
            /*是则直接转发到下一组件
            这里页可以看出来 doFilter参数 非 Http且后面需要强转
             */
            chain.doFilter(request,response);
        } else {
            //否，则验证登录状态
            //判断是否是登录页、首页、登录servlet
            if (loginflag != null) {
                if (loginflag.equals("login_success")){
                    //登录成功，直接转发到下一组件
                    chain.doFilter(request,response);
                } else if (loginflag !=null&&loginflag.equals("login_error")){
                    //登录失败，跳转到登录页，并曹村当前页面的url文件路径
                    req.setAttribute("msg","登录失败");
                    req.setAttribute("return_uri",servletPath);
                    //这里是用的 HttpservletRequest
                    RequestDispatcher rd = req.getRequestDispatcher("signIn1.0/signIn.html");
                    rd.forward(req,res);
                }
                //没有登录则在跳转到 登录界面，并提示 “您尚未登录”
            } else {
                //未登录，跳转到登录页，并保存当前页面的url文件路径
                req.setAttribute("msg","您当前未登录，请登录");
                req.setAttribute("return_uri",servletPath);
                RequestDispatcher rd = req.getRequestDispatcher("signIn1.0/signIn.html");
                rd.forward(req,res);
            }
        }
    }

    public static void main(String[] args) {

    }

    @Override
    public void destroy() {

    }
}
