package team.redrock.weiBo.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.weiBo.been.Collection;

import java.util.List;

public class CollectionService {
    //status 200
    //收藏列表的 json
    public String createCollectionJson(List<Collection> list,String collectionNumber){

        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArray =new JSONArray();

        jsonObjectAll.put("collectionNumber",collectionNumber);

        for (Collection collection : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("u_name",collection.getO_onick());
            jsonObject.put("u_pic",collection.getO_opic());
            jsonObject.put("p_times",collection.getP_times());
            jsonObject.put("p_content",collection.getP_content());
            jsonObject.put("g_number",collection.getG_number());
            jsonObject.put("c_number",collection.getC_number());
            jsonObject.put("p_image",collection.getP_image());
            jsonArray.add(jsonObject);
        }

        jsonObjectAll.put("collection",jsonArray);

        return jsonObjectAll.toString();
    }


}
