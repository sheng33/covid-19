package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.ClientUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientUserMapper {
    /***
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户dao类
     */
    @Select("SELECT * FROM client_user WHERE userid = #{userid}")
    ClientUser findById(@Param("userid") Integer userId);

    /***
     * 根据用户姓名查询用户信息
     * @param username 用户姓名
     * @return 用户dao类
     */
    @Select("SELECT * FROM client_user WHERE username = #{username}")
    ClientUser findByName(@Param("username")String username);

    /***
     * 根据用户姓名查询用户信息
     * @param mobile 用户手机号
     * @return 用户dao类
     */
    @Select("SELECT * FROM client_user WHERE mobile = #{mobile}")
    ClientUser findByMobile(@Param("mobile")String mobile);



    /***
     * 新增用户
     * @param userid
     * @param username
     * @param mobile
     * @param identity
     * @return
     */
    @Insert("INSERT INTO client_user(userid,username,mobile,identity,istouch,isarea,istemperature)" +
            " VALUES(#{userid},#{username},#{mobile},#{identity},0,0,0)")
    int addClinetUser(@Param("userid")Integer userid,@Param("username") String username,
                      @Param("mobile")String mobile,@Param("identity") String identity);

    /***
     * 查询所有用户
     * @return 返回List<用户dao列表>
     */
    @Select("SELECT * FROM client_user")
    List<ClientUser> findAllUser();

    /***
     * 更新用户信息
     * @param userId 用户id
     * @param username 用户姓名
     * @param mobile 用户手机号
     * @return 成功与否状态
     */
    @Update("UPDATE client_user SET username=#{username},mobile=#{mobile} WHERE userid=#{userid}")
    int updateClientUser(@Param("userid")Integer userId,@Param("username")String username,@Param("mobile")String mobile);


    /***
     * 更新用户状态信息
     * @param userId 用户id
     * @param istouch 是否与异常人员接触
     * @param isarea 是否处于异常地区
     * @param istemperature 是否温度异常
     * @return
     */
    @Update("UPDATE client_user SET istouch=#{istouch},isarea=#{isarea},istemperature=#{istemperature} WHERE userid=#{userid}")
    int updateClientUserStatus(@Param("userid")Integer userId,@Param("istouch")int istouch,
                               @Param("isarea")int isarea,@Param("istemperature")int istemperature);
    /***
     * 删除用户(根据用户id)
     * @param userId 用户id
     * @return 成功与否状态
     */
    @Delete("DELETE FROM client_user WHERE userid=#{userid}")
    int delClientUserById(@Param("userid")Integer userId);

    /***
     * 删除用户(根据手机号)
     * @param mobile 用户id
     * @return 成功与否状态
     */
    @Delete("DELETE FROM client_user WHERE mobile=#{mobile}")
    int delClientUserByMoblie(@Param("mobile")String mobile);

}
