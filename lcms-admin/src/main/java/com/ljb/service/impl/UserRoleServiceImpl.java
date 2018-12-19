package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.UserRoleDao;
import com.ljb.entity.UserRole;
import com.ljb.model.ExportParams;
import com.ljb.model.UserRoleModel;
import com.ljb.service.UserRoleService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户角色管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public int saveUserRole(UserRoleModel userRoleModel) {
        userRoleDao.deleteByMap(Condition.build().eq("user_id",userRoleModel.getUserId()));
        List<UserRole> list = new ArrayList<>();
        for (Long roleId : userRoleModel.getRoleIds()) {
            UserRole userRole = new UserRole();
            userRole.setId(IdUtil.service().genId());
            userRole.setUserId(userRoleModel.getUserId());
            userRole.setRoleId(roleId);
            userRole.setIsDelete(1);
            userRole.setCreateId(getUserId());
            userRole.setUpdateId(getUserId());
            userRole.setCreateTime(DateUtils.getSqlDate());
            userRole.setUpdateTime(DateUtils.getSqlDate());
            list.add(userRole);
        }
        return userRoleDao.saveBatch(list) > 0 ? 1 : 0;
    }


    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = userRoleDao.selectMapList(query);
        Long total = userRoleDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public UserRole selectById(Long id) {
        return userRoleDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return userRoleDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return userRoleDao.deleteBatchIds(ids);
    }

    @Override
    public int save(UserRole entity) {
        return userRoleDao.insert(entity);
    }

    @Override
    public int update(UserRole entity) {
        return userRoleDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = userRoleDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "用户角色管理.xlsx", data);
    }
}
