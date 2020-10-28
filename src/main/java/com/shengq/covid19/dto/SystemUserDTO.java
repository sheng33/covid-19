package com.shengq.covid19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SystemUserDTO implements Serializable {
    private static final long serialVersionUID = 2032468700387603006L;
    private Integer userid;
    private String username;
    private String password;
    private String mobile;
    private int status;
    private int permission;
}
