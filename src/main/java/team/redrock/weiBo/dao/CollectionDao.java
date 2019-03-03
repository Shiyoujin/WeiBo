package team.redrock.weiBo.dao;
import team.redrock.weiBo.been.Collection;
import java.sql.*;
import java.util.*;


public class CollectionDao {

    //status 200
    //向 Collection表中添加 收藏微博p_id
    public boolean insertCollection(String userID, String u_name, String p_id) {
        String sql = "INSERT INTO collection(userID,u_name,p_id) VALUE(?,?,?)";
        Connection connection = new JDBC().getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, u_name);
            pstmt.setString(3, p_id);
            int restult = pstmt.executeUpdate();
            if (restult > 0) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                pstmt.close();  //前面 pstmt必需有 null 才可以 pstmt.close
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    //status 200
    //向Collection表中 删除p_id，取消收藏
    public boolean deleteCollection(String userID, String p_id) {
        String sql = "DELETE FROM collection WHERE userID = ? AND p_id =?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, p_id);
            int result = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            if (result != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //status 200
    //先通过userID查询出所有的 p_id,返回 list（p_id）
    public List findUerCollectionP_id(String userID) {
        String sql = "SELECT c.* FROM collection c WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmst = null;
        ResultSet res = null;
        List list = new ArrayList();
        try {
            pstmst = connection.prepareStatement(sql);
            pstmst.setString(1, userID);
            res = pstmst.executeQuery();
            while (res.next()) {
                list.add(res.getString("p_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new JDBC().close(res, pstmst, connection);
        }
        return list;
    }

    //status 200
    //通过每一个 p_id查询出 该微博的内容及其微博昵称（后面添加时间、图片、头像）
    public List<Collection> findCollectionWeibo(List p_idList) {
        List<Collection> list = new ArrayList<>();
        for (int i = 0; i < p_idList.size(); i++) {
            String sql = "SELECT p.* FROM post p WHERE id = ?";
            Connection connection = JDBC.getConnection();
            PreparedStatement pstmt = null;
            ResultSet res = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, String.valueOf(p_idList.get(i)));
                res = pstmt.executeQuery();
                if (res.next()) {
                    Collection collection = new Collection();   //必需是这里创建对象
                    collection.setO_opic(res.getString("o_opic"));  //用户头像
                    collection.setO_onick(res.getString("u_name")); //用户昵称
                    collection.setP_content(res.getString("content")); //微博内容
                    collection.setId(res.getInt("id"));              //微博id
                    collection.setP_times(res.getString("p_times")); //微博发送时间
                    collection.setP_image(res.getString("p_image")); //微博发送图片
                    collection.setC_number(res.getString("c_number")); //评论数
                    collection.setG_number(res.getString("g_number")); //点赞数
                    list.add(collection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                new JDBC().close(res, pstmt, connection);
            }
        }
        return list;
    }


    //status 200
    //前台传入 微博的 id 即 follow_id，判断 collection表中 用户是否收藏过
    public boolean isCollectionExist(String userID,String follow_id){
        String sql = "SELECT c.* FROM collection c WHERE userID = ? AND p_id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            pstmt.setString(2,follow_id);
            res = pstmt.executeQuery();
            if (res.next()){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //status 200
    //查询出 用户收藏条数
    public String findCollectionNumber(String userID){
        String sql = "SELECT c.* FROM collection c WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        int collectionNumber = 0;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
            while (res.next()){
                collectionNumber += 1;
            }
            return String.valueOf(collectionNumber);
        } catch (SQLException e){
            e.printStackTrace();
            return "查询收藏条数失败";
        }
    }


    //测试
    public static void main(String[] args) {
        CollectionDao collectionDao = new CollectionDao();
//        if (collectionDao.deleteCollection("1","3"))
//        {
//            System.out.println("成功");
//        } else {
//            System.out.println("没有");
        System.out.println(collectionDao.findCollectionNumber("1"));
        }
}



