package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.DeptDao;
import com.ljb.dao.RoleDao;
import com.ljb.dao.RoleMenuDao;
import com.ljb.entity.Dept;
import com.ljb.entity.Role;
import com.ljb.model.ExportParams;
import com.ljb.model.RoleDeptModel;
import com.ljb.security.IdentityUtils;
import com.ljb.service.RoleService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Override
    public Role update(RoleDeptModel model) {
        Role role = roleDao.selectById(model.getId());
        role.setName(model.getName());
        role.setDescription(model.getDescription());
        role.setRemark(model.getRemark());
        role.setUpdateId(IdentityUtils.getUserId());
        roleDao.updateByIdSelective(role);
        return null;
    }

    @Override
    public Role save(RoleDeptModel model) {
        Role role = new Role();
        role.setName(model.getName());
        role.setDescription(model.getDescription());
        role.setRemark(model.getRemark());
        role.setCreateId(IdentityUtils.getUserId());
        roleDao.insert(role);
        return role;
    }

    @Override
    public List<Map<String, Object>> roleListByuserIdAndAdminId(Long userId) {
        //登录账户所拥有的角色
        List<Role> allRoles;
        //判断是否是超级管理员
        if(IdentityUtils.getIdentity().hasRole(Constant.SUPER_ADMIN)){
            allRoles=roleDao.selectList(null);
        }else {
            allRoles = roleDao.findByUserId(getUserId());
        }
        //将赋予角色用户拥有的角色
        List<Role> hasRoles;
        hasRoles = roleDao.findByUserId(userId);
        Map<Long, Object> ids = new HashMap<>();
        for (Role role : hasRoles) {
            ids.put(role.getId(), "");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Role role : allRoles) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("name", role.getName());
            if (ids.containsKey(role.getId())) {
                map.put("checked", "checked");
            } else {
                map.put("checked", false);
            }
            result.add(map);
        }
        return result;
    }

    @Override
    public Object selectByRole(Long id) {
        Role role = roleDao.selectById(id);
        Map<String, Object> map = new HashMap<>();
        map = BeanUtils.bean2Map(role, true);
        Dept Dept = deptDao.findByRole(role.getId());
        map.put("deptId", Dept.getId());
        map.put("deptName", Dept.getName());
        return map;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = roleDao.selectMapList(query);
        Long total = roleDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Role selectById(Long id) {
        return roleDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return roleDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return roleDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Role entity) {
        return roleDao.insert(entity);
    }

    @Override
    public int update(Role entity) {
        return roleDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = roleDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "角色管理.xlsx", data);
    }
}
