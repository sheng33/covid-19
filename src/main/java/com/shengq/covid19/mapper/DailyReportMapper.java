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
    List<DailyReport> findById(@Param("userid")Integer userid);

    /***
     * 插入新的日常填报记录
     * @param info
     * @return
     */
    @Insert("INSERT INTO dailyreport(id,userid,address,temperature,createtime,remark)" +
            "VALUES(0,#{info.userid},#{info.address},#{info.temperature},#{info.createtime}," +
            "#{info.remark})")
    int insert(@Param("info") DailyReport info);

    /***
     * 返回所有异常人员信息
     * @return
     */
    @Select("SELECT * FROM dailyreport")
    List<DailyReport> findAlldailyreport();



}
