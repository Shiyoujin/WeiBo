package team.redrock.weiBo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class U_pic {

    //status 200
    //根据 userID查询到 用户的头像
    public String findU_pic(String userID){
        String sql = "SELECT u.u_pic FROM user u WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res =null;
        String u_pic= null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
            if (res.next()){
                u_pic = res.getString("u_pic");
                return u_pic;
            }else {
                return "查询用户头像失败";
            }
        }catch (SQLException e){
            e.printStackTrace();
            return "查询用户头像出现异常";
        }
    }

    public static void main(String[] args) {
        U_pic u_pic = new U_pic();
       String a = u_pic.findU_pic("1");
        System.out.println(a);
    }
}
