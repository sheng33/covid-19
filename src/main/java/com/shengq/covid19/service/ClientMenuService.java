package com.shengq.covid19.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.shengq.covid19.dao.ClientMenu;
import com.shengq.covid19.vo.ClientMenuVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientMenuService {
    /***
     * 获取全部用户菜单
     * @return
     */
    List<ClientMenuVo> getAllMenu();

    /***
     * 获取全部用户菜单_根据记录statu值
     * @return
     */
    List<ClientMenuVo> getAllMenuByStatus(@Param("status")Integer status);

    /***
     *  获取全部用户菜单_根据记录auth值
     * @return
     */
    List<ClientMenuVo> getAllMenuByAuth(@Param("auth")Integer auth);


    List<ClientMenuVo> getAllMenuByStatus_Auth(@Param("status")Integer status, @Param("auth")Integer auth);

    /***
     * 新增记录
     * @return
     */
    int addMenu(JsonNode json);

    /***
     * 删除记录
     * @param id 菜单id
     * @return
     */
    int delMenu(@Param("id")Integer id);

    /***
     * 根据id获取信息
     * @param id
     * @return
     */
    ClientMenuVo getMenu(@Param("id")Integer id);
}
