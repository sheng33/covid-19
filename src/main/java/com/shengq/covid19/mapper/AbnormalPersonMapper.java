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
    AbnormalPerson findById(@Param("userid")String userid);

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
     * @return
     */
    @Update("UPDATE abnormalperson SET status = #{status},remark = #{remark} WHERE id = #{id}")
    int updateStatus(@Param("userid")String userid,@Param("status")String status,@Param("remark")String remark);


    /***
     * 插入新的异常人员记录
     * @param info
     */
    @Insert("INSERT INTO abnormalperson(id,userid,mobile,adress,nearestaddress,status,remark)" +
            "VALUES(null,#{info.userid},#{info.mobile},#{info.address},#{info.nearestaddress},#{info.status}")
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
