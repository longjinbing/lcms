package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiUserDao;
import com.ljb.entity.ApiUser;
import com.ljb.exception.AppException;
import com.ljb.model.AppRegisterModel;
import com.ljb.model.ExportParams;
import com.ljb.model.LoginStatus;
import com.ljb.model.SetPasswordModel;
import com.ljb.service.ApiUserService;
import com.ljb.service.Kaptcha;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 商城用户Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiUserServiceImpl extends BaseServiceImpl implements ApiUserService {
    @Autowired
    private ApiUserDao apiUserDao;
    @Autowired
    private Kaptcha kaptcha;
    @Autowired
    private HeadTokenUtils headTokenUtils;

    @Override
    public LoginStatus usernameAndPasswordLogin(String username, String password){
        List<ApiUser> apiUsers=apiUserDao.selectByMap(Condition.build().eq("username",username));
        if(apiUsers.size()!=1){
            return LoginStatus.usernameOrPasswordError();
        }
        ApiUser apiUser=apiUsers.get(0);
        if(!new BCryptPasswordEncoder().matches(password, apiUser.getPassword())){
            return LoginStatus.usernameOrPasswordError();
        }
        if(apiUser.getStatus()!=1){
            return LoginStatus.error("账户异常，无法登录");
        }
        headTokenUtils.setCookie(apiUser.getId().toString());
        return LoginStatus.success(apiUser);
    }

    @Override
    public LoginStatus phoneLogin(String phone, String kaptcha){
        List<ApiUser> apiUsers=apiUserDao.selectByMap(Condition.build().eq("mobile",phone));
        if(apiUsers.size()!=1){
            return LoginStatus.usernameOrPasswordError();
        }
        ApiUser apiUser=apiUsers.get(0);
        if(apiUser.getStatus()!=1){
            return LoginStatus.error("账户异常，无法登录");
        }
        headTokenUtils.setCookie(apiUser.getId().toString());
        return LoginStatus.success(apiUser);
    }

    @Override
    public ApiUser register(AppRegisterModel appRegisterModel){
        if(apiUserDao.selectByMap(Condition.build().eq("username",appRegisterModel.getUsername())).size()>0){
            throw new AppException(HttpStatus.FORBIDDEN);
        }
        ApiUser apiUser=new ApiUser();
        apiUser.setPassword(new BCryptPasswordEncoder().encode(appRegisterModel.getPassword()));
        apiUser.setUsername(appRegisterModel.getUsername());
        apiUser.setGender(appRegisterModel.getGender());
        apiUser.setMobile(appRegisterModel.getMobile());
        apiUser.setLastLoginTime(DateUtils.getSqlDate());
        apiUser.setStatus(1);
        apiUser.setIsDelete(1);
        apiUser.setCreateTime(DateUtils.getSqlDate());
        apiUser.setUpdateTime(DateUtils.getSqlDate());
        apiUserDao.insert(apiUser);
        apiUser.setCreateId(apiUser.getId());
        apiUser.setUpdateId(apiUser.getId());
        apiUserDao.updateByIdSelective(apiUser);
        return apiUser;
    }

    @Override
    public ApiUser updatePassword(SetPasswordModel setPasswordModel){
        ApiUser apiUser=apiUserDao.selectById(headTokenUtils.getUserId());
        if(!apiUser.getPassword().equals(new BCryptPasswordEncoder().encode(setPasswordModel.getOldPassword()))){
            throw new AppException(HttpStatus.FORBIDDEN);
        }
        apiUser.setPassword(new BCryptPasswordEncoder().encode(setPasswordModel.getNewPassword()));
        apiUserDao.updateByIdSelective(apiUser);
        return apiUser;
    }

    @Override
    public ApiUser updateInfo(ApiUser apiUser){
        apiUserDao.updateByIdSelective(apiUser);
        return apiUser;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiUserDao.selectMapList(query);
        Long total = apiUserDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiUser selectById(Long id) {
        return apiUserDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiUserDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiUserDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiUser entity) {
        return apiUserDao.insert(entity);
    }

    @Override
    public int update(ApiUser entity) {
        return apiUserDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiUserDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商城用户.xlsx", data);
    }
}
