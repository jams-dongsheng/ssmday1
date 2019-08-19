package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id} )")
    public Permission findById(String id);
    //查询
    @Select("select * from permission")
    public List<Permission> findAll()throws Exception;
    //添加
    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);
}
