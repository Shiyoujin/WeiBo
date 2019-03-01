package team.redrock.weiBo.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.weiBo.been.Care;
import team.redrock.weiBo.dao.MutualityDao;
import java.util.List;

//关注列表 展示的json
public class CareService {
    public String createCareMessageJson(List<Care> list) {

        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Care care : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userID",care.getUserID());
            jsonObject.put("l_name",care.getL_name());
            jsonObject.put("l_sex",care.getL_sex());
            jsonObject.put("l_intro",care.getL_intro());
            jsonObject.put("l_pic",care.getL_pic());
            jsonObject.put("l_careNumber",care.getL_careNumber());
            jsonObject.put("l_fansNumber",care.getL_fansNumber());
            jsonArray.add(jsonObject);
        }
        jsonObjectAll.put("CareMessage",jsonArray);
        return jsonObjectAll.toString();
    }

    public static void main(String[] args) {
        MutualityDao mutualityDao = new MutualityDao();

        List<String> list = mutualityDao.findCareId("5");
        List<Care> listCare = mutualityDao.findCareMessage(list);
        System.out.println(new CareService().createCareMessageJson(listCare));
    }
}
