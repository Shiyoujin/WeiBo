package team.redrock.weiBo.test;

import team.redrock.weiBo.been.Detail;
import team.redrock.weiBo.dao.DetailDao;
import team.redrock.weiBo.service.impl.DetailService;

public class TestDetail {
    public static void main(String[] args) {
        DetailDao detailDao = new DetailDao();
        Detail detail = detailDao.findDetatilWeibo(3,"3");
        System.out.println(new DetailService().createDetail(detail));    //组装 json时，数据库只用一根 \ ,打印出来是两根 \\ ,但 打印对象时 一根\，就是一根\

    }
}
