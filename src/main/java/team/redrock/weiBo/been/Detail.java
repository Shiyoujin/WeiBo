package team.redrock.weiBo.been;

import lombok.Data;

import java.util.List;

@Data
public class Detail {

    //详情页的 微博
    public int id;                 //详情页的 微博id
    public String u_name;          //详情页的 用户昵称
    public String userID;          //详情页的 用户ID
    public String u_pic;           //详情页的 用户的头像
    public String d_image;         //详情页的 发送微博的图片
    public String d_times;         //详情页的 微博时间
    public String d_content;       //详情页的 微博内容
    public String greatNumber;     //详情页的 点赞数
    public String commentNumber;   //详情页的 评论数
    public String isGreat;         //详情页的 点赞状态

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setIsGreat(String isGreat) {
        this.isGreat = isGreat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_pic(String u_pic) {
        this.u_pic = u_pic;
    }

    public void setD_image(String d_image) {
        this.d_image = d_image;
    }

    public void setD_times(String d_times) {
        this.d_times = d_times;
    }

    public void setD_content(String d_content) {
        this.d_content = d_content;
    }

    public void setGreatNumber(String greatNumber) {
        this.greatNumber = greatNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getId() {
        return id;
    }

    public String getIsGreat() {
        return isGreat;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_pic() {
        return u_pic;
    }

    public String getD_image() {
        return d_image;
    }

    public String getD_times() {
        return d_times;
    }

    public String getD_content() {
        return d_content;
    }

    public String getGreatNumber() {
        return greatNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

}
