package xmu.good.oomall.service;

import org.springframework.stereotype.Service;
import xmu.good.oomall.domain.CollectItem;
import xmu.good.oomall.domain.CollectItemPo;

import java.util.List;

@Service
public interface CollectService {
    public List<CollectItem> findCollectItemByUserId(Integer userId, Integer page, Integer limit);

    public CollectItemPo addCollectItem(CollectItemPo collectItemPo);

    public boolean deleteCollectItem(Integer id);
}
