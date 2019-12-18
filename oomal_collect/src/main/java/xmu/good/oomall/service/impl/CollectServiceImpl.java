package xmu.good.oomall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.good.oomall.dao.CollectDao;
import xmu.good.oomall.domain.CollectItem;
import xmu.good.oomall.domain.CollectItemPo;
import xmu.good.oomall.domain.GoodsPo;
import xmu.good.oomall.service.CollectService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectDao collectDao;

    @Override
    public List<CollectItem> findCollectItemByUserId(Integer userId, Integer page, Integer limit) {
        List<CollectItem> collectItemList =new ArrayList<>();
        List<CollectItemPo> collectItemPoList =collectDao.findCollectItemByUserId(userId, page, limit);
        for(int i=0;i<collectItemPoList.size();i++)
        {

            CollectItem collectItem=new CollectItem(collectItemPoList.get(i));

            /**
             * TODO:从goods服务中获取goodsPo实体
             */
            GoodsPo goodsPo=new GoodsPo();
            goodsPo.setId(collectItemPoList.get(i).getGoodsId());
            goodsPo.setName("商品"+collectItemPoList.get(i).getGoodsId());

            collectItem.setGoodsPo(goodsPo);

            collectItemList.add(collectItem);

        }
        return collectItemList;
    }

    @Override
    public CollectItemPo addCollectItem(CollectItemPo collectItemPo) {
        collectItemPo.setGmtCreate(LocalDateTime.now());
        collectItemPo.setGmtModified(LocalDateTime.now());
        CollectItemPo newcollectItemPo= collectDao.findCollectItemByGoodsId(collectItemPo.getGoodsId());
        System.out.println(newcollectItemPo);
        if(newcollectItemPo!=null)
        {
            //如果要插入的收藏已经存在则不能删除，将id标识为0
            newcollectItemPo.setId(0);
            return newcollectItemPo;
        }
        else return collectDao.addCollectItem(collectItemPo);
    }

    @Override
    public boolean deleteCollectItem(Integer id) {
        return collectDao.deleteCollectItem(id);
    }
}
