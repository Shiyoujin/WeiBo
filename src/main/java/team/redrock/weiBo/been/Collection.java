package team.redrock.weiBo.been;

import team.redrock.weiBo.dao.JDBC;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class Collection {
    //每一个微博id，微博内容，微博发送时间，用户id，用户昵称，
    //暂时 不用微博id，收藏列表暂时不跳转

    public String p_content; //微博内容
    public String p_times; //微博发送时间
    public String o_id; //用户id
    public String o_onick; //用户昵称
    public String o_opic; //用户头像
    public String p_image; //微博图片
    public String g_number; //点赞数
    public String c_number; //评论数

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

    public void setP_content(String p_content) {
        this.p_content = p_content;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public void setP_times(String p_times) {
        this.p_times = p_times;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public void setO_onick(String o_onick) {
        this.o_onick = o_onick;
    }

    public void setO_opic(String o_opic) {
        this.o_opic = o_opic;
    }


    public String getP_content() {
        return p_content;
    }

    public String getP_image() {
        return p_image;
    }

    public String getP_times() {
        return p_times;
    }

    public String getO_id (){
        return o_id;
    }

    public String getO_onick() {
        return o_onick;
    }

    public String getO_opic() {
        return o_opic;
    }


}
