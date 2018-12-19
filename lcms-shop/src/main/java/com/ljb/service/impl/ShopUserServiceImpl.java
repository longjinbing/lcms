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
import com.ljb.dao.ShopUserDao;
import com.ljb.entity.ShopUser;
import com.ljb.service.ShopUserService;

/**
 * 商城用户管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class ShopUserServiceImpl extends BaseServiceImpl implements ShopUserService {
    @Autowired
    private ShopUserDao shopUserDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = shopUserDao.selectMapList(query);
        Long total = shopUserDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ShopUser selectById(Long id) {
        return shopUserDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return shopUserDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return shopUserDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ShopUser entity) {
        return shopUserDao.insert(entity);
    }

    @Override
    public int update(ShopUser entity) {
        return shopUserDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = shopUserDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商城用户管理.xlsx", data);
    }
}
