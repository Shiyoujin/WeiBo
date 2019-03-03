package team.redrock.weiBo.dao;
import com.sun.org.apache.regexp.internal.RE;
import sun.dc.pr.PRError;
import team.redrock.weiBo.been.SearchObjcet;
import team.redrock.weiBo.service.impl.SearchService;
import team.redrock.weiBo.servlet.Search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchDao {

    //status 200
    // 分别在 用户表和微博表 模糊查询 用户昵称 和 微博内容
    //多表联查，根据 搜索 关键字 搜索出 微博和用户的信息，返回 list  -------------此思路不合理，会出现错误查询信息
    public HashMap<String, List<SearchObjcet>> searchWeiboUser(String content) {

//        String sql = "SELECT u.u_name AS uname,p.u_name AS o_onick,p.*,u.* FROM user u, post p WHERE p.content like ? OR u.u_name like ?";--------多表联查，此思路已 废除

        String sqlU = "SELECT * FROM user WHERE u_name like ?";

        String sqlP = "SELECT * FROM post WHERE content like ? AND p_id = 0 ORDER BY p_times DESC ";  //若是 搜索的微博，根据 发送微博的时间 来排序(DESC 降序，ASC 升序)

        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resU = null;
        ResultSet resP = null;

        HashMap<String, List<SearchObjcet>> hashMap = new HashMap<>();

        List<SearchObjcet> listU = new ArrayList();
        List<SearchObjcet> listP = new ArrayList();

        try {
            pstmt = connection.prepareStatement(sqlU);
            pstmt.setString(1, "%" + content + "%");

            resU = pstmt.executeQuery();

            while (resU.next()) {

                SearchObjcet user = new SearchObjcet();

                user.setU_name(resU.getString("u_name"));     //用户昵称
                user.setU_sex(resU.getString("u_sex"));       //用户性别
                user.setU_intro(resU.getString("u_intro"));   //用户个人简介
                user.setUserID(resU.getString("userID"));     //用户ID，用户添加关注

                listU.add(user);
            }
            resU.close();
            hashMap.put("user", listU);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pstmt = connection.prepareStatement(sqlP);
            pstmt.setString(1, "%" + content + "%");

            resP = pstmt.executeQuery();

            while (resP.next()) {

                SearchObjcet post = new SearchObjcet();

                post.setO_onick(resP.getString("u_name"));   //微博昵称
                post.setP_content(resP.getString("content"));//微博内容
                post.setP_times(resP.getString("p_times"));  //微博时间
                post.setId(resP.getInt("id"));               //微博的id,用户添加收藏
                listP.add(post);
            }
            hashMap.put("weibo", listP);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            new JDBC().close(resP, pstmt, connection);
        }
        return hashMap;
    }

    //status 200
    //每使用一次搜索功能，触发此 方法
    //微博热搜，若 数据库没有此关键词，则 search 表中添加 字段，并且 number默认为1
    //         若  数据库已经存在关键词， 则取出 number， 数值 +1 再 update回去
    public boolean insertSearch(String content) {
        String sqlA = "SELECT * FROM search WHERE content = ?";
        String sql = "INSERT INTO search(content,number) VALUES (?,?)";
        String sqlU = "UPDATE search SET number = ? WHERE content = ?";
        String sqlN = "SELECT number FROM search WHERE content = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        ResultSet resA = null;
        int result = 0;
        int initial = 1;
        int string = 0;
        PreparedStatement pstmtN = null;

        try {
            pstmt = connection.prepareStatement(sqlA);  //先查询 search热搜表里 是否存在 此条 contentt 记录
            pstmt.setString(1, content);
            res = pstmt.executeQuery();
            if (!res.next()){    //如果没有此条 content 的记录
                pstmt = connection.prepareStatement(sql);  //则 向 search表插入新的一条 content记录，且 number 为1
                pstmt.setString(1,content);
                pstmt.setString(2, String.valueOf(initial));
                result = pstmt.executeUpdate();
                res.close();
                pstmt.close();
                return result > 0;
            } else {            //如果之前 热搜表 search表里 有此条 content 记录
                pstmt = connection.prepareStatement(sqlN);  //则先取出 其content 对应的 number 字符串
                pstmt.setString(1,content);
                resA = pstmt.executeQuery();
                if (resA.next())                           //如果字符串存在的话
                string = Integer.parseInt(resA.getString("number"));  //将字符串转化为 int 类型
                string +=1;                                                        //将之前的number + 1
                pstmt.close();
                resA.close();
                pstmt = connection.prepareStatement(sqlU);
                pstmt.setString(1,String.valueOf(string));         //将search表里 number字符串
                pstmt.setString(2,content);                        //更新为 +1新的 number
                result = pstmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        //status 200
        //搜索出 search 表中 所有 热搜关键词 及其对应的 热搜次数，用于 热搜的 json组装
        public List<SearchObjcet> findSearchAll () {
            String sql = "SELECT * FROM search ORDER BY number+0 DESC ";          //这里的 排序需要 字段+0  和之前微博的时间排序不一样
            Connection connection = JDBC.getConnection();                            //也就说 时间排序和数字排序是有区别的
            PreparedStatement pstmt = null;
            List<SearchObjcet> list = new ArrayList<>();
            ResultSet res = null;
            try {
                pstmt = connection.prepareStatement(sql);
                res = pstmt.executeQuery();
                while (res.next()){
                    SearchObjcet searchObjcet = new SearchObjcet();
                    searchObjcet.setContent(res.getString("content"));
                    searchObjcet.setNumber(res.getString("number"));
                    list.add(searchObjcet);
                }
                return list;
            } catch (SQLException e){
                e.printStackTrace();
                return list;
            } finally {
                new JDBC().close(res,pstmt,connection);
            }
        }

    public static void main(String[] args) {
        SearchDao searchDao = new SearchDao();
//        HashMap<String,List<SearchObjcet>> hashMap  =searchDao.searchWeiboUser("游琎");
//        String json = new SearchService().createSearchJson(hashMap);
//        System.out.println(json);

//            if (searchDao.insertSearch("章鱼哥")) {
//                System.out.println("修改成功");
//            }
//
            List<SearchObjcet> list = searchDao.findSearchAll();
        System.out.println(list);
        }

}
