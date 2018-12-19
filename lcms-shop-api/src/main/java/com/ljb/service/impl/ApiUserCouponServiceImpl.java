package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiCouponDao;
import com.ljb.dao.ApiUserCouponDao;
import com.ljb.entity.ApiCoupon;
import com.ljb.entity.ApiUserCoupon;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiUserCouponService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户优惠卷Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiUserCouponServiceImpl extends BaseServiceImpl implements ApiUserCouponService {
    @Autowired
    private ApiUserCouponDao apiUserCouponDao;
    @Autowired
    private ApiCouponDao apiCouponDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;


    @Override
    public Boolean getCoupon(Long id){
        ApiCoupon apiCoupon=apiCouponDao.selectById(id);
        ApiUserCoupon apiUserCoupon=new ApiUserCoupon();
        apiUserCoupon.setAddTime(DateUtils.getSqlDate());
        apiUserCoupon.setUserId(headTokenUtils.getUserId());
        apiUserCoupon.setCreateId(headTokenUtils.getUserId());
        apiUserCoupon.setCreateTime(DateUtils.getSqlDate());
        return apiUserCouponDao.insert(apiUserCoupon)==1?true:false;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiUserCouponDao.selectMapList(query);
        Long total = apiUserCouponDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiUserCoupon selectById(Long id) {
        return apiUserCouponDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiUserCouponDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiUserCouponDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiUserCoupon entity) {
        return apiUserCouponDao.insert(entity);
    }

    @Override
    public int update(ApiUserCoupon entity) {
        return apiUserCouponDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiUserCouponDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "用户优惠卷.xlsx", data);
    }
}
