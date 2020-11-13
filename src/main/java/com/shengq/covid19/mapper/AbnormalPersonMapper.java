package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.AbnormalPerson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AbnormalPersonMapper {
    /***
     * 根据用户id查询
     * @param userid
     * @return abnormalperson类
     */
    @Select("SELECT * FROM abnormalperson WHERE userid = #{userid}")
    AbnormalPerson findById(@Param("userid")Integer userid);

    /***
     * 根据用户手机号查询
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM abnormalperson WHERE mobile = #{mobile}")
    AbnormalPerson findByMobile(@Param("mobile")String mobile);

    /***
     * 更新记录状态
     * @param userid
     * @param status
     */
    @Update("UPDATE abnormalperson SET status = #{status} WHERE userid = #{userid}")
    void updateStatus(@Param("userid")Integer userid,@Param("status")String status);


    /***
     * 插入新的异常人员记录
     * @param info
     */
    @Insert("INSERT INTO abnormalperson(userid,mobile,adress,nearestaddress,status,createtime)" +
            "VALUES(#{info.userid},#{info.mobile},#{info.address},#{info.nearestaddress},#{info.status}," +
            "#{info.createtime})")
    void insert(@Param("info") AbnormalPerson info);

    /***
     * 返回所有异常人员信息
     * @return
     */
    @Select("SELECT * FROM abnormalperson")
    List<AbnormalPerson> findAllAbnormalPerson();

    /***
     * 根据状态返回所有符合条件的异常人员信息
     * @param status
     * @return
     */
    @Select("SELECT * FROM abnormalperson WHERE status = #{status}")
    List<AbnormalPerson> findAllAbnormalPersonByStatus(@Param("status")String status);



}
