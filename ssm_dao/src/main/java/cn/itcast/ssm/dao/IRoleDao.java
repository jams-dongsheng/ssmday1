package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "cn.itcast.ssm.dao.IPermissionDao.findById")
            )
    })

    public List<Role> findByUserId(String userId);
    //查询角色
    @Select("select * from role")
    public List<Role> findAll();
    //添加角色
    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role)throws Exception;
    @Select("select * from role where id = #{roleId}")
    public Role findById(String roleId);
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId);
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    public void addRoleToPermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

}
