package team.redrock.weiBo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendChildDao {


    //status 200
    //发送 评论和回复
    public boolean sendChild(String userID,String p_id,String times,String u_name,String content,String u_pic,String g_number,String c_number ){
        String sql = "INSERT INTO post(userID,p_id,p_times,u_name,content,o_opic,g_number,c_number) VALUE(?,?,?,?,?,?,?,?)";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        int result;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            pstmt.setString(2,p_id);
            pstmt.setString(3,times);
            pstmt.setString(4,u_name);
            pstmt.setString(5,content);
            pstmt.setString(6,u_pic);
            pstmt.setString(7,g_number);
            pstmt.setString(8,c_number);
            result = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            if (result > 0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    //status 200
    //发送 评论后，查询新的 微博评论数
    public String newCommentNumber(int id){
        String sql = "SELECT * FROM post WHERE p_id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            int newCommentNumber = 0;    //不能放在 try上面 同点赞
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);
            res = pstmt.executeQuery();
            while (res.next()){
                newCommentNumber +=1;
            }
            return String.valueOf(newCommentNumber);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            new JDBC().close(res,pstmt,connection);
        }
        return "查询失败";
    }

    //status 200
    //发表评论后，更新评论数，类似 点赞功能
    public void updateCommentNumber(String newNumber,int id){
        String sql = "UPDATE post SET c_number = ? WHERE id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,newNumber);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendChildDao sendChildDao = new SendChildDao();
        String a = sendChildDao.newCommentNumber(46);
        System.out.println(a);
//        sendChildDao.updateCommentNumber("3",2);
    }
}
