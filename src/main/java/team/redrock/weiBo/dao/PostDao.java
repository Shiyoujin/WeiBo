package team.redrock.weiBo.dao;

import java.sql.*;


public class PostDao {

    //status 200
    //发表微博   p_id,content,userID,u_name,p_times
    //这里的p_id一定为 "0"
    public boolean sendPost(String p_id,String content,String userID,String u_name,String p_times,String g_number,String c_number,String o_opic) throws SQLException {
        Connection connection = new JDBC().getConnection();
        PreparedStatement pstmt =null;
        String sql = "insert into post(p_id,content,userID,u_name,p_times,g_number,c_number,o_opic) value(?,?,?,?,?,?,?,?)";
        int result;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,p_id);
            pstmt.setString(2,content);
            pstmt.setString(3,userID);
            pstmt.setString(4,u_name);
            pstmt.setString(5,p_times);
            pstmt.setString(6,g_number);
            pstmt.setString(7,c_number);
            pstmt.setString(8,o_opic);
            result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            pstmt.close();
            connection.close();
        }
    }



    //status 200
    //发送微博时， 查出 微博id评论的条数  返回前端
    public String findCommentNumber(String p_id){
        String sql = "SELECT id FROM post WHERE p_id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        int postNumber =0;
        ResultSet res = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,p_id);
            res = pstmt.executeQuery();
            while (res.next()){
                postNumber +=1;
            }
            return String.valueOf(postNumber);
        } catch (SQLException e ){
            e.printStackTrace();
            return "查询失败";
        }
    }


    //status 200
    //查询出用户发送微博的 条数
    public String findPostNumber(String userID,String pid){
        String sql = "SELECT p_id FROM post WHERE p_id = ? AND userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        int postNumber =0;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,pid);
            pstmt.setString(2,userID);
            res = pstmt.executeQuery();
            while (res.next()){
                postNumber = postNumber +1;
            }
            return String.valueOf(postNumber);
        } catch (SQLException e){
            e.printStackTrace();
            return "返回用户微博条数失败";
        }
    }

    public static void main(String[] args) throws SQLException {
        PostDao postDao =  new PostDao();
        postDao.sendPost("0","前后端交互发微博","6","水母","10:55","0","0","用户头像");


    }


}
