package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.AbnormalPerson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DailyReport {
    /***
     * 根据用户id查询
     * @param userid
     * @return DailyReport
     */
    @Select("SELECT * FROM dailyreport WHERE userid = #{userid}")
    DailyReport findById(@Param("userid")Integer userid);

    /***
     * 插入新的异常人员记录
     * @param info
     */
    @Insert("INSERT INTO dailyreport(userid,address,temperature,createtime,remark)" +
            "VALUES(#{info.userid},#{info.address},#{info.temperature},#{info.createtime}," +
            "#{info.remark})")
    void insert(@Param("info") DailyReport info);

    /***
     * 返回所有异常人员信息
     * @return
     */
    @Select("SELECT * FROM dailyreport")
    List<DailyReport> findAlldailyreport();



}
