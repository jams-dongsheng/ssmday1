package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll() throws Exception;
    public void save(UserInfo userInfo) throws Exception;
    public UserInfo findById(String id)throws Exception;

    List<Role> findOtherRoles(String userId);

    public void addRoleToUser(String userId, String[] roleIds);
}
