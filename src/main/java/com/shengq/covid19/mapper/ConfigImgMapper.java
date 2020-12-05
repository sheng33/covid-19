package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.ConfigImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConfigImgMapper {
    /***
     * 获得所有资源图片
     * @return
     */
    @Select("SELECT *  FROM config_img")
    List<ConfigImg> findAllImg();

    /***
     * 获取所有资源图片来自图片类型
     * @param type 图片类型
     * @return
     */
    @Select("SELECT * FROM config_img WHERE type = #{type}")
    List<ConfigImg> findAllImgByType(@Param("type") Integer type);



    /***
     * 删除图片
     * @param id 图片id
     * @return
     */
    @Delete("DELETE config_img WHERE id=#{id}")
    int delImg(@Param("id")Integer id);

    /***
     * 插入图片记录
     * @param imgname 图片名称
     * @param imgUrl 图片URl
     * @param operator 操作者
     * @return
     */
    @Insert("INSERT INTO config_img(imgname,imgUrl,operator,type) VALUES(#{imgname},#{imgUrl},#{operator},#{type})")
    int addSysConfig(@Param("imgname")String  imgname, @Param("imgUrl")String imgUrl,
                      @Param("operator")Integer operator,@Param("type")Integer type);

    /***
     * 根据id查询图片记录
     * @param id
     * @return
     */
    @Select("SELECT * FROM config_img WHERE id = #{id}")
    ConfigImg findImgById(@Param("id")Integer id);
    /***
     * 根据id查询图片记录
     * @param imgname
     * @return
     */
    @Select("SELECT * FROM config_img WHERE imgname = #{imgname}")
    ConfigImg findImgByimgName(@Param("imgname")String imgName);
}
