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
import com.ljb.dao.GoodsSpecificationDao;
import com.ljb.entity.GoodsSpecification;
import com.ljb.service.GoodsSpecificationService;

/**
 * 商品规格管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsSpecificationServiceImpl extends BaseServiceImpl implements GoodsSpecificationService {
    @Autowired
    private GoodsSpecificationDao goodsSpecificationDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = goodsSpecificationDao.selectMapList(query);
        Long total = goodsSpecificationDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public GoodsSpecification selectById(Long id) {
        return goodsSpecificationDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsSpecificationDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsSpecificationDao.deleteBatchIds(ids);
    }

    @Override
    public int save(GoodsSpecification entity) {
        return goodsSpecificationDao.insert(entity);
    }

    @Override
    public int update(GoodsSpecification entity) {
        return goodsSpecificationDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsSpecificationDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品规格管理.xlsx", data);
    }
}
