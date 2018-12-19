package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiOrderGoodsDao;
import com.ljb.entity.ApiOrderGoods;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiOrderGoodsService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 订单商品Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiOrderGoodsServiceImpl extends BaseServiceImpl implements ApiOrderGoodsService {
    @Autowired
    private ApiOrderGoodsDao apiOrderGoodsDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;

    @Override
    public List<ApiOrderGoods> orderGoods(Long id){
        List<ApiOrderGoods> apiOrderGoods=apiOrderGoodsDao.selectByMap(Condition.build().eq("order_id",id));
        return apiOrderGoods;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        query.put("userId", headTokenUtils.getUserId());
        List<Map<String, Object>> list = apiOrderGoodsDao.selectMapList(query);
        Long total = apiOrderGoodsDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiOrderGoods selectById(Long id) {
        return apiOrderGoodsDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiOrderGoodsDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiOrderGoodsDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiOrderGoods entity) {
        return apiOrderGoodsDao.insert(entity);
    }

    @Override
    public int update(ApiOrderGoods entity) {
        return apiOrderGoodsDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiOrderGoodsDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "订单商品.xlsx", data);
    }
}
