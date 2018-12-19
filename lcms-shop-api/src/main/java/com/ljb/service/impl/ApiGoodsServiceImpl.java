package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiGoodsDao;
import com.ljb.entity.ApiGoods;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiGoodsService;
import com.ljb.utils.ExcelUtils;
import com.ljb.utils.PageUtils;
import com.ljb.utils.Query;
import com.ljb.utils.ResponseUtils;
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
 * @date 2018-12-17
 */
@Service
public class ApiGoodsServiceImpl extends BaseServiceImpl implements ApiGoodsService {
    @Autowired
    private ApiGoodsDao apiGoodsDao;



    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiGoodsDao.selectMapList(map);
        Long total = apiGoodsDao.selectTotal(map);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiGoods selectById(Long id) {
        return apiGoodsDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiGoodsDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiGoodsDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiGoods entity) {
        return apiGoodsDao.insert(entity);
    }

    @Override
    public int update(ApiGoods entity) {
        return apiGoodsDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiGoodsDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品管理.xlsx", data);
    }
}
