package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IRoleDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao dao;
    @Override
    public List<Role> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        dao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return dao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return dao.findOtherPermission(roleId);
    }
//添加角色资源权限
    @Override
    public void addRoleToPermission(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            dao.addRoleToPermission(roleId,permissionId);
        }
    }
}
