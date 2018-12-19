package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiGoodsCategoryDao;
import com.ljb.entity.ApiGoodsCategory;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiGoodsCategoryService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 商品分类Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiGoodsCategoryServiceImpl extends BaseServiceImpl implements ApiGoodsCategoryService {
    @Autowired
    private ApiGoodsCategoryDao apiGoodsCategoryDao;

    @Override
    public List<Map<String,Object>> selectList(){
        List<Map<String, Object>> list = apiGoodsCategoryDao.selectMapList(null);
        return TreeUtils.buildMapTree(list,null);
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiGoodsCategoryDao.selectMapList(query);
        Long total = apiGoodsCategoryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiGoodsCategory selectById(Long id) {
        return apiGoodsCategoryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiGoodsCategoryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiGoodsCategoryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiGoodsCategory entity) {
        return apiGoodsCategoryDao.insert(entity);
    }

    @Override
    public int update(ApiGoodsCategory entity) {
        return apiGoodsCategoryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiGoodsCategoryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品分类.xlsx", data);
    }
}
