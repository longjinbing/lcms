package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.GoodsCategoryDao;
import com.ljb.entity.GoodsCategory;
import com.ljb.model.ExportParams;
import com.ljb.service.GoodsCategoryService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品分类管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsCategoryServiceImpl extends BaseServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryDao goodsCategoryDao;


    @Override
    public List<Map<String,Object>> treeList() {
        return TreeUtils.buildMapTree(goodsCategoryDao.selectMapList(null),null);
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        Integer offset = Integer.valueOf(map.getOrDefault("offset", 0).toString());
        Integer limit = Integer.valueOf(map.getOrDefault("limit", 10).toString());
        List<Map<String, Object>> list = goodsCategoryDao.selectMapList(null);
        list = TreeUtils.buildMapTree(list);
        list = list.stream().skip(offset).limit(limit).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(list, Long.valueOf(list.size()), query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public GoodsCategory selectById(Long id) {
        return goodsCategoryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsCategoryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsCategoryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(GoodsCategory entity) {
        return goodsCategoryDao.insert(entity);
    }

    @Override
    public int update(GoodsCategory entity) {
        return goodsCategoryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsCategoryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品分类管理.xlsx", data);
    }
}
