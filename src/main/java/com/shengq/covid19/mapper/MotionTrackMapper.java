package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.MotionTrack;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MotionTrackMapper {
    /***
     * 根据用户id查询信息
     * @param userid
     * @return
     */
    @Select("SELECT * FROM motiontrack WHERE userid =#{userid} ")
    List<MotionTrack> findById(@Param("userid")String userid);

    /***
     * 返回所有行动轨迹
     * @return
     */
    @Select("SELECT * FROM motiontrack")
    List<MotionTrack> findAllMotionTrack();

    /***
     * 插入新记录
     * @param info
     */
    @Insert("INSERT INTO motionTrack(id,userid,transportation," +
            "traveltime,serialNumber,startPoint,endPoint)" +
            "Values(0,#{info.userid},#{info.transportation}," +
            "#{info.traveltime},#{info.serialNumber},#{info.startPoint},#{info.endPoint})")
    void Insert(@Param("info")MotionTrack info);

}
