package team.redrock.weiBo.been;

import java.net.URL;

//这个可以根据需要后面自己添加
public class User {
    public String userID;
    public String u_pic;  //用户的头像
    public String u_intro; //用户的个性签名



    public User(){

    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setU_intro(String u_intro) {
        this.u_intro = u_intro;
    }

    public String getU_intro() {
        return u_intro;
    }

    public String getUserID() {
        return userID;
    }

    public String getU_pic() {
        return u_pic;
    }


    public void setU_pic(String u_pic) {
        this.u_pic = u_pic;
    }

}
