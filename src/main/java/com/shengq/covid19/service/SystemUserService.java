package com.shengq.covid19.service;

import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.vo.SystemUserVo;

import java.util.List;

public interface SystemUserService {
    /***
     * 获取用户信息（用户id）
     * @param userid 用户id
     * @return 系统用户DTO信息
     */
    SystemUserDTO getUserInfo(Integer userid);

    /***
     * 获取用户信息（手机号）
     * @param mobile 手机号
     * @return 系统用户DTO信息
     */
    SystemUserDTO getUserInfo(String mobile);

    SystemUserDTO getUserInfoByName(String name);

    /***
     * 得到所有系统用户信息列表
     * @return list集合
     */
    List<SystemUserDTO> getAllUser();

    /***
     * 更新用户个人信息（用户名或者密码）
     * @param userid 用户id
     * @param username 用户名
     * @param password 密码
     * @return 状态码
     */
    int updateSystemUserInfo(Integer userid,String username,String password);

    int registerSystemUser(SystemUserVo systemUserVo);

    /***
     * 删除系统用户
     * @param userid 用户id
     * @return 状态码
     */
    int delSystemUser(Integer userid);

    /***
     * 更新用户权限
     * @param userid 用户id
     * @return 状态码
     */
    int updateUserPermission(Integer userid);
}