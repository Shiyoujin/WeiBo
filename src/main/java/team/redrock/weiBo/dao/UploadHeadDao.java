package team.redrock.weiBo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadHeadDao {

    //status 200
    //根据 userID 要存储的 url， update  user表  u_pic字段
    public boolean uploadHead(String userID,String url){
        String sql = "UPDATE user SET u_pic = ? WHERE userID = ?";
        String sqlP = "UPDATE post SET o_opic = ? WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        int resultP = 0;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,url);
            pstmt.setString(2,userID);
            result = pstmt.executeUpdate();
            pstmt.close();
            if (result > 0){
                pstmt = connection.prepareStatement(sqlP);
                pstmt.setString(1,url);
                pstmt.setString(2,userID);
                resultP = pstmt.executeUpdate();
                if (resultP > 0){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        UploadHeadDao uploadHeadDao = new UploadHeadDao();

        if (uploadHeadDao.uploadHead("1","C:\\Users\\white matter\\IdeaProjects\\WeiBo\\src\\main\\webapp\\UserPic\\1da8362e955db67c3a2b1e09fde69049.jpeg")){
            System.out.println("存储成功");
        } else {
            System.out.println("存储失败");
        }
    }
}
