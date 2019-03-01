package team.redrock.weiBo.dao;

import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GreatDao {

    //status 200
    //先判断 userID和 id是否同时存在，不存在再进行插入， 得到点赞效果
    //great表插入 数据后，根据 记录 可以查询出 点赞状态
    public boolean insertGreat(int id, String userID) {
        String sqlI = "INSERT INTO great(id,userID) VALUE(?,?)";
        String sqlS = "SELECT * FROM great WHERE userID =? AND id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;     //这里只有一个 pstmt
        ResultSet res = null;
        try {
            pstmt = connection.prepareStatement(sqlS);
            pstmt.setString(1, userID);
            pstmt.setInt(2, id);
            res = pstmt.executeQuery();
            if (res.next()) {
                return false;
            } else {
                pstmt = connection.prepareStatement(sqlI);
                pstmt.setInt(1, id);
                pstmt.setString(2, userID);
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            new JDBC().close(res, pstmt, connection);

        }
    }

    //status 200
    //根据 post表特定 id，得到 这条 id的点赞数
    public String greatNumber(int id) {
        String sql = "SELECT * FROM great WHERE id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            int postNumber = 0;
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            res = pstmt.executeQuery();
            while (res.next()) {
                postNumber += 1;
            }
            return String.valueOf(postNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return "查询点赞数目失败";
        }
    }

    //status 200
    //根据 微博id和 点赞后获得新的 数值 更新 post g_number字段  点赞数，点赞状态
    public boolean updateG_number(String newG_number, int id) {
        String sql = "UPDATE post SET g_number = ? WHERE id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, newG_number);
            pstmt.setInt(2, id);
            int result = pstmt.executeUpdate();
            if (result > 0) {
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
    //根据微博（评论、回复） id，userID(登录用户ID) 来取消点赞
    public boolean deleteGreat(int id, String userID) throws SQLException {
        String sql = "DELETE FROM great WHERE id = ? AND userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, userID);
            int resutt = pstmt.executeUpdate();
            if (resutt != 0) {                              //如果执行了 取消点赞 则返回 true
                return true;                              //否则返回 false
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            pstmt.close();
            connection.close();
        }
    }


    //status 200
    //判断 用户之前对该微博是否点赞（详情页里面 显示 点赞图标）
    public String isGreat(int id, String userID) {
        String sql = "SELECT g.* FROM great g WHERE id = ? AND userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String isgreat = "0";    // 0 代表该用户没有对该条微博进行点赞，1 代表 已经点过赞

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, userID);
            res = pstmt.executeQuery();
            if (res.next()) {
                isgreat = "1";
            }

            pstmt.close();
            res.close();
            connection.close();
            return isgreat;
        } catch (SQLException e) {
            e.printStackTrace();
            return "查询点赞状态失败";
        }
    }
}

    //测试
//    public static void main(String[] args) throws SQLException {
//        GreatDao greatDao = new GreatDao();
////        System.out.println(greatDao.isGreat(3,"6"));
//        String result = "great";
//        if (result == "great"){
//            if (greatDao.insertGreat(1,"47")){
//                String a = greatDao.greatNumber(1);
//                greatDao.updateG_number(a,1);
//            }
//
//        }




//        if (greatDao.deleteGreat(3,"2")){
//            System.out.println("取消赞成功！");
//        } else {
//            System.out.println("取消点赞失败！");
//        }



//        String result = greatDao.postNumber(10);
//        System.out.println(result);


//        if (greatDao.insertGreat(3,"1")){
//            System.out.println("点赞成功");
//            String newG_number =  greatDao.greatNumber(1);
//            greatDao.updateG_number(newG_number,1);
//        } else {
//            System.out.println("点赞失败");
//        }
//    }
//}
