package team.redrock.weiBo.been;

import lombok.Data;

@Data
public class Care {

    //关注的人 发送的微博
    public int l_id;        //关注的人的微博id
    public String l_name;   //关注的人的用户昵称
    public String userID;  //关注的人 用户ID
    public String l_times; //关注的人 发送微博时间
    public String l_content; //关注的人 微博内容
    public String g_number;  //点赞数
    public String c_number;  //评论数
    public String l_pic;  //关注的人头像
    public String l_image;//关注的人 微博的图片
    public String l_intro;//关注的人 个性签名
    public String l_sex;  //关注的人 性别
    public String l_careNumber;//关注的人 关注数
    public String l_fansNumber; //关注的人 粉丝数

    public void setL_careNumber(String l_careNumber) {
        this.l_careNumber = l_careNumber;
    }

    public void setL_fansNumber(String l_fansNumber) {
        this.l_fansNumber = l_fansNumber;
    }

    public String getL_careNumber() {
        return l_careNumber;
    }

    public String getL_fansNumber() {
        return l_fansNumber;
    }

    public void setL_sex(String l_sex) {
        this.l_sex = l_sex;
    }

    public String getL_sex() {
        return l_sex;
    }

    public void setL_intro(String l_intro) {
        this.l_intro = l_intro;
    }

    public String getL_intro() {
        return l_intro;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_image(String l_image) {
        this.l_image = l_image;
    }

    public String getL_image() {
        return l_image;
    }

    public String getL_pic() {
        return l_pic;
    }

    public String getG_number() {
        return g_number;
    }

    public String getC_number() {
        return c_number;
    }

    public String getL_name() {
        return l_name;
    }

    public String getL_times() {
        return l_times;
    }

    public String getL_content() {
        return l_content;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setL_times(String l_times) {
        this.l_times = l_times;
    }

    public void setL_content(String l_content) {
        this.l_content = l_content;
    }

    public void setG_number(String g_number) {
        this.g_number = g_number;
    }

    public void setC_number(String c_number) {
        this.c_number = c_number;
    }

    public void setL_pic(String l_pic) {
        this.l_pic = l_pic;
    }
}
