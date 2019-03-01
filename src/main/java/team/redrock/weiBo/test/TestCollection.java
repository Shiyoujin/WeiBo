package team.redrock.weiBo.test;

import team.redrock.weiBo.been.Collection;
import team.redrock.weiBo.dao.CollectionDao;
import team.redrock.weiBo.service.impl.CollectionService;

import java.util.List;

public class TestCollection {
    public static void main(String[] args) {

        CollectionDao collectionDao = new CollectionDao();
        CollectionService collectionService = new CollectionService();
        List p_idList = collectionDao.findUerCollectionP_id("1");
        List<Collection> list = collectionDao.findCollectionWeibo(p_idList);
        String collectionNumber = collectionDao.findCollectionNumber("1");
        String json = collectionService.createCollectionJson(list,collectionNumber);
        System.out.println(json);
    }

}
