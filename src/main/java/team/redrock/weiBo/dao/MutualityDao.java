package team.redrock.weiBo.dao;

import team.redrock.weiBo.been.Care;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//focus_id 为 String follow_id 也为 String
public class MutualityDao {
    //status 200
    //登录用户ID，关注人的ID，向userID 添加关注者，向follow_id添加粉丝，返回 boolean
    public boolean newFollow(String userID,String follow_id) throws SQLException {
        String sqlU = "UPDATE mutuality SET focus_id = CONCAT(focus_id,?) WHERE userID = ?";
        String sqlF = "UPDATE mutuality SET follower_id = CONCAT(follower_id,?) WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        int resultU;
        int resultF;
        try{
            pstmt = connection.prepareStatement(sqlU);
            pstmt.setString(1,follow_id + ";");
            pstmt.setString(2,userID);
            resultU = pstmt.executeUpdate();
            if (resultU == 1) {
                pstmt = connection.prepareStatement(sqlF);
                pstmt.setString(1, userID + ";");
                pstmt.setString(2, follow_id);
                resultF = pstmt.executeUpdate();
                if (resultF == 1) {
                    return true;
                }else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
            pstmt.close();
        }
    }

    //status 200
    //得到该用户  粉丝数和关注人数, 传入 userID，得到 List 里面有两个String元素，分别对应 focus_id,follower_id 数值为数字
    public List<String> numberFollow(String userID){
        String sql = "SELECT focus_id,follower_id FROM mutuality WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res =null;
        List<String> list = new ArrayList();
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
            while (res.next()) {
                String focus_id = res.getString("focus_id");  // 1;2;3;4;5;
                String follower_id = res.getString("follower_id");
                String resultFocus_id[] = focus_id.split(";");
                String resultFollower[] = follower_id.split(";");
                if ("".equals(resultFocus_id[0])){   //这里的处理最后还是选择了 注册增加关注，海绵宝宝，粉丝 自己
                                                     //判断关注和粉丝id是否为空,为空，默认 为 0，防止出现BUG
                    list.add("0");                   //也可以 注册时 加入 空字符串 ""
                }else {
                    list.add(String.valueOf(resultFocus_id.length));
                }

                if ("".equals(resultFollower[0])){
                    list.add("0");
                } else {
                    list.add(String.valueOf(resultFollower.length));
                }
            }
            pstmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            List<String> falseList = new ArrayList();
            falseList.add("获取用户关注人数失败");
            return falseList;
        }
    }

    //status 200
    //传入 登录用户ID，关注人的ID，进行删除 相应  两者的userID字段，返回 boolean
    //取消关注
    public Boolean deleteFollow(String userID,String follow_id){
        String sql = "SELECT focus_id FROM mutuality WHERE userID= ?";
        String sqlC= "SELECT follower_id FROM mutuality WHERE userID= ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmtFocus = null;
        PreparedStatement pstmtFans = null;
        ResultSet resA = null;
        ResultSet reB = null;

        String focus_id = null;
        String follower_id = null;
        StringBuffer focus_idSb = new StringBuffer();
        StringBuffer follower_idSb =new StringBuffer();

        try {
            pstmtFocus =connection.prepareStatement(sql);
            pstmtFocus.setString(1,userID);
            resA = pstmtFocus.executeQuery();
            pstmtFans =connection.prepareStatement(sqlC);
            pstmtFans.setString(1,follow_id);
            reB = pstmtFans.executeQuery();
            if (resA.next())
            {
                focus_id = resA.getString("focus_id");    //1;2;3;4;
            }
            if (reB.next()){
                follower_id = reB.getString("follower_id");
            }

            String resultFocus_id[] = focus_id.split(";");      //1 2 3 4  .lenth = 4, 但最大索引值为3
            String resultFollower_id[] = follower_id.split(";");
            List<String> Focus_idList = Arrays.asList(resultFocus_id);
            List<String> Follower_idList = Arrays.asList(resultFollower_id);

            if (Focus_idList.contains(follow_id)&&Follower_idList.contains(userID))
            {
                List<String> lisaA = new ArrayList<String>(Focus_idList);  //将之前的list转化为,Arraylist调用相关的remove方法
                List<String> listB = new ArrayList<>(Follower_idList);
                lisaA.remove(follow_id);                            //这里的follow_id 是int，但是要求String
                listB.remove(userID);
                for (String string :lisaA){
                    focus_idSb.append(string+";");                       //获得删除4，之后的 StringBuffer 1;2;3;
                }
                for (String string : listB){
                    follower_idSb.append(string+";");
                }
            } else {    //如果不包含的话，那就不改动，这里有个 BUG 如果 关注或者粉丝为 0的话，那么会 新增加一个分号，但实际中 并不会出现这种情况！
                List<String> lisaA = new ArrayList<String>(Focus_idList); //将之前的list转化为,Arraylist调用相关的remove方法
                List<String> listB = new ArrayList<>(Follower_idList);
                for (String string :lisaA){
                    focus_idSb.append(string+";");                       //获得删除4，之后的 StringBuffer 1;2;3;
                }
                for (String string : listB){
                    follower_idSb.append(string+";");
                }

            }

            String newSqlA = "UPDATE mutuality set focus_id = ? WHERE userID =?";
            String newSqlB = "UPDATE mutuality set follower_id = ? WHERE userID = ?";
            PreparedStatement pstmtA =null;
            PreparedStatement pstmtB =null;
            int result;
            pstmtA =connection.prepareStatement(newSqlA);
            pstmtA.setString(1, String.valueOf(focus_idSb));
            pstmtA.setString(2, userID);
            pstmtA.executeUpdate();
            pstmtB = connection.prepareStatement(newSqlB);
            pstmtB.setString(1, String.valueOf(follower_idSb));
            pstmtB.setString(2,follow_id);
            result = pstmtB.executeUpdate();
            if (result!=0){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            new JDBC().close(resA,pstmtFocus,connection);
            new JDBC().close(reB,pstmtFans,connection);
        }
    }


    //status 200
    //根据 用户ID  返回 list 包含各个关注的人 ID
    public List<String> findCareId(String userID){
        String sql = "SELECT focus_id FROM mutuality WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt;
        ResultSet res = null;
        List<String> list = new ArrayList();
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
           while (res.next()) {
               StringBuffer sb = new StringBuffer();
               String careAllId = res.getString("focus_id");
               String care[] = careAllId.split(";");  //将字符串拆分成数组
               list = Arrays.asList(care);  //将数组转化成 list
           }
            pstmt.close();
            connection.close();
            return list;   //获得 关注的人 的各个 用户id

        } catch (SQLException e){
            e.printStackTrace();
            return list;   //捕捉错误，list为空
        }
    }

