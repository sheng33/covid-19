package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.ClientConfigImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientConfigImgMapper {
    /***
     * 获得所有资源图片
     * @return
     */
    @Select("SELECT *  FROM client_Config_img")
    List<ClientConfigImg> getAllImg();

    /***
     * 删除图片
     * @param id 图片id
     * @return
     */
    @Delete("DELETE client_Config_img WHERE id=#{id}")
    int delImg(@Param("id")Integer id);

    /***
     * 插入图片记录
     * @param imgname 图片名称
     * @param imgUrl 图片URl
     * @param operator 操作者
     * @return
     */
    @Insert("INSERT INTO client_Config_img(imgname,imgUrl,operator) VALUES(#{imgname},#{imgUrl},#{operator})")
    int addSysConfig(@Param("imgname")String  imgname, @Param("imgUrl")String imgUrl,
                      @Param("operator")Integer operator);

    /***
     * 根据id查询图片记录
     * @param id
     * @return
     */
    @Select("SELECT * FROM client_Config_img WHERE id = #{id}")
    ClientConfigImg getImg(@Param("id")Integer id);
}
