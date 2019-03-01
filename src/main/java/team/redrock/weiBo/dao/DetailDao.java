package team.redrock.weiBo.dao;

import team.redrock.weiBo.been.Child;
import team.redrock.weiBo.been.Detail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DetailDao {


    //status 200
    //详情页 查询 一条微博的 详情， userID用于 判断 该用户下 此条微博的 点赞状态
    public Detail findDetatilWeibo(int id,String userID){
        String sql = "SELECT p.* FROM post p WHERE id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement newpstmt = null;
        ResultSet res = null;

        GreatDao greatDao = new GreatDao();

        Detail detail = new Detail(); //因为只有 一条记录，所以只需要 创建一个对象，而不要集合

        String isGreat = greatDao.isGreat(id,userID);
        try {
            //查询该条微博 的 详细内容
            newpstmt = connection.prepareStatement(sql);
            newpstmt.setInt(1,id);
            res = newpstmt.executeQuery();
            if (res.next()){

                detail.setId(res.getInt("id"));                      //微博的id
                detail.setU_name(res.getString("u_name"));           //用户昵称
                detail.setUserID(res.getString("userID"));           //用户ID
                detail.setD_times(res.getString("p_times"));         //微博时间
                detail.setD_content(res.getString("content"));       //微博内容
                detail.setIsGreat(isGreat);                                       //点赞状态
                detail.setGreatNumber(res.getString("g_number"));    //点赞数
                detail.setCommentNumber(res.getString("c_number"));  //评论数
                detail.setU_pic(res.getString("o_opic"));            //用户头像
                detail.setD_image(res.getString("p_image"));         //微博图片
            }
            newpstmt.close();
            connection.close();
            return detail;
        } catch (SQLException e){
            e.printStackTrace();
            return detail;
        }
    }

    //status 200
    //通过 微博或评论的 id，查询下面 所有子节点的 信息，然后可以组装json
    public List<Child> findChild(int id){
        String sql = "SELECT * FROM post WHERE p_id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Child> listChild = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);
            res = pstmt.executeQuery();
            while (res.next()){
                Child child = new Child();
                child.setId(res.getInt("id"));
                child.setUserID(res.getString("userID"));
                child.setU_name(res.getString("u_name"));
                child.setChildTimes(res.getString("p_times"));
                child.setChildContent(res.getString("content"));
                child.setU_pic(res.getString("o_opic"));
                child.setP_image(res.getString("p_image"));
                listChild.add(child);
            }
            return listChild;
        }  catch (SQLException e){
            e.printStackTrace();
            return listChild;
        }finally {
            new JDBC().close(res,pstmt,connection);
        }
    }


    public static void main(String[] args) {
        DetailDao detailDao = new DetailDao();
        System.out.println(detailDao.findDetatilWeibo(1,"4"));
        System.out.println(detailDao.findChild(1));
    }

}
