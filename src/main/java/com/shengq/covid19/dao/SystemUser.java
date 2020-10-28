package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SystemUser implements Serializable {
    private static final long serialVersionUID = 1041832535256082017L;
    private Integer userid;
    private String username;
    private String password;
    private String mobile;
    private String updateTime;
    private int status;
    private int permission;
}
