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
import com.ljb.dao.GoodsGalleryDao;
import com.ljb.entity.GoodsGallery;
import com.ljb.service.GoodsGalleryService;

/**
 * 商品展示管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsGalleryServiceImpl extends BaseServiceImpl implements GoodsGalleryService {
    @Autowired
    private GoodsGalleryDao goodsGalleryDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = goodsGalleryDao.selectMapList(query);
        Long total = goodsGalleryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public GoodsGallery selectById(Long id) {
        return goodsGalleryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsGalleryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsGalleryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(GoodsGallery entity) {
        return goodsGalleryDao.insert(entity);
    }

    @Override
    public int update(GoodsGallery entity) {
        return goodsGalleryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsGalleryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品展示管理.xlsx", data);
    }
}
