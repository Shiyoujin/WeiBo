package team.redrock.weiBo.test;

import team.redrock.weiBo.been.Care;
import team.redrock.weiBo.dao.MutualityDao;
import team.redrock.weiBo.service.impl.CareService;

import java.util.List;

public class TestCareAll {
    public static void main(String[] args) {

        MutualityDao mutualityDao = new MutualityDao();
        CareService careService = new CareService();
        List<String> list = mutualityDao.findCareId("1");
        List<Care> listCare = mutualityDao.findCareMessage(list);
        String json = careService.createCareMessageJson(listCare);

        System.out.println(json);
    }
}
