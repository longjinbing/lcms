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
import com.ljb.dao.GoodsAttributeCategoryDao;
import com.ljb.entity.GoodsAttributeCategory;
import com.ljb.service.GoodsAttributeCategoryService;

/**
 * 商品属性类型管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsAttributeCategoryServiceImpl extends BaseServiceImpl implements GoodsAttributeCategoryService {
    @Autowired
    private GoodsAttributeCategoryDao goodsAttributeCategoryDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = goodsAttributeCategoryDao.selectMapList(query);
        Long total = goodsAttributeCategoryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public GoodsAttributeCategory selectById(Long id) {
        return goodsAttributeCategoryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsAttributeCategoryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsAttributeCategoryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(GoodsAttributeCategory entity) {
        return goodsAttributeCategoryDao.insert(entity);
    }

    @Override
    public int update(GoodsAttributeCategory entity) {
        return goodsAttributeCategoryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsAttributeCategoryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品属性类型管理.xlsx", data);
    }
}
