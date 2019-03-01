package team.redrock.weiBo.toolsBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Change {
    public static String dateTimeChange(Date date){
        String strDate="";
        if(date!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strDate=format.format(date);
        }
        return strDate;
    }
}
