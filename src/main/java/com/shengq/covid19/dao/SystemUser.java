package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shengQ
 */
@Data
@AllArgsConstructor
public class SystemUser implements Serializable {
    private static final long serialVersionUID = 1041832535256082017L;
    private Integer userid;
    private String username;
    private String password;
    private String mobile;
    private int status;
    private int permission;
    private String updateTime;
    private String createTime;
}
