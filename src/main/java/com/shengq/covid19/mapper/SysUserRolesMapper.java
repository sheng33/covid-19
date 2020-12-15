package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRolesMapper {
    @Select("SELECT * FROM sys_role WHERE id IN (SELECT roles_id from sys_user_roles WHERE sys_user_id = #{id})")
    List<SysRole> findByUserRoles(@Param("id") Integer id);

    @Select("SELECT * FROM sys_role")
    List<SysRole> getAllRoles();
}
