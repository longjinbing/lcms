package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.UserDao;
import com.ljb.entity.User;
import com.ljb.model.ExportParams;
import com.ljb.service.UserService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 系统用户管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = userDao.selectMapList(query);
        Long total = userDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public User findByCode(String code){
        List<User> list=userDao.selectByMap(Condition.build().eq("number", code));
        return list.size()>0?list.get(0):null;
    }

    @Override
    public User selectById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return userDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return userDao.deleteBatchIds(ids);
    }

    @Override
    public int save(User entity) {
        return userDao.insert(entity);
    }

    @Override
    public int update(User entity) {
        return userDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = userDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "系统用户管理.xlsx", data);
    }
}
