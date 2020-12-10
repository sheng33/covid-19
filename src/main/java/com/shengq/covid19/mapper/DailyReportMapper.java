package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.DailyReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DailyReportMapper {
    /***
     * 根据用户id查询
     * @param userid
     * @return DailyReport
     */
    @Select("SELECT * FROM dailyreport WHERE userid = #{userid}")
    List<DailyReport> findById(@Param("userid")String userid);

    /***
     * 插入新的日常填报记录
     * @param info
     * @return
     */
    @Insert("INSERT INTO dailyreport(userid,address,temperature,remark,status)" +
            "VALUES(#{info.userid},#{info.address},#{info.temperature}," +
            "#{info.remark},#{info.status})")
    int insert(@Param("info") DailyReport info);

    /***
     * 返回所有异常人员信息
     * @return
     */
    @Select("SELECT * FROM dailyreport")
    List<DailyReport> findAlldailyreport();



}
