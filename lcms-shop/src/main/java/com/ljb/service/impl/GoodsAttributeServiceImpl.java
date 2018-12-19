package com.ljb.service.impl;

import com.ljb.Base.BaseServiceImpl;
import com.ljb.utils.*;
import com.ljb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletResponse;
import com.ljb.dao.GoodsAttributeDao;
import com.ljb.entity.GoodsAttribute;
import com.ljb.service.GoodsAttributeService;

/**
 * 商品属性管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsAttributeServiceImpl extends BaseServiceImpl implements GoodsAttributeService {
    @Autowired
    private GoodsAttributeDao goodsAttributeDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = goodsAttributeDao.selectMapList(query);
        Long total = goodsAttributeDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public GoodsAttribute selectById(Long id) {
        return goodsAttributeDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsAttributeDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsAttributeDao.deleteBatchIds(ids);
    }

    @Override
    public int save(GoodsAttribute entity) {
        return goodsAttributeDao.insert(entity);
    }

    @Override
    public int update(GoodsAttribute entity) {
        return goodsAttributeDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsAttributeDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品属性管理.xlsx", data);
    }
}
