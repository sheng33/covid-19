package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUser implements Serializable {
    private static final long serialVersionUID = 4250591492098617176L;
    private String userid;
    private String username;
    private String mobile;
    private String identity;
    private String sessionKey;
    private String openid;
    private int istouch;
    private int isarea;
    private int istemperature;
    private Date createtime;

    public ClientUser(String id, String name, String moblie, String identity) {
        this.userid = id;
        this.username = name;
        this.mobile = moblie;
        this.identity = identity;
    }
}
