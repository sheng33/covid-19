package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.SystemUser;
import com.shengq.covid19.dto.SystemUserDTO;
import com.shengq.covid19.exception.GlobalException;
import com.shengq.covid19.exception.NotFoundException;
import com.shengq.covid19.mapper.SystemUserMapper;
import com.shengq.covid19.service.SystemUserService;
import com.shengq.covid19.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService{
    @Autowired
    SystemUserMapper userMapper;
    @Autowired
    MyPasswordEncoder myPasswordEncoder;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
    /***
     * 获取用户信息（用户id）
     * @param userid 用户id
     * @return 系统用户DTO信息
     */
    @Override
    public SystemUserDTO getUserInfo(Integer userid) {
        SystemUser systemUser = userMapper.findById(userid);
        if (systemUser == null){
            return null;
        }
        SystemUserDTO systemUserDTO = new SystemUserDTO(systemUser.getId(),systemUser.getName(),
                systemUser.getUsername(),systemUser.getPassword(),systemUser.getMobile(),
                systemUser.getStatus(),systemUser.getPermission());
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
        if (systemUser == null){
            return null;
        }
        return new SystemUserDTO(systemUser.getId(),systemUser.getName(),systemUser.getUsername(),
                systemUser.getPassword(),systemUser.getMobile(),systemUser.getStatus(),systemUser.getPermission());
    }

    @Override
    public SystemUserDTO getUserInfoByName(String name) throws GlobalException {
        SystemUser systemUser = userMapper.findByName(name);

        if (systemUser == null){
            throw new NotFoundException("找不到用户名为【"+name+"】的用户");
        }

        return new SystemUserDTO(systemUser.getId(),systemUser.getName(),systemUser.getUsername(),
                systemUser.getPassword(),systemUser.getMobile(),systemUser.getStatus(),systemUser.getPermission());
    }

    /***
     * 得到所有系统用户信息列表
     * @return list集合
     */
    @Override
    public List<SystemUserDTO> getAllUser() {
        List<SystemUser> userList = userMapper.findAllSystemUser();
        List<SystemUserDTO> systemUserDtos = new ArrayList<>();
        userList.forEach(systemUser -> systemUserDtos.add(new SystemUserDTO(systemUser.getId(),
                systemUser.getName(),systemUser.getUsername(),systemUser.getPassword(),
                systemUser.getMobile(),systemUser.getStatus(),systemUser.getPermission())));
        return systemUserDtos;
    }

    /***
     * 更新用户个人信息（用户名或者密码）
     * @param userid 用户id
     * @param username 用户名
     * @param oldPassword 密码
     * @return 状态码
     */
    @Override
    public int updateSystemUserInfo(Integer userid,String name, String username, String oldPassword,String newPassword) {
        Calendar calendar= Calendar.getInstance();
        SystemUser systemUser = userMapper.findById(userid);
        boolean pd = myPasswordEncoder.matches(newPassword,oldPassword);
        System.out.println("test");
        if (pd){
            return userMapper.updateSystemUserInfo(userid,name,username,newPassword,dateFormat.format(calendar.getTime()));
        }
        return -1;
    }



    /***
     * 注册系统用户
     * @param systemUserDTO
     * @return
     */
    @Override
    public int registerSystemUser(SystemUserDTO systemUserDTO) {
        Calendar calendar= Calendar.getInstance();
        SystemUser systemUser = userMapper.findByName(systemUserDTO.getUsername());
        if (systemUser != null){
            return -1;
        }

        String password = myPasswordEncoder.encode(systemUserDTO.getPassword());
        return userMapper.addSystemUser(0,systemUserDTO.getName(),systemUserDTO.getUsername(),
                password,systemUserDTO.getMobile(),systemUserDTO.getPermission());
    }

    /***
     * 删除系统用户
     * @param userid 用户id
     * @return 状态码
     */
    @Override
    public int delSystemUser(Integer userid) {
        Calendar calendar= Calendar.getInstance();
        return userMapper.updateSystemUserStatus(userid,-1,dateFormat.format(calendar.getTime()));
    }

    /***
     * 更新用户权限
     * @param userid 用户id
     * @return 状态码
     */
    @Override
    public int updateUserPermission(Integer userid) {
        //TODO:2020.10.29 权限分配逻辑还未完成
        Calendar calendar= Calendar.getInstance();
        return userMapper.updateSystemUserPermission(userid,0,dateFormat.format(calendar.getTime()));
    }


}
