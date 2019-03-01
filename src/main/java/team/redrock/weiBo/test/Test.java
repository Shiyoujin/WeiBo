package team.redrock.weiBo.test;

import team.redrock.weiBo.been.Care;
import team.redrock.weiBo.been.Post;
import team.redrock.weiBo.dao.MutualityDao;
import team.redrock.weiBo.dao.PostDao;
import team.redrock.weiBo.dao.U_pic;
import team.redrock.weiBo.dao.WeiboDao;
import team.redrock.weiBo.service.impl.MutualityService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        String json;

    WeiboDao weiboDao = new WeiboDao();

    MutualityDao mutualityDao = new MutualityDao();

    MutualityService mutualityService = new MutualityService();

    PostDao postDao = new PostDao();

    U_pic u_pic = new U_pic();

    List<Post> listU = new ArrayList<>();

    List list;

    List<Care> listC;

    List<String> listM;


        try {
            listU = weiboDao.findUserWeibo("0","1");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String postNumber = postDao.findPostNumber("1","0");

        list = mutualityDao.findCareId("1");  //得到 用户 post 集合

        listC = mutualityDao.findCareWeibo(list,"0");  //得到关注的人 care 集合

        listM = mutualityDao.numberFollow("1");   //得到关注、粉丝、微博数目 集合

        String u_picc =  u_pic.findU_pic("1");

         json= mutualityService.createWeibojson(listU,listC,listM,"游琎",postNumber,u_picc,"个性签名自己输入的");
        System.out.println(json);
    }

}
