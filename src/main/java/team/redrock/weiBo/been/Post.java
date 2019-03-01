package team.redrock.weiBo.been;

import lombok.Data;

import java.util.List;

//这一个注解非常好用，注解在类上；提供类所有属性的 getting 和 setting 方法，
// 此外还提供了equals、canEqual、hashCode、------toString 方法
@Data
public class Post {

    //微博
    public int p_id; //此微博的id
    public String p_content; //微博内容
    public String p_times; //微博日期
    public String p_image;   //微博的图片
    public String u_name;   //用户的昵称
    public String o_opic;    //用户的头像
    public String userID; //用户的id
    public String g_number; //微博点赞数
    public String c_number; //评论数

    public String number;  //微博热搜中的 点击微博进入详情页的 次数


    public Post(){

    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setG_number(String g_number) {
        this.g_number = g_number;
    }

    public void setC_number(String c_number) {
        this.c_number = c_number;
    }

    public String getG_number() {
        return g_number;
    }

    public String getC_number() {
        return c_number;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setP_content(String p_content) {
        this.p_content = p_content;
    }

    public void setP_times(String p_times) {
        this.p_times = p_times;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setO_opic(String o_opic) {
        this.o_opic = o_opic;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getUserID() {
        return userID;
    }

    public String getP_content() {
        return p_content;
    }

    public String getP_times() {
        return p_times;
    }

    public String getU_name() {
        return u_name;
    }

    public String getO_opic() {
        return o_opic;
    }

    public String getP_image() {
        return p_image;
    }

    //构造方法
    //public Post()


}
