package com.shengq.covid19.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface DangerAddress {
    @Insert("Insert INTO dangeraddress" +
            "Values(id,province,city,area,status,createtime)")
    int insert(@Param("id")Integer id, @Param("province")String province,@Param("city")String city,
               @Param("area")String area,@Param("status")String status,@Param("createtime")String createtime);

}
