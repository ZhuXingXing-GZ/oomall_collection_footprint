package xmu.good.oomall.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.good.oomall.domain.CollectItem;
import xmu.good.oomall.domain.CollectItemPo;
import xmu.good.oomall.service.CollectService;
import xmu.good.oomall.util.Login;
import xmu.good.oomall.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class CollectController {
    @Autowired
    CollectService collectService;


    /**
     * list实现类
     * 测试url：localhost:8080/collects?userId=1&type=1&page=1&limit=2&order=ASC
     //* @param userId 用户ID
     * @param page   分页页数
     * @param limit   分页大小
     * @return
     */
    @GetMapping("/collects")
    public Object list(HttpServletRequest request,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit)  {
        Integer userId;
        userId= Login.getUserId(request);
        if(userId==null)
        {
            Object reObj=ResponseUtil.unlogin();
            return reObj;
        }

        Object reObj;
        List<CollectItem> collectItemList=collectService.findCollectItemByUserId(userId, page, limit);
        if(collectItemList.isEmpty())
            reObj= ResponseUtil.fail(502,"系统内部错误");
        else reObj=ResponseUtil.ok(collectItemList);

        return reObj;
    }

    /**
     * 测试url：localhost:8080/collects?userId=1 body: { "type":"1" , "valueId":"1" }
     * @param collectItemPo
     * @return
     */
    @PostMapping("/collects")
    public Object add(@RequestBody CollectItemPo collectItemPo) {
        Object reObj;
        CollectItemPo newCollectItemPo=collectService.addCollectItem(collectItemPo);
        if (newCollectItemPo.getId()==0)
            reObj=ResponseUtil.fail(502,"数据库插入失败,商品已收藏");
        else reObj=ResponseUtil.ok(newCollectItemPo);
        return reObj;
    }

    /**
     * update实现类，目前用的是delete方法
     * 测试url：localhost:8080/collects/{i1}?userId=1 body:{ "type":"1" , "valueId":"1" }
     * @param userId
     * @param id
     * @return
     */
    @DeleteMapping("/collects/{id}")
    public Object update(@PathVariable("id") Integer id,Integer userId) {
        boolean ok=collectService.deleteCollectItem(id);
        Object reObj;
        if(ok==false)
            reObj=ResponseUtil.fail(502,"该收藏不存在，不能删除");
        else reObj=ResponseUtil.ok();
        return reObj;
    }
}
