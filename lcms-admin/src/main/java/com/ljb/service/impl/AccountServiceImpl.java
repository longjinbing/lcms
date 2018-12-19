package com.ljb.service.impl;

import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.MenuDao;
import com.ljb.dao.RoleDao;
import com.ljb.dao.TokenDao;
import com.ljb.dao.UserDao;
import com.ljb.entity.User;
import com.ljb.security.DefaultGrantedAuthority;
import com.ljb.security.IdentityUtils;
import com.ljb.service.AccountService;
import com.ljb.utils.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/11<br>
 * 描述: <br>
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private TokenDao  tokenDao;

    public Set<GrantedAuthority> authorities(Long id){
        Set<GrantedAuthority> result=new HashSet<>();
        List<String> roles=roleDao.roleList(id);
        Iterator iterator =roles.iterator();
        while (iterator.hasNext()){
            String role=(String)iterator.next();
            DefaultGrantedAuthority simpleGrantedAuthority=new DefaultGrantedAuthority(role);
            result.add(simpleGrantedAuthority);
        }
        return result;
    }

    @Override
    public void logout() {
        if(IdentityUtils.isAuthenticated()){
             tokenDao.deleteById(IdentityUtils.getUserId());
        }
    }

    @Override
    public User findByUsername(String username) {
        List<User> list=userDao.selectByMap(Condition.build().eq("username", username));
        if(list.size()==1)
            return list.get(0);
        return null;
    }
    @Override
    public User findByCode(String number) {
        List<User> list=userDao.selectByMap(Condition.build().eq("number", number));
        if(list.size()==1)
            return list.get(0);
        return null;
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public List<String> roles(Long id){
        return roleDao.roleList(id);
    }

    @Override
    public List<String> actions(Long id) {
        return menuDao.actionList(id);
    }

    @Override
    public List<String> allActions(){
        return menuDao.allActions();
    }
}
