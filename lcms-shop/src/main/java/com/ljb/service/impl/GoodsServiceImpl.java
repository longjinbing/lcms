package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.GoodsDao;
import com.ljb.entity.Goods;
import com.ljb.model.ExportParams;
import com.ljb.service.GoodsService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 商品管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = goodsDao.selectMapList(query);
        Long total = goodsDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Goods selectById(Long id) {
        return goodsDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Goods entity) {
        entity.setGoodsSn(IdUtil.createIdByDate());
        return goodsDao.insert(entity);
    }

    @Override
    public int update(Goods entity) {
        return goodsDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品管理.xlsx", data);
    }
}
