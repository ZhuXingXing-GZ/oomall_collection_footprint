package xmu.good.oomall.service;

import org.springframework.stereotype.Service;
import xmu.good.oomall.domain.FootprintItem;
import xmu.good.oomall.domain.FootprintItemPo;

import java.util.List;

@Service
public interface FootPrintService {
    List<FootprintItem> findFootPrintItemByUserIdAndGoodsId(Integer userId, Integer goodsId, Integer page, Integer limit);

    List<FootprintItem> findFootPrintItemByUserId(Integer userId, Integer page, Integer limit);

    boolean deleteFootPrintItemByUserIdAndId(Integer userId,Integer id);

    FootprintItemPo addFootPrintItem(FootprintItemPo footprintItemPo);
}
