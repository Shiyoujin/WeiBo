package team.redrock.weiBo.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.weiBo.been.SearchObjcet;
import team.redrock.weiBo.dao.SearchDao;



import java.util.List;

public class HotSearchService {
    //热门搜索 展示的json
    public String createHotSearch(List<SearchObjcet> list){
        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (SearchObjcet searchObjcet : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content",searchObjcet.content);
            jsonObject.put("number",searchObjcet.getNumber());
            jsonArray.add(jsonObject);
        }
        jsonObjectAll.put("HotSearch",jsonArray);
        return jsonObjectAll.toString();
    }

    public static void main(String[] args) {
        SearchDao searchDao = new SearchDao();
        List<SearchObjcet> list = searchDao.findSearchAll();
        System.out.println(new HotSearchService().createHotSearch(list));
    }
}
