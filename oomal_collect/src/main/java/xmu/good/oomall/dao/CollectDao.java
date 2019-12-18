package xmu.good.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xmu.good.oomall.domain.CollectItem;
import xmu.good.oomall.domain.CollectItemPo;
import xmu.good.oomall.mapper.CollectMapper;

import java.util.List;

@Repository
public class CollectDao {

    @Autowired
    private CollectMapper collectMapper;

    public List<CollectItemPo> findCollectItemByUserId(Integer userId, Integer page, Integer limit)
    {
        return collectMapper.findCollectItemByUserId(userId,page,limit);
    }

    public CollectItemPo findCollectItemByGoodsId(Integer id)
    {
        return collectMapper.findCollectItemByGoodsId(id);
    }

    public CollectItemPo addCollectItem(CollectItemPo collectItemPo)
    {
        boolean ok=collectMapper.addCollectItem(collectItemPo);

        //数据库操作失败则将id设为0
        if(ok==false)
            collectItemPo.setId(0);

        Integer id=collectItemPo.getId();
        System.out.println(id);
        return collectItemPo;
    }

    public  boolean deleteCollectItem(Integer id)
    {
        return collectMapper.deleteCollectItem(id);
    }



}
