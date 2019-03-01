package team.redrock.weiBo.been;

import lombok.Data;



//评论、回复
@Data
public class Child {
    public int id;
    public String userID;
    public String u_name;
    public String childContent;
    public String childTimes;
    public String u_pic;    //头像
    public String p_image; //图片


    public void setU_pic(String u_pic) {
        this.u_pic = u_pic;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getU_pic() {
        return u_pic;
    }

    public String getP_image() {
        return p_image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setChildContent(String childContent) {
        this.childContent = childContent;
    }

    public void setChildTimes(String childTimes) {
        this.childTimes = childTimes;
    }

    public int getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getChildContent() {
        return childContent;
    }

    public String getChildTimes() {
        return childTimes;
    }


    public String getU_name() {
        return u_name;
    }
}
