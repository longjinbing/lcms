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
import com.ljb.dao.ApiCouponGoodsDao;
import com.ljb.entity.ApiCouponGoods;
import com.ljb.service.ApiCouponGoodsService;

/**
 * 优惠券关联商品Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiCouponGoodsServiceImpl extends BaseServiceImpl implements ApiCouponGoodsService {
    @Autowired
    private ApiCouponGoodsDao apiCouponGoodsDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiCouponGoodsDao.selectMapList(query);
        Long total = apiCouponGoodsDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiCouponGoods selectById(Long id) {
        return apiCouponGoodsDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiCouponGoodsDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiCouponGoodsDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiCouponGoods entity) {
        return apiCouponGoodsDao.insert(entity);
    }

    @Override
    public int update(ApiCouponGoods entity) {
        return apiCouponGoodsDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiCouponGoodsDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "优惠券关联商品.xlsx", data);
    }
}
