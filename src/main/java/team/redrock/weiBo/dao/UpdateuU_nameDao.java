package team.redrock.weiBo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateuU_nameDao {
    //status 200
    //通过用户ID，更改 user表，post表，collection表，mutuality表 u_name，
    //多条SQL 语句，必需要有 pstmt.executeUpdate(); 这种，代表 pstmt执行了
    //并且要有多个 pstmt 类型
    public boolean updateU_name(String newU_name,String userID){
        //此种写法错误，会全部更改
//        String sqlU = "UPDATE user u,post p,collection c,mutuality m set u.u_name = ?,p.u_name = ?,c.u_name = ?,m.u_name = ? WHERE u.userID = ? OR p.u_name =? OR c.u_name = ? OR m.u_name = ?";4
        String sqlU = "UPDATE user SET u_name =? WHERE userID = ?";
        String sqlP = "UPDATE post SET u_name = ? WHERE userID = ?";
        String sqlC = "UPDATE collection SET u_name = ? WHERE userID = ?";
        String sqlM = "UPDATE mutuality SET u_name = ? WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmtU= null;
        PreparedStatement pstmtP =null;
        PreparedStatement pstmtC = null;
        PreparedStatement pstmtM = null;
        int result = 0;
        try {
            pstmtU = connection.prepareStatement(sqlU);
            pstmtU.setString(1,newU_name);
            pstmtU.setString(2,userID);
            pstmtU.executeUpdate();
            pstmtP = connection.prepareStatement(sqlP);
            pstmtP.setString(1,newU_name);
            pstmtP.setString(2,userID);
            pstmtP.executeUpdate();
            pstmtC = connection.prepareStatement(sqlC);
            pstmtC.setString(1,newU_name);
            pstmtC.setString(2,userID);
            pstmtC.executeUpdate();
            pstmtM = connection.prepareStatement(sqlM);
            pstmtM.setString(1,newU_name);
            pstmtM.setString(2,userID);
            result = pstmtM.executeUpdate();
            return result>0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        UpdateuU_nameDao updateuU_nameDao =new UpdateuU_nameDao();

        if (updateuU_nameDao.updateU_name("小孩子","1"))
        {
            System.out.println("chengg");
        }else {
            System.out.println("失败");
        }
    }




}
