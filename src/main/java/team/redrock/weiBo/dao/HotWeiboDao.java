package team.redrock.weiBo.dao;


import team.redrock.weiBo.been.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotWeiboDao {

    //status 200
    //每点击微博上面的时间进入详情页 触发此 方法
    //微博热门 ，若 数据库没有此关键词，则 hotweibo 表中添加 字段，并且 number默认为1
    //若  数据库已经存在关键词， 则取出 number， 数值 +1 再 update回去
    public boolean insertHotWeibo(int id){
        String sqlH = "SELECT * FROM hotweibo WHERE id = ?";
        String sqlI = "INSERT INTO hotweibo(id,number) VALUE (?,?)";
        String sqlU = "UPDATE hotweibo SET number = ? WHERE id =?";
        String sqlN = "SELECT number FROM hotweibo WHERE id =?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt =null;
        ResultSet res = null;
        ResultSet resA = null;
        int a = 0;
        String initial = "1";
        int b = 0;
        PreparedStatement pstmtN = null;

        try {
            pstmt = connection.prepareStatement(sqlH);
            pstmt.setInt(1,id);
            res = pstmt.executeQuery();
            if (!res.next()){                              //如果之前 没有点击此条微博
                pstmt = connection.prepareStatement(sqlI); //则 hotweibo 热搜热门表里，新插入一条记录，且 number为1
                pstmt.setInt(1,id);
                pstmt.setString(2,initial);
                a = pstmt.executeUpdate();
                res.close();
                pstmt.close();
                return a > 0;
            } else {                                       //如果之前 微博热门表 hotweibo表里 有此条 id记录
                pstmt = connection.prepareStatement(sqlN); //则先取出 id 所对应的 number字符串
                pstmt.setInt(1,id);
                resA = pstmt.executeQuery();
                if (resA.next())
                {
                    b = Integer.parseInt(resA.getString("number")); //将number字段 的内容转化为 int类型
                }
                b +=1;                                                            //int类型如此方便 +1
                pstmt.close();
                resA.close();
                pstmt = connection.prepareStatement(sqlU);
                pstmt.setString(1,String.valueOf(b));  //将 微博热搜表 hotweibo表 number里面的内容
                pstmt.setInt(2,id);                                    //更新为 之前 +1新的 number
                a = pstmt.executeUpdate();
                return a > 0;
            }
        }  catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    //status 200
    //查询 hotweibo表里所有的 微博信息，用于 组装 微博热门的 json
    public List<Post> findHotPostAll() {

        //这里 如果不用 hashmap  其实也可以 联表查询 post和hotweibo 更方便-------------下面试一下 多表联查
        String sql = "SELECT * FROM hotweibo ORDER BY number+0 DESC ";
        String sqlP = "SELECT * FROM post WHERE id = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        List<Post> list = new ArrayList<>();
        List<HashMap<Integer,String>> listId = new ArrayList<>();
        ResultSet res = null;
        try {
            pstmt = connection.prepareStatement(sql);      //现在 微博热搜表 hotweibo 中按 number字段降序
            res = pstmt.executeQuery();                    //查询出 id及其对应的 number， 存进 Hashmap里面
            while (res.next()){
                HashMap<Integer,String> hashMap = new HashMap<>();
                hashMap.put(res.getInt("id"),res.getString("number"));
                listId.add(hashMap);
            }
            res.close();
            pstmt.close();

            for (int i = 0; i<listId.size();i++){        //遍历 list，相应依次取出 hashmap
                pstmt = connection.prepareStatement(sqlP);
                for (Map.Entry<Integer,String> map: listId.get(i).entrySet()){   //遍历hashmap，对其取出 键 和值
                    Post post = new Post();
                    pstmt.setInt(1,map.getKey());
                    res = pstmt.executeQuery();
                    if (res.next()) {
                        post.setP_id(res.getInt("id"));
                        post.setUserID(res.getString("userID"));
                        post.setU_name(res.getString("u_name"));
                        post.setNumber(map.getValue());
                        post.setO_opic(res.getString("o_opic"));
                        post.setP_content(res.getString("content"));
                        post.setP_times(res.getString("p_times"));
                        post.setG_number(res.getString("g_number"));
                        post.setC_number(res.getString("c_number"));
                        post.setP_image(res.getString("p_image"));
                    }
                    list.add(post);
                }
                pstmt.close();
                res.close();
            }
            return list;
        } catch (SQLException e){
            e.printStackTrace();
            return list;
        } finally {
            new JDBC().close(res,pstmt,connection);
        }
    }


    //sql 语句用得好 代码少 百行！！
    //不用 Hashmap 而 用sql 的多表联查 更方便
    public List<Post> testFindHotPostAll(){
        String sql = "SELECT p.*,h.number  FROM post p,hotweibo h WHERE p.id = h.id ORDER BY number+0 DESC";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Post> list = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()){
                Post post = new Post();
                post.setP_id(res.getInt("id"));
                post.setUserID(res.getString("userID"));
                post.setU_name(res.getString("u_name"));
                post.setNumber(res.getString("number"));
                post.setO_opic(res.getString("o_opic"));
                post.setP_content(res.getString("content"));
                post.setP_times(res.getString("p_times"));
                post.setG_number(res.getString("g_number"));
                post.setC_number(res.getString("c_number"));
                post.setP_image(res.getString("p_image"));
                list.add(post);
            }
            return list;
        } catch (SQLException e){
            e.printStackTrace();
            return list;
        }
    }

    public static void main(String[] args) {
        List<Post> list = new HotWeiboDao().testFindHotPostAll();     //测试 更方便的 多表联查用法，不用 Hashmap 来获取 热门微博
        System.out.println(list);
    }
}