    //status 200
    //根据 每一个 careId 和p_id =0 查询出 关注人的微博 昵称 内容 时间
    public List<Care> findCareWeibo(List<String> list,String p_id) {
        List<Care> listCare = new ArrayList();

        for (String string : list) {

            String sql = "SELECT p.* FROM post p WHERE userID = ? AND p_id = ? ORDER BY p_times ASC ";
            Connection connection = JDBC.getConnection();
            PreparedStatement pstmt;
            ResultSet res =null;

            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,string);
                pstmt.setString(2,p_id);
                res = pstmt.executeQuery();
                while (res.next()){
                    Care care = new Care();
                    care.setL_name(res.getString("u_name"));
                    care.setUserID(res.getString("userID"));
                    care.setL_id(res.getInt("id"));
                    care.setL_content(res.getString("content"));
                    care.setL_times(res.getString("p_times"));
                    care.setG_number(res.getString("g_number"));
                    care.setC_number(res.getString("c_number"));
                    care.setL_pic(res.getString("o_opic"));
                    care.setL_image(res.getString("p_image"));
                    listCare.add(care);
                }
                pstmt.close();
                connection.close();
                res.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return listCare;
    }


    //status 200
    //点击关注人数，触发的 交互，展示关注的人 的 昵称，性别，个签，头像,关注和粉丝人数
    public List<Care> findCareMessage(List<String> list){
        List<Care> listCare = new ArrayList<>();

        for (String string : list){

            String sql = "SELECT u.* FROM user u WHERE userID = ? ";
            Connection connection = JDBC.getConnection();
            PreparedStatement pstmt = null;
            ResultSet res = null;

            List<String> list1 = numberFollow(string); //通过 userID，找到的关注，粉丝数集合


            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,string);
                res = pstmt.executeQuery();
                if (res.next()){
                    Care care = new Care();
                    care.setUserID(res.getString("userID"));
                    care.setL_name(res.getString("u_name"));
                    care.setL_sex(res.getString("u_sex"));
                    care.setL_intro(res.getString("u_intro"));
                    care.setL_pic(res.getString("u_pic"));
                    care.setL_careNumber(list1.get(0));
                    care.setL_fansNumber(list1.get(1));
                    listCare.add(care);
                }
                pstmt.close();
                connection.close();
                res.close();

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return listCare;
    }

    //status 200
    //根据 userID和关注人ID 判断该用户之前 是否关注
    public boolean isCare(String userID,String focus_id){
        String sql = "SELECT focus_id FROM mutuality WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String string = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userID);
            res = pstmt.executeQuery();
            if (res.next()){
                string = res.getString("focus_id");
            }
            String str[] = string.split(";");
            List<String> list = Arrays.asList(str);

            if (list.contains(String.valueOf(focus_id))){
                return false;
            }else {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        MutualityDao mutualityDao =new MutualityDao();
        //测试 取消关注功能
//        if (mutualityDao.deleteFollow("2","1")) {
//            System.out.println("取消关注成功");
//        }else {
//            System.out.println("未执行");
//        }

    }

}
