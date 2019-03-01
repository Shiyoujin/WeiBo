package team.redrock.weiBo.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.weiBo.been.SearchObjcet;
import team.redrock.weiBo.servlet.Search;

import java.util.HashMap;
import java.util.List;

public class SearchService {
    //搜索功能 微博和用户 展示的  json
    public String  createSearchJson(HashMap<String,List<SearchObjcet>> hashMap) {
        //weibo微博
        List<SearchObjcet> listU = hashMap.get("user");
        //user用户
        List<SearchObjcet> listP = hashMap.get("weibo");

        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArrayU = new JSONArray();
        JSONArray jsonArrayP = new JSONArray();

        for (SearchObjcet searchObjcet : listU){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userID",searchObjcet.getUserID());
            jsonObject.put("u_name",searchObjcet.getU_name());
            jsonObject.put("u_sex",searchObjcet.getU_sex());
            jsonObject.put("u_intro",searchObjcet.getU_intro());
            jsonArrayU.add(jsonObject);
        }

        for (SearchObjcet searchObjcet : listP){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("o_onick",searchObjcet.getO_onick());
            jsonObject.put("p_id",searchObjcet.getId());
            jsonObject.put("p_content",searchObjcet.getP_content());
            jsonObject.put("p_times",searchObjcet.getP_times());
            jsonArrayP.add(jsonObject);
        }

        jsonObjectAll.put("user",jsonArrayU);

        jsonObjectAll.put("weibo",jsonArrayP);

        return jsonObjectAll.toString();
    }
}



