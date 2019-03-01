package team.redrock.weiBo.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.weiBo.been.Child;
import team.redrock.weiBo.dao.DetailDao;

import java.util.List;

//评论和回复的 json
public class ChildService {
    public String createChildJson(List<Child> list){
        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Child child : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",child.getId());
            jsonObject.put("userID",child.getUserID());
            jsonObject.put("u_name",child.getU_name());
            jsonObject.put("p_times",child.getChildTimes());
            jsonObject.put("content",child.getChildContent());
            jsonObject.put("u_pic",child.getU_pic());
            jsonObject.put("p_image",child.getP_image());
            jsonArray.add(jsonObject);
        }
        jsonObjectAll.put("child",jsonArray);
        return jsonObjectAll.toString();
    }

    public static void main(String[] args) {
        DetailDao detailDao = new DetailDao();
        List<Child> childList = detailDao.findChild(1);
        System.out.println(new ChildService().createChildJson(childList));
    }

}
