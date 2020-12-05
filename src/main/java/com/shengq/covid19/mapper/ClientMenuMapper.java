package com.shengq.covid19.mapper;

import com.shengq.covid19.dao.ClientMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientMenuMapper {
    /***
     * 获取全部用户菜单
     * @return
     */
    @Select("SELECT * FROM client_menu")
    List<ClientMenu> getAllMenu();

    /***
     * 获取全部用户菜单_根据记录statu值
     * @param status
     * @return
     */
    @Select("SELECT * FROM client_menu WHERE status=#{status}")
    List<ClientMenu> getAllMenuByStatus(@Param("status")Integer status);

    /***
     *  获取全部用户菜单_根据记录auth值
     * @param auth
     * @return
     */
    @Select("SELECT * FROM client_menu WHERE auth >= #{auth}")
    List<ClientMenu> getAllMenuByAuth(@Param("auth")Integer auth);

    @Select("SELECT * FROM client_menu WHERE status=#{status} and auth >= #{auth}")
    List<ClientMenu> getAllMenuByStatus_Auth(@Param("status")Integer status,@Param("auth")Integer auth);
    /***
     * 新增记录
     * @param menu_name 菜单名
     * @param menu_url 菜单url
     * @param menu_imgId 菜单图片id
     * @param status 状态值
     * @param auth 所需权限
     * @return
     */
    @Insert("INSERT INTO client_menu(menu_name,menu_url,menu_imgId,status,auth)" +
            "Values(#{menu_name},#{menu_url},#{menu_imgId},#{status},#{auth})")
    int addMenu(@Param("menu_name")String menu_name,@Param("menu_url")String menu_url,@Param("menu_imgId")Integer menu_imgId,
                @Param("status")Integer status,@Param("auth")Integer auth);

    /***
     * 删除记录
     * @param id 菜单id
     * @return
     */
    @Delete("DELETE client_menu WHERE id = #{id}")
    int delMenu(@Param("id")Integer id);

    /***
     * 根据id获取信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM client_menu WHERE id = #{id}")
    ClientMenu getMenu(@Param("id")Integer id);

}
