package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dao.SystemUser;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface SystemUserMapper {

    /***
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return SystemUser
     */
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SystemUser findById(@Param("id") Integer userId);

    /***
     * 根据用户名查询用户信息
     * @param username 用户登录名
     * @return SystemUser
     */
    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    SystemUser findByName(@Param("username") String username);

    /***
     * 根据用户号码查询用户信息
     * @param mobile
     * @return SystemUser
     */
    @Select("SELECT * FROM sys_user WHERE mobile = #{mobile}")
    SystemUser findByMobile(@Param("mobile") String mobile);

    /***
     * 新增用户
     * @param userid 用户id（系统自动生成）
     * @param name 用户登录名
     * @param password 密码
     * @param mobile 手机号
     * @param updateTime
     * @param permission 权限
     * @return 状态码
     */
    @Insert("INSERT INTO sys_user(id,username,password,mobile,status,permission,update_time,create_time)" +
            "VALUES(#{id},#{username},#{password},#{mobile},0,#{permission},#{update_time},#{create_time})")
    int addSystemUser(@Param("id") Integer userid, @Param("username") String name,
                      @Param("password") String password, @Param("mobile") String mobile,
                      @Param("permission") int permission,@Param("update_time") String updateTime,
                      @Param("create_time") String createTime);

    /***
     * 列出全部管理员账户
     * @return List<SystemUser>
     */
    @Select("SELECT * FROM sys_user")
    List<SystemUser> findAllSystemUser();

    /***
     * 修改用户信息（登录名或者密码)
     * @param userid 用户id
     * @param username 登录名
     * @param password 密码
     * @return 状态码
     */
    @Update("UPDATE sys_user SET username = #{username},password = #{password},update_time=${update_time}" +
            " WHERE id = #{id}")
    int updateSystemUserInfo(@Param("id")Integer userid,@Param("username") String username,
                             @Param("password") String password,@Param("update_time") String updateTime);

    /***
     * 删除管理员账户
     * @param userid 用户id
     * @param status 状态值 0 正常 -1 删除
     * @return
     */
    @Update("UPDATE sys_user SET status = #{status},update_time=${update_time} WHERE id = #{id}")
    int updateSystemUserStatus(@Param("id")Integer userid,@Param("status") int status,
                               @Param("update_time") String updateTime);

    /***
     * 修改管理员权限
     * @param userid 用户id
     * @param permission 权限
     * @return
     */
    @Update("UPDATE sys_user SET permission = #{permission},update_time=${update_time} WHERE id = #{id}")
    int updateSystemUserPermission(@Param("id")Integer userid,@Param("permission")int permission,
                                   @Param("update_time") String updateTime);
}
