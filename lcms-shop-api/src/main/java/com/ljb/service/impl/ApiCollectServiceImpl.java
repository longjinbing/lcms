package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiCollectDao;
import com.ljb.dao.ApiGoodsDao;
import com.ljb.entity.ApiCollect;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiCollectService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 商品收藏Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiCollectServiceImpl extends BaseServiceImpl implements ApiCollectService {
    @Autowired
    private ApiCollectDao apiCollectDao;
    @Autowired
    private ApiGoodsDao apiGoodsDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;


    @Override
    public Boolean add(Long id){
        ApiCollect apiCollect=new ApiCollect();
        apiCollect.setCreateTime(DateUtils.getSqlDate());
        apiCollect.setUserId(headTokenUtils.getUserId());
        apiCollect.setValueId(id);
        apiCollect.setStatus(1);
        apiCollect.setIsDelete(1);
        apiCollect.setCreateId(headTokenUtils.getUserId());
        return apiCollectDao.insert(apiCollect)==1?true:false;
    }

    @Override
    public Boolean remove(Long id){
        return apiCollectDao.deleteByMap(Condition.build().eq("user_id",id ).eq("value_id",id ))==1?true:false;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiCollectDao.selectMapList(query);
        Long total = apiCollectDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiCollect selectById(Long id) {
        return apiCollectDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiCollectDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiCollectDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiCollect entity) {
        return apiCollectDao.insert(entity);
    }

    @Override
    public int update(ApiCollect entity) {
        return apiCollectDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiCollectDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品收藏.xlsx", data);
    }
}
