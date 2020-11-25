package com.shengq.covid19.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dto.ClientUserDTO;
import com.shengq.covid19.mapper.ClientUserMapper;
import com.shengq.covid19.service.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class ClientUserServiceImpl implements ClientUserService {
    @Autowired
    ClientUserMapper userMapper;
    /***
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户dao类
     */
    @Override
    public ClientUserDTO findById(String userId) {
        ClientUser clientUser = userMapper.findById(userId);

        if (clientUser == null) return null;

        ClientUserDTO userDTO = new ClientUserDTO(clientUser.getUserid(),clientUser.getUsername(),
                clientUser.getMobile(),clientUser.getIstouch(),clientUser.getIsarea(),clientUser.getIstemperature());
        return userDTO;
    }

    @Override
    public ClientUserDTO findByOpenId(String openid) {
        ClientUser clientUser = userMapper.findByOpenId(openid);

        if (clientUser == null) return null;

        ClientUserDTO userDTO = new ClientUserDTO(clientUser.getUserid(),clientUser.getUsername(),
                clientUser.getMobile(),clientUser.getIstouch(),clientUser.getIsarea(),clientUser.getIstemperature());
        return userDTO;
    }

    /***
     * 根据用户姓名查询用户信息
     * @param username 用户姓名
     * @return 用户dao类
     */
    @Override
    public ClientUserDTO findByName(String username) {
        ClientUser clientUser = userMapper.findByName(username);

        if (clientUser == null) return null;

        ClientUserDTO userDTO = new ClientUserDTO(clientUser.getUserid(),clientUser.getUsername(),
                clientUser.getMobile(),clientUser.getIstouch(),clientUser.getIsarea(),clientUser.getIstemperature());
        return userDTO;
    }

    /***
     * 根据用户姓名查询用户信息
     * @param mobile 用户手机号
     * @return 用户dao类
     */
    @Override
    public ClientUserDTO findByMobile(String mobile) {
        ClientUser clientUser = userMapper.findByMobile(mobile);

        if (clientUser == null) return null;

        ClientUserDTO userDTO = new ClientUserDTO(clientUser.getUserid(),clientUser.getUsername(),
                clientUser.getMobile(),clientUser.getIstouch(),clientUser.getIsarea(),clientUser.getIstemperature());
        return userDTO;
    }

    /***
     * 新增用户
     * @param user 用户dao类
     * @return 成功与否状态
     */
    @Override
    public int addClinetUser(ClientUser user) {
        return userMapper.addClinetUser(user.getUserid(),user.getUsername(),user.getMobile(),
                user.getMobile(), DateUtil.date(Calendar.getInstance()).toString(),user.getSessionKey(),user.getOpenid());
    }

    /***
     * 查询所有用户
     * @return 返回List<用户dao列表>
     */
    @Override
    public List<ClientUserDTO> findAllUser() {
        List<ClientUser> userList = userMapper.findAllUser();
        List<ClientUserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(new ClientUserDTO(user.getUserid(),user.getUsername(),
                user.getMobile(),user.getIstouch(),user.getIsarea(),user.getIstemperature())));
        return userDTOList;
    }

    /***
     * 更新用户信息
     * @param clientUserDTO 用户Dto类
     * @return
     */
    @Override
    public int updateClientUser(ClientUserDTO clientUserDTO) {
        return userMapper.updateClientUser(clientUserDTO.getUserid(),clientUserDTO.getUsername(),clientUserDTO.getMobile());
    }

    /***
     * 删除用户(根据用户id)
     * @param userId 用户id
     * @return 成功与否状态
     */
    @Override
    public int delClientUserById(String userId) {

        return userMapper.delClientUserById(userId);
    }

    /***
     * 删除用户(根据手机号)
     * @param mobile 用户id
     * @return 成功与否状态
     */
    @Override
    public int delClientUserByMobile(String mobile) {
        return userMapper.delClientUserByMoblie(mobile);
    }

    /***
     * 更新用户状态信息
     * @param clientUserDTO 用户DTO类
     * @return 成功与否状态
     */
    @Override
    public int updateClientUserStatus(ClientUserDTO clientUserDTO) {
        return userMapper.updateClientUserStatus(clientUserDTO.getUserid(),clientUserDTO.getIstouch(),
                clientUserDTO.getIsarea(),clientUserDTO.getIstemperature());
    }

    @Override
    public int updateClinetUserSession(String openId, String sessionId) {
        return userMapper.updateClinetUserSession(openId, sessionId);
    }


}
