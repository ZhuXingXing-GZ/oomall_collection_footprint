package xmu.good.oomall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.good.oomall.dao.FootPrintDao;
import xmu.good.oomall.domain.FootprintItem;
import xmu.good.oomall.domain.FootprintItemPo;
import xmu.good.oomall.domain.Goods;
import xmu.good.oomall.domain.GoodsPo;
import xmu.good.oomall.service.FootPrintService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FootPrintServiceImpl implements FootPrintService {

    @Autowired
    FootPrintDao footPrintDao;

    @Override
    public List<FootprintItem> findFootPrintItemByUserIdAndGoodsId(Integer userId, Integer goodsId, Integer page, Integer limit) {
        List<FootprintItemPo> footprintItemPos=footPrintDao.findFootPrintItemByUserIdAndGoodsId(userId, goodsId, page, limit);

        List<FootprintItem> footprintItems = new ArrayList<>();
        for(int i=0;i<footprintItemPos.size();i++)
        {
            FootprintItem footprintItem=new FootprintItem(footprintItemPos.get(i));

            /**
             * TODO :根据goodsId从商品微服务中获取goods实体
             */
            GoodsPo goodsPo=new GoodsPo();

            goodsPo.setId(footprintItemPos.get(i).getGoodsId());
            goodsPo.setName("商品"+footprintItemPos.get(i).getGoodsId());

            footprintItem.setGoodsPo(goodsPo);

            footprintItems.add(footprintItem);
        }
        return footprintItems;//footPrintDao.findFootPrintItemByUserIdAndGoodsId(userId, goodsId, page, limit, sort, order);
    }

    @Override
    public List<FootprintItem> findFootPrintItemByUserId(Integer userId, Integer page, Integer limit) {
        List<FootprintItemPo> footprintItemPoList =footPrintDao.findFootPrintItemByUserId(userId, page, limit);
        List<FootprintItem> footprintItemList=new ArrayList<>();
        for(int i=0;i<footprintItemPoList.size();i++)
        {
            FootprintItem footprintItem=new FootprintItem(footprintItemPoList.get(i));


            /**
             * TODO:根据goodsid从good服务中获取商品实体
             */
            GoodsPo goodsPo=new GoodsPo();
            goodsPo.setId(footprintItemPoList.get(i).getId());
            goodsPo.setName("商品"+footprintItemPoList.get(i).getId());

            footprintItem.setGoodsPo(goodsPo);
            footprintItemList.add(footprintItem);
        }
        return footprintItemList;
    }

    @Override
    public boolean deleteFootPrintItemByUserIdAndId(Integer userId, Integer id) {
        return footPrintDao.deleteFootPrintItemByUserIdAndId(userId, id);
    }

    @Override
    public FootprintItemPo addFootPrintItem(FootprintItemPo footprintItemPo) {
        footprintItemPo.setBirthTime(LocalDateTime.now());
        footprintItemPo.setGmtCreate(LocalDateTime.now());
        return footPrintDao.addFootPrintItem(footprintItemPo);
    }
}
