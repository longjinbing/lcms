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
import com.ljb.dao.ApiGoodsGalleryDao;
import com.ljb.entity.ApiGoodsGallery;
import com.ljb.service.ApiGoodsGalleryService;

/**
 * 商品展示Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiGoodsGalleryServiceImpl extends BaseServiceImpl implements ApiGoodsGalleryService {
    @Autowired
    private ApiGoodsGalleryDao apiGoodsGalleryDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiGoodsGalleryDao.selectMapList(query);
        Long total = apiGoodsGalleryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiGoodsGallery selectById(Long id) {
        return apiGoodsGalleryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiGoodsGalleryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiGoodsGalleryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiGoodsGallery entity) {
        return apiGoodsGalleryDao.insert(entity);
    }

    @Override
    public int update(ApiGoodsGallery entity) {
        return apiGoodsGalleryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiGoodsGalleryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品展示.xlsx", data);
    }
}
