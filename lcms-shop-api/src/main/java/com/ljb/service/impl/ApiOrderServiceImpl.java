package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiOrderDao;
import com.ljb.dao.ApiOrderGoodsDao;
import com.ljb.entity.ApiCart;
import com.ljb.entity.ApiOrder;
import com.ljb.entity.ApiOrderGoods;
import com.ljb.model.ExportParams;
import com.ljb.model.OrderModel;
import com.ljb.service.ApiCartService;
import com.ljb.service.ApiOrderService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiOrderServiceImpl extends BaseServiceImpl implements ApiOrderService {
    @Autowired
    private ApiOrderDao apiOrderDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;
    @Autowired
    private ApiCartService apiCartService;
    @Autowired
    private ApiOrderGoodsDao apiOrderGoodsDao;


    @Override
    public Map<String, Object> save(OrderModel orderModel) {
        ApiOrder apiOrder = new ApiOrder();
        apiOrder.setAddress(orderModel.getAddress());
        apiOrder.setCity(orderModel.getCity());
        apiOrder.setProvince(orderModel.getProvince());
        apiOrder.setDistrict(orderModel.getStreet());
        apiOrder.setCreateId(headTokenUtils.getUserId());
        apiOrder.setCreateTime(DateUtils.getSqlDate());
        apiOrder.setUpdateTime(DateUtils.getSqlDate());
        apiOrder.setUpdateId(headTokenUtils.getUserId());
        apiOrder.setPhone(orderModel.getPhone());
        apiOrder.setUserId(headTokenUtils.getUserId());
        apiOrder.setConsignee(orderModel.getName());
        apiOrder.setOrderSn(IdUtil.createIdByDate());
        apiOrder.setStatus(1);
        apiOrder.setIsDelete(1);
        apiOrder.setShippingStatus(1);
        apiOrder.setShippingFee(new BigDecimal(0));
        apiOrderDao.insert(apiOrder);
        Map<String,Object> cartInfo=orderModel.getOrderInfo();
        BigDecimal sum=new BigDecimal(0);
        for(Iterator iterator=cartInfo.keySet().iterator();iterator.hasNext();){
           Long cartId=Long.valueOf(iterator.next().toString());
            ApiCart apiCart=apiCartService.selectById(cartId);
            sum.add(apiCart.getRetailPrice().multiply(new BigDecimal(apiCart.getNumber())));
            ApiOrderGoods apiOrderGoods=new ApiOrderGoods();
            apiOrderGoods.setGoodsId(apiCart.getGoodsId());
            apiOrderGoods.setListPicUrl(apiCart.getListPicUrl());
            apiOrderGoods.setGoodsName(apiCart.getGoodsName());
            apiOrderGoods.setGoodsSn(apiCart.getGoodsSn());
            apiOrderGoods.setCreateId(headTokenUtils.getUserId());
            apiOrderGoods.setCreateTime(DateUtils.getSqlDate());
            apiOrderGoods.setStatus(1);
            apiOrderGoods.setIsDelete(1);
            apiOrderGoods.setNumber(apiCart.getNumber());
            apiOrderGoods.setOrderId(apiOrder.getId());
            apiOrderGoods.setRetailPrice(apiCart.getRetailPrice());
            apiOrderGoods.setMarketPrice(apiCart.getMarketPrice());
            apiOrderGoodsDao.insert(apiOrderGoods);
        }
        apiOrder.setGoodsPrice(sum);
        apiOrder.setFullCutPrice(new BigDecimal(0));
        apiOrder.setActualPrice(sum);
        apiOrder.setCouponPrice(new BigDecimal(0));
        apiOrder.setFreightPrice(0);
        apiOrderDao.updateByIdSelective(apiOrder);
        apiCartService.clearCart();
        return apiOrderDao.selectMapById(apiOrder.getId());
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiOrderDao.selectMapList(query);
        Long total = apiOrderDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiOrder selectById(Long id) {
        return apiOrderDao.selectById(id);
    }

    @Override
    public Map<String, Object> selectMapById(Long id) {
        return apiOrderDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiOrderDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiOrder entity) {
        return apiOrderDao.insert(entity);
    }

    @Override
    public int update(ApiOrder entity) {
        return apiOrderDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiOrderDao.selectMapList(exportParams.getQueryParams());
        byte[] data = ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "订单管理.xlsx", data);
    }
}
