package team.redrock.weiBo.test;


import team.redrock.weiBo.been.Post;
import team.redrock.weiBo.dao.HotWeiboDao;

import team.redrock.weiBo.service.impl.HotWeiboService;

import java.util.List;

public class TestHotWeibo {
    public static void main(String[] args) {
        HotWeiboDao hotWeiboDao = new HotWeiboDao();
        HotWeiboService hotWeiboService = new HotWeiboService();
        if (hotWeiboDao.insertHotWeibo(1)){
            System.out.println("成功");

            List<Post>  list = hotWeiboDao.findHotPostAll();
            System.out.println(hotWeiboService.createHotWeibo(list));

        }
    }


}
