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
import com.ljb.dao.ApiCouponDao;
import com.ljb.entity.ApiCoupon;
import com.ljb.service.ApiCouponService;

/**
 * 商品优惠卷Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiCouponServiceImpl extends BaseServiceImpl implements ApiCouponService {
    @Autowired
    private ApiCouponDao apiCouponDao;



    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiCouponDao.selectMapList(query);
        Long total = apiCouponDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiCoupon selectById(Long id) {
        return apiCouponDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiCouponDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiCouponDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiCoupon entity) {
        return apiCouponDao.insert(entity);
    }

    @Override
    public int update(ApiCoupon entity) {
        return apiCouponDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiCouponDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品优惠卷.xlsx", data);
    }
}
