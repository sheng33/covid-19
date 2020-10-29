package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.SystemUser;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.mapper.SystemUserMapper;
import com.shengq.covid19.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    SystemUserMapper userMapper;
    /***
     * 获取用户信息（用户id）
     * @param userid 用户id
     * @return 系统用户DTO信息
     */
    @Override
    public SystemUserDTO getUserInfo(Integer userid) {
        SystemUser systemUser = userMapper.findById(userid);
        SystemUserDTO systemUserDTO = new SystemUserDTO(systemUser.getUserid(),systemUser.getUsername(),
                systemUser.getPassword(),systemUser.getMobile(),systemUser.getStatus(),systemUser.getPermission());
        return systemUserDTO;
    }

    /***
     * 获取用户信息（手机号）
     * @param mobile 手机号
     * @return 系统用户DTO信息
     */
    @Override
    public SystemUserDTO getUserInfo(String mobile) {
        SystemUser systemUser = userMapper.findByMobile(mobile);
        SystemUserDTO systemUserDTO = new SystemUserDTO(systemUser.getUserid(),systemUser.getUsername(),
                systemUser.getPassword(),systemUser.getMobile(),systemUser.getStatus(),systemUser.getPermission());
        return systemUserDTO;
    }

    /***
     * 得到所有系统用户信息列表
     * @return list集合
     */
    @Override
    public List<SystemUserDTO> getAllUser() {
        List<SystemUser> userList = userMapper.findAllSystemUser();
        List<SystemUserDTO> userDTOS = new ArrayList<>();
        userList.forEach(systemUser -> userDTOS.add(new SystemUserDTO(systemUser.getUserid(),systemUser.getUsername(),
                systemUser.getPassword(),systemUser.getMobile(),systemUser.getStatus(),systemUser.getPermission())));
        return userDTOS;
    }

    /***
     * 更新用户个人信息（用户名或者密码）
     * @param userid 用户id
     * @param username 用户名
     * @param password 密码
     * @return 状态码
     */
    @Override
    public int updateSystemUserInfo(Integer userid, String username, String password) {
        return userMapper.updateSystemUserInfo(userid,username,password);
    }

    /***
     * 删除系统用户
     * @param userid 用户id
     * @return 状态码
     */
    @Override
    public int delSystemUser(Integer userid) {
        return userMapper.updateSystemUserStatus(userid,-1);
    }

    /***
     * 更新用户权限
     * @param userid 用户id
     * @return 状态码
     */
    @Override
    public int updateUserPermission(Integer userid) {
        //TODO:2020.10.29 权限分配逻辑还未完成
        return userMapper.updateSystemUserPermission(userid,0);
    }
}
