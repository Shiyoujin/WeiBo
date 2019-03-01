package team.redrock.weiBo.dao;
import java.sql.*;

public class JDBC {

    //设置通用的 连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            //这里和之前有点区别，手写的话需要转化，从之前的话直接复制
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //注册时 检查userID和u_name是否存在。
    public boolean checkRegiser(String userID,String u_name) throws SQLException {
        ResultSet resultSet;
        Connection conn = JDBC.getConnection();
        String sql = "select userID,u_name from user where userID = ? OR u_name = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2,u_name);
            resultSet = pstmt.executeQuery();
            if (resultSet.next())
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //检验账户名不存在后，进行注册插入数据库.user表添加个人信息，mutuality 关注粉丝表 新增加 userID和u_name
    public boolean regiserUser(String user, String pwd, String u_sex, String u_name,String u_pic) {

        Connection conn = JDBC.getConnection();
        String sqlU = "insert into user (userID,password,u_sex,u_name,u_pic) values(?,?,?,?,?)";
        String sqlC = "INSERT INTO mutuality (userID,u_name,focus_id,follower_id) VALUES (?,?,?,?)";
        PreparedStatement pstmt = null;
        String focus_id = "3";         //注册时，默认关注海绵宝宝，丰富 注册内容
        String follower_id = "1";
        try {
            pstmt = conn.prepareStatement(sqlU);
            pstmt.setString(1, user);
            pstmt.setString(2, pwd);
            pstmt.setString(3, u_sex);
            pstmt.setString(4, u_name);
            pstmt.setString(5,u_pic);
            int count = pstmt.executeUpdate();
            if (count > 0)
            {
                pstmt = conn.prepareStatement(sqlC);
                pstmt.setString(1,user);
                pstmt.setString(2,u_name);
                pstmt.setString(3,focus_id);
                pstmt.setString(4,follower_id);
                int collection = pstmt.executeUpdate();
                if (collection >0){
                    return true;
                }else {
                    return false;
                }
             } return false;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
    }

    //一个 close()  方法关闭 res，pstmt，connection
    public void close(ResultSet res, PreparedStatement pstmt, Connection con) {
        try {
            res.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean A(int a) {
        if (a == 1)
            return true;
        else
            return false;
    }

    //status 200
    //根据 session中的 userID查询 用户个人简介
    public String findUserIntro(String userID){
        String sql = "SELECT u.u_intro FROM user u WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String intro = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
            if (res.next())
            {
                intro = res.getString("u_intro");
                return intro;
            } else {
                return "查询用户个人简介失败";
            }
        } catch (SQLException e ){
            e.printStackTrace();
            return "查询用户个人简介异常";
        }
    }

    //status 200
    //通过 userID查询 u_name
    public String findU_name(String userID){
        String sql = "SELECT u_name FROM user WHERE userID = ?";
        Connection con = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String u_name = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
            if (res.next()) {
                u_name = res.getString("u_name");
            }
            return u_name;
        }catch (SQLException e){
            e.printStackTrace();
            return "通过 userID查询 u_name 异常";
        }
    }

    //测试
    public static void main(String[] args) throws SQLException {
        JDBC jdbc = new JDBC();
        String a = jdbc.findU_name("1");
        System.out.println(a);
//        String a = jdbc.findUserIntro("1");
//        System.out.println(a);
//        jdbc.regiserUser()

//        UpdateU_introDao updateU_introDao = new UpdateU_introDao();
//        updateU_introDao.updateU_intro("123", "这个人");


    }
}
//        Date now = new Date();
//        System.out.println(now);//Tue Feb 19 09:33:52 CST 2019
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now));  //2019-02-19 09:33:52
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd ").format(now));          //2019-02-19




