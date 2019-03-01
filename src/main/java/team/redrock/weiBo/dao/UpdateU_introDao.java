package team.redrock.weiBo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateU_introDao {
    //status 200
    //通过userID，更改 用户个人简介
    public boolean updateU_intro(String  userID, String newU_intro) {
        String sql = "UPDATE user set u_intro = ? WHERE userID = ?";
        Connection connection = JDBC.getConnection();
        PreparedStatement pstmt = null;
        int result;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, newU_intro);
            pstmt.setString(2, userID);
            result = pstmt.executeUpdate();
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
}
