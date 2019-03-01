//package team.redrock.weiBo.test;
//
//import team.redrock.weiBo.dao.GreatDao;
//
//import java.sql.SQLException;
//
//public class TestChildServlet {
//    public static void main(String[] args) {
//        String userID = "1";
//        int id = 1;
//
//
//        String status = "cancel";
//
//        GreatDao greatDao = new GreatDao();
//
//
//        if (status == "great"){
//            if (greatDao.insertGreat(id,userID)){
//                String newGreatNumber = greatDao.greatNumber(id);
//                greatDao.updateG_number(newGreatNumber,id);
//            }
//        }else if (status == "cancel")
//        {
//            try {
//                greatDao.deleteGreat(id,userID);
//                String newGreatNumber = greatDao.greatNumber(id);
//                greatDao.updateG_number(newGreatNumber,id);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    }
