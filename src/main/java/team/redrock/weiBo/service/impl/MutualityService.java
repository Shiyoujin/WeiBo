package team.redrock.weiBo.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import team.redrock.weiBo.been.Care;
import team.redrock.weiBo.been.Post;

import java.util.List;

public class MutualityService {
    //登录后 主页展示需要的 json
    //根据 用户自己发送微博（post，含多条），关注的人发送微博的 两个集合 返回 自己和用户微博信息json
    //根据List<String> 获得 用户name，关注人数，粉丝数，发微博条数
    public String createWeibojson(List<Post> listU, List<Care> listC,List < String > listM,String u_name,String postNumber,String u_pic,String u_intro) {


        JSONObject jsonObjectAll = new JSONObject();

        JSONArray jsonArrayUser = new JSONArray();
        JSONArray jsonArrayCare = new JSONArray();

        jsonObjectAll.put("u_name", u_name);      //用户昵称
        jsonObjectAll.put("u_intro",u_intro);     //用户个性签名
        jsonObjectAll.put("careNumber", listM.get(0)); //关注数
        jsonObjectAll.put("fansNumber", listM.get(1)); //粉丝数
        jsonObjectAll.put("postNumber",postNumber);    //微博数
        jsonObjectAll.put("u_pic",u_pic);              //用户头像


        for (Post post : listU) {                  //用户自己发送的微博
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userID",post.getUserID());
            jsonObject.put("u_name", post.getU_name());
            jsonObject.put("id",post.getP_id());
            jsonObject.put("u_content", post.getP_content());
            jsonObject.put("u_times", post.getP_times());
            jsonObject.put("g_number",post.getG_number());
            jsonObject.put("c_number",post.getC_number());
            jsonObject.put("o_opic",post.getO_opic());
            jsonObject.put("p_image",post.getP_image());
            jsonArrayUser.add(jsonObject);
        }

        for (Care care : listC) {                 //关注的人 发送的微博
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("l_name", care.getL_name());
            jsonObject.put("userID",care.getUserID());
            jsonObject.put("id",care.getL_id());
            jsonObject.put("l_content", care.getL_content());
            jsonObject.put("l_times", care.getL_times());
            jsonObject.put("g_number",care.getG_number());
            jsonObject.put("c_number",care.getC_number());
            jsonObject.put("l_pic",care.getL_pic());
            jsonObject.put("l_image",care.getL_image());
            jsonArrayCare.add(jsonObject);
        }

        jsonObjectAll.put("user", jsonArrayUser);
        jsonObjectAll.put("like", jsonArrayCare);

        return jsonObjectAll.toString();
    }
}
