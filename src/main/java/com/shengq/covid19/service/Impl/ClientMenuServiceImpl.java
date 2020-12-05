package com.shengq.covid19.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.shengq.covid19.dao.ClientMenu;
import com.shengq.covid19.dao.ConfigImg;
import com.shengq.covid19.mapper.ClientMenuMapper;
import com.shengq.covid19.service.ClientMenuService;
import com.shengq.covid19.service.ConfigImgService;
import com.shengq.covid19.vo.ClientMenuVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ClientMenuService")
public class ClientMenuServiceImpl implements ClientMenuService {
    final ClientMenuMapper clientMenuMapper;
    final ConfigImgService configImgService;
    final ConfigImgService imgService;
    public ClientMenuServiceImpl(ClientMenuMapper clientMenuMapper, ConfigImgService configImgService, ConfigImgService imgService) {
        this.clientMenuMapper = clientMenuMapper;
        this.configImgService = configImgService;
        this.imgService = imgService;
    }

    /***
     * 获取全部用户菜单
     * @return
     */
    @Override
    public List<ClientMenuVo> getAllMenu() {
        List<ClientMenu> menuList = clientMenuMapper.getAllMenu();
        List<ClientMenuVo> menuVoList = new ArrayList<>();
        menuList.forEach(menu->{
            ConfigImg configImg = configImgService.getImageById(menu.getMenu_imgId());
            menuVoList.add(new ClientMenuVo(menu.getId(),menu.getMenu_name(),
                    menu.getMenu_url(),menu.getMenu_imgId(),configImg.getImgname(),
                    configImg.getImgUrl(),menu.getStatus(),menu.getAuth()));
        });
        return menuVoList;
    }

    /***
     * 获取全部用户菜单_根据记录statu值
     * @return
     * @param status
     */
    @Override
    public List<ClientMenuVo> getAllMenuByStatus(Integer status) {
        List<ClientMenu> menuList = clientMenuMapper.getAllMenuByStatus(status);
        List<ClientMenuVo> menuVoList = new ArrayList<>();
        menuList.forEach(menu->{
            ConfigImg configImg = configImgService.getImageById(menu.getMenu_imgId());
            menuVoList.add(new ClientMenuVo(menu.getId(),menu.getMenu_name(),
                    menu.getMenu_url(),menu.getMenu_imgId(),configImg.getImgname(),
                    configImg.getImgUrl(),menu.getStatus(),menu.getAuth()));
        });
        return menuVoList;

    }

    /***
     *  获取全部用户菜单_根据记录auth值
     * @return
     * @param auth
     */
    @Override
    public List<ClientMenuVo> getAllMenuByAuth(Integer auth) {
        List<ClientMenu> menuList = clientMenuMapper.getAllMenuByAuth(auth);
        List<ClientMenuVo> menuVoList = new ArrayList<>();
        menuList.forEach(menu->{
            ConfigImg configImg = configImgService.getImageById(menu.getMenu_imgId());
            menuVoList.add(new ClientMenuVo(menu.getId(),menu.getMenu_name(),
                    menu.getMenu_url(),menu.getMenu_imgId(),configImg.getImgname(),
                    configImg.getImgUrl(),menu.getStatus(),menu.getAuth()));
        });
        return menuVoList;
    }

    /***
     * 获取状态true并且权限足够的菜单
     * @param status
     * @param auth
     * @return
     */
    @Override
    public List<ClientMenuVo> getAllMenuByStatus_Auth(Integer status, Integer auth) {
        List<ClientMenu> menuList = clientMenuMapper.getAllMenuByStatus_Auth(status,auth);
        List<ClientMenuVo> menuVoList = new ArrayList<>();
        menuList.forEach(menu->{
            ConfigImg configImg = configImgService.getImageById(menu.getMenu_imgId());
            menuVoList.add(new ClientMenuVo(menu.getId(),menu.getMenu_name(),
                    menu.getMenu_url(),menu.getMenu_imgId(),configImg.getImgname(),
                    configImg.getImgUrl(),null,null));
        });
        return menuVoList;
    }

    /***
     * 新增记录
     * @return
     * @param json
     */
    @Override
    public int addMenu(JsonNode json) {
        String menuName = json.get("menuName").asText();
        String menuUrl = json.get("menuUrl").asText();
        Integer status = json.get("status").asInt();
        Integer auth = json.get("auth").asInt();
        String imgUrl = json.get("menu_imgUrl").asText();
        String imgName = json.get("menu_imgName").asText();
        System.out.println("========="+status);
        System.out.println("========="+auth);
        ConfigImg is_configImg = imgService.getImgByimgName(imgName);
        if (is_configImg!=null){
            return -2;
        }
        int code = imgService.addImage(imgName,imgUrl,ConfigImg.Type_ClientMenu);
        if (code == 1){
            ConfigImg configImg = imgService.getImgByimgName(imgName);
            code =  clientMenuMapper.addMenu(menuName,menuUrl,configImg.getId(),status,auth);
            if (code == 1){
                return code;
            }else {
                return -1;
            }
        }else {
            return -1;
        }

    }

    /***
     * 删除记录
     * @param id 菜单id
     * @return
     */
    @Override
    public int delMenu(Integer id) {
        return clientMenuMapper.delMenu(id);
    }

    /***
     * 根据id获取信息
     * @param id
     * @return
     */
    @Override
    public ClientMenuVo getMenu(Integer id) {
        ClientMenu menu = clientMenuMapper.getMenu(id);
        ConfigImg configImg = configImgService.getImageById(menu.getMenu_imgId());
        return new ClientMenuVo(menu.getId(),menu.getMenu_name(),
                menu.getMenu_url(),menu.getMenu_imgId(),configImg.getImgname(),
                configImg.getImgUrl(),menu.getStatus(),menu.getAuth());
    }
}
