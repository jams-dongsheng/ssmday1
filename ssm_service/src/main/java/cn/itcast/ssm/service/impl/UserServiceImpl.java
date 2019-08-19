package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IUserDao;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.IUserService;
import cn.itcast.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
             userInfo = dao.findByUserName(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
//userInfo.getStatus() == 0 ? false : true,true,true,true
        User user =  new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true,true,true,true,getAuthority(userInfo.getRoles()));



        return user;
    }
    //List<Role> roles
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
//用户查询
    @Override
    public List<UserInfo> findAll() throws Exception {
        return dao.findAll();
    }
    //添加用户

    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(BCryptPasswordEncoderUtils.BCryptPasswordEncoder(userInfo.getPassword()));
        dao.save(userInfo);
    }
    //详情查询

    @Override
    public UserInfo findById(String id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return dao.findOtherRoles(userId);
    }
    //给用户添加角色
    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            dao.addRoleToUser(userId,roleId);
        }
    }
}
