package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiCartDao;
import com.ljb.dao.ApiGoodsDao;
import com.ljb.entity.ApiCart;
import com.ljb.entity.ApiGoods;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiCartService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 购物缓存Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiCartServiceImpl extends BaseServiceImpl implements ApiCartService {
    @Autowired
    private ApiCartDao apiCartDao;
    @Autowired
    private ApiGoodsDao apiGoodsDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;

    @Override
    public Boolean clearCart(){
        apiCartDao.deleteByMap(Condition.build().eq("user_id",headTokenUtils.getUserId()));
        return true;
    }

    @Override
    public Boolean add(Long id){
        List<ApiCart> apiCarts=apiCartDao.selectByMap(Condition.build().eq("user_id",headTokenUtils.getUserId()).eq("goods_id",id ));
        if(apiCarts.size()>0) {
            ApiCart apiCart=apiCarts.get(0);
            apiCart.setNumber(apiCart.getNumber()!=null?apiCart.getNumber()+1:1);
            apiCartDao.updateByIdSelective(apiCart);
            return true;
        }else {
            ApiGoods apiGoods = apiGoodsDao.selectById(id);
            ApiCart apiCart = new ApiCart();
            apiCart.setGoodsId(id);
            apiCart.setNumber(1L);
            apiCart.setGoodsName(apiGoods.getName());
            apiCart.setListPicUrl(apiGoods.getListPicUrl());
            apiCart.setMarketPrice(apiGoods.getMarketPrice());
            apiCart.setRetailPrice(apiGoods.getRetailPrice());
            apiCart.setStatus(1);
            apiCart.setIsDelete(1);
            apiCart.setUserId(headTokenUtils.getUserId());
            apiCart.setCreateTime(DateUtils.getSqlDate());
            apiCart.setCreateId(headTokenUtils.getUserId());
            apiCartDao.insert(apiCart);
            return true;
        }
    }

    @Override
    public Boolean remove(Long id){
        apiCartDao.deleteByMap(Condition.build().eq("user_id",headTokenUtils.getUserId() ).eq("goods_id",id ));
        return true;
    }

    @Override
    public Boolean removeAll(){
        apiCartDao.deleteByMap(Condition.build().eq("user_id",headTokenUtils.getUserId() ));
        return true;
    }

    @Override
    public Boolean addNumber(Long id){
        ApiCart apiCart=apiCartDao.selectById(id);
        apiCart.setNumber(apiCart.getNumber()+1);
        return apiCartDao.updateByIdSelective(apiCart)==1?true:false;
    }
    @Override
    public Boolean subNumber(Long id){
        ApiCart apiCart=apiCartDao.selectById(id);
        if(apiCart.getNumber()>1) {
            apiCart.setNumber(apiCart.getNumber() - 1);
            return apiCartDao.updateByIdSelective(apiCart) == 1 ? true : false;
        }else{
            return remove(id);
        }
    }

    @Override
    public List<Map<String,Object>> cartList(){
        List<Map<String, Object>> list = apiCartDao.selectMapList(Condition.build().eq("user_id", headTokenUtils.getUserId()));
        return list;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiCartDao.selectMapList(query);
        Long total = apiCartDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiCart selectById(Long id) {
        return apiCartDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiCartDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiCartDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiCart entity) {
        return apiCartDao.insert(entity);
    }

    @Override
    public int update(ApiCart entity) {
        return apiCartDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiCartDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "购物缓存.xlsx", data);
    }
}
