package xmu.good.oomall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xmu.good.oomall.domain.CollectItem;
import xmu.good.oomall.domain.CollectItemPo;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper {
    /**
     *
     * @param userId
     * @return
     */
    List<CollectItemPo> findCollectItemByUserId(@Param("userId") Integer userId, @Param("page") Integer page, @Param("limit") Integer limit);

    CollectItemPo findCollectItemByGoodsId(@Param("goodsId") Integer goodsId);

    boolean addCollectItem(CollectItemPo collectItemPo);

    boolean deleteCollectItem(@Param("id") Integer id);


}
