package team.redrock.weiBo.dao;

import team.redrock.weiBo.been.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeiboDao{

    //status 200
    //根据 p_id=0, 用户ID 获得每一条微博的信息，装进list里面
    public List<Post> findUserWeibo(String p_id,String userID) throws SQLException {
        String sql = "SELECT p.* FROM post p WHERE p_id = ? AND userID = ? ORDER BY p_times ASC " ;
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res =null;

        List list = new ArrayList();

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,p_id);
            pstmt.setString(2,userID);
            res = pstmt.executeQuery();
            while (res.next()){
                Post post = new Post();
                post.setU_name(res.getString("u_name"));
                post.setUserID(res.getString("userID"));
                post.setP_id(res.getInt("id"));
                post.setP_content(res.getString("content"));
                post.setP_times(res.getString("p_times"));
                post.setG_number(res.getString("g_number"));
                post.setC_number(res.getString("c_number"));
                post.setO_opic(res.getString("o_opic"));
                post.setP_image(res.getString("p_image"));
                list.add(post);
            }
            pstmt.close();
            connection.close();
            return list;
        } catch (SQLException e ){
            e.printStackTrace();
            return list;   //这里的 list应该为空
        }
    }


    //测试
    public static void main(String[] args) throws SQLException {
        WeiboDao weiboDao = new WeiboDao();
        List<Post> list = weiboDao.findUserWeibo("0","1");
        System.out.println(list.size());
        System.out.println(list.get(1));
        System.out.println(list.get(0));
        System.out.println(list.get(2));
    }


}
