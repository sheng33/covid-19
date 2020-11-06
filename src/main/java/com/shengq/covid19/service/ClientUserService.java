package com.shengq.covid19.service;

import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dto.ClientUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface ClientUserService {
    /***
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户dao类
     */
    ClientUserDTO findById(@Param("userid") Integer userId);

    /***
     * 根据用户姓名查询用户信息
     * @param username 用户姓名
     * @return 用户dao类
     */
    ClientUserDTO findByName(@Param("username")String username);

    /***
     * 根据用户姓名查询用户信息
     * @param moblie 用户手机号
     * @return 用户dao类
     */
    ClientUserDTO findByMobile(@Param("mobile")String moblie);

    /***
     * 新增用户
     * @param user 用户dao类
     * @return 成功与否状态
     */
    int addClinetUser(ClientUser user);

    /***
     * 查询所有用户
     * @return 返回List<用户dao列表>
     */
    List<ClientUserDTO> findAllUser();

    /***
     * 更新用户信息
     * @param clientUserDTO 用户Dto类
     * @return
     */
    int updateClientUser(ClientUserDTO clientUserDTO);

    /***
     * 删除用户(根据用户id)
     * @param userId 用户id
     * @return 成功与否状态
     */
    int delClientUserById(Integer userId);

    /***
     * 删除用户(根据手机号)
     * @param moblie 用户id
     * @return 成功与否状态
     */
    int delClientUserByMobile(String moblie);

    /***
     * 更新用户状态信息
     * @param clientUserDTO 用户DTO类
     * @return 成功与否状态
     */
    int updateClientUserStatus(ClientUserDTO clientUserDTO);

}
