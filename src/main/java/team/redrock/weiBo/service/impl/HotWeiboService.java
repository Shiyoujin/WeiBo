package team.redrock.weiBo.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.weiBo.been.Post;

import java.util.List;

public class HotWeiboService {
    //微博热门 展示的json
    public String createHotWeibo(List<Post> list){
        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Post post : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",post.getP_id());
            jsonObject.put("u_name",post.getU_name());
            jsonObject.put("number",post.getNumber());
            jsonObject.put("u_pic",post.getO_opic());
            jsonObject.put("p_times",post.getP_times());
            jsonObject.put("p_content",post.getP_content());
            jsonObject.put("g_number",post.getG_number());
            jsonObject.put("c_number",post.getC_number());
            jsonObject.put("p_image",post.getP_image());
            jsonArray.add(jsonObject);
        }
        jsonObjectAll.put("hotweibo",jsonArray);
        return jsonObjectAll.toString();
    }


}
