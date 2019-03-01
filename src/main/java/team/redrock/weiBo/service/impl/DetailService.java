package team.redrock.weiBo.service.impl;


import net.sf.json.JSONObject;

import team.redrock.weiBo.been.Detail;



public class DetailService {
    //微博详情页展示的 json
    public String createDetail(Detail detail) {

        JSONObject jsonObjectAll = new JSONObject();

        jsonObjectAll.put("id", detail.getId());
        jsonObjectAll.put("userID",detail.getUserID());
        jsonObjectAll.put("u_name", detail.getU_name());
        jsonObjectAll.put("d_times", detail.getD_times());
        jsonObjectAll.put("d_content", detail.getD_content());
        jsonObjectAll.put("isGreat", detail.getIsGreat());
        jsonObjectAll.put("g_Number", detail.getGreatNumber());
        jsonObjectAll.put("c_Number", detail.getCommentNumber());
        jsonObjectAll.put("u_pic", detail.getU_pic());
        jsonObjectAll.put("d_image",detail.getD_image());
        return jsonObjectAll.toString();
    }
}
