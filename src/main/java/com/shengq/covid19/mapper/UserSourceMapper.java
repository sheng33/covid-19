package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.UserSource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserSourceMapper {

    @Select("SELECT * FROM userSource WHERE userid = #{userid}")
    UserSource findById(@Param("userid")String userid);

    @Insert("INSERT SET userSource(userid,platform)" +
            "VALUES(#{userSource.userid},#{userSource.platform})")
    void insert(@Param("userSource")UserSource userSource);
}
