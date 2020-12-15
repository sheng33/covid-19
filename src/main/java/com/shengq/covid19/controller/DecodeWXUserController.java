package com.shengq.covid19.controller;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.shengq.covid19.config.Result;
import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dto.ClientUserDTO;
import com.shengq.covid19.exception.GlobalException;
import com.shengq.covid19.mapper.ClientUserMapper;
import com.shengq.covid19.service.ClientUserService;
import com.shengq.covid19.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = "微信交互")
public class DecodeWXUserController {
    @Autowired
    ClientUserService    clientUserService;
    @Autowired
    ClientUserMapper clientUserMapper;
    /**
     * 解密用户敏感数据
     * @param code     用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return
     */
    @RequestMapping(value = "/decode",method = RequestMethod.GET)
    public Result<?> decodeUser(@RequestParam("code") String code) throws GlobalException {
    // 生成token
//            后端获取code，换取openid 和  session_key
        // 登录凭证不能为空
        if (code == null || code.length()==0){
            return  ResultUtil.error(0,"code 不能为空");
        }
        // 小程序唯一标识（在微信小程序管理后台获取）
        String wxspAppid = "wxf33e9a68af8030a7";
        // 小程序的 app secret（在微信小程序管理后台获取）
        String wxspSecret = "dd8c45fbfd5ca86f4c9e19844f403227";
        // 授权 （必填）
        String grant_type = "authorization_code";
        // 1. 向微信服务器  使用登录凭证  code 获取 session_key 和 openid
        // 请求参数
        String params = "appid="+wxspAppid+"&secret="+wxspSecret+"&js_code="+code+"&grant_type="+grant_type;
        //发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?"+params;
        String str = HttpRequest.get(url).contentType(HttpRequest.CONTENT_TYPE_JSON).acceptJson().body();
        // 解析相应内容（转换成json对象）
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = mapper.readTree(str);
        } catch (JsonProcessingException e) {
            throw new GlobalException("解析错误");
        }
        // 用户的唯一表示 （openid）
//            String openid = (String) json.get("openid");
        String openid = json.findValue("openid").asText();
        String sessionKey = json.findValue("session_key").asText();
        System.out.println("Session::::"+sessionKey);
        // 判断 openid是否存在于数据库中，如果存在直接根据openid查询用户的信息，返回信息和token
        // 如果不存在 openid，则存到数据库中，相当于插入了用户，但是其它信息为空
        ClientUserDTO clientUserDto = clientUserService.findByOpenId(openid);
        if (clientUserDto==null){
            // 将openid、token存入数据库
            ClientUser clientUser = new ClientUser();
            String simpleUUID = IdUtil.objectId();
            clientUser.setUserid(simpleUUID);
            clientUser.setOpenid(openid);
            clientUser.setSessionKey(sessionKey);
            int oplog = clientUserService.addClinetUser(clientUser);
            ClientUserDTO newCreate = clientUserService.findByOpenId(openid) ;
            // 依然查询用户信息，返回信息为空和token
            if (oplog == 1){
                return ResultUtil.success("新增用户成功",newCreate);
            }else{
                throw new GlobalException("新增用户失败");
            }
        }else {
            ClientUser clientUser = clientUserMapper.findByOpenId(openid);
            if (sessionKey.equals(clientUser.getSessionKey())){
                return ResultUtil.success("查询成功",clientUserDto);
            }else {
                clientUserService.updateClinetUserSession(openid,sessionKey);
                return ResultUtil.success("更新用户Session成功",clientUserDto);
            }

            // 直接查询用户信息，返回信息和token
        }
    }
}













