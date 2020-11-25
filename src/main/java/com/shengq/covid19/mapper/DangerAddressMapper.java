package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.DangerAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DangerAddressMapper {
    /***
     * 插入异常地址
     * @param id
     * @param province
     * @param city
     * @param area
     * @param status
     * @param createtime
     * @return
     */
    @Insert("Insert INTO dangeraddress" +
            "Values(id,province,city,area,status,createtime)")
    int insert(@Param("id")String id, @Param("province")String province,@Param("city")String city,
               @Param("area")String area,@Param("status")String status,@Param("createtime")String createtime);


    /***
     * 根据省份城市查询危险区域
     * @param province
     * @param city
     * @return
     */
    @Select("SELECT * FROM dangeraddress WHERE province = #{province} AND city = #{city}")
    DangerAddress getDangerAddress(@Param("province")String province,@Param("city")String city);




}
