package team.redrock.weiBo.been;

import lombok.Data;

@Data
public class SearchObjcet {

    //搜索出来的 微博和用户
    public String u_name;  //用户昵称
    public String u_sex;   //用户性别
    public String u_intro;  //个人简介
    public String userID;
    public String o_onick;   //微博昵称
    public int id;      //微博id
    public String p_content; //微博内容
    public String p_times;   //微博时间

    public String content;   //热搜内容
    public String number;    //热搜次数

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getU_intro() {
        return u_intro;
    }

    public String getP_times() {
        return p_times;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_sex() {
        return u_sex;
    }

    public String getO_onick() {
        return o_onick;
    }

    public String getP_content() {
        return p_content;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setO_onick(String o_onick) {
        this.o_onick = o_onick;
    }

    public void setP_content(String p_content) {
        this.p_content = p_content;
    }

    public void setP_times(String p_times) {
        this.p_times = p_times;
    }

    public void setU_intro(String u_intro) {
        this.u_intro = u_intro;
    }
}
