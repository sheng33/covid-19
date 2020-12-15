package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shengQ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser implements Serializable {
    private static final long serialVersionUID = 1041832535256082017L;
    public Integer id;
    public String name;
    public String username;
    public String password;
    public String mobile;
    public int status;
    public int permission;
    public String updateTime;
    public String createTime;

}
