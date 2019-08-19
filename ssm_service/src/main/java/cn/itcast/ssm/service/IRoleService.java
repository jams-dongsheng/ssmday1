package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();
    public void save(Role role) throws Exception;

    public Role findById(String roleId);

    List<Permission> findOtherPermission(String roleId);

    public void addRoleToPermission(String roleId, String[] permissionIds);
}
