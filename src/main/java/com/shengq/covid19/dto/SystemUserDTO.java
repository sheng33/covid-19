package com.shengq.covid19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserDTO implements Serializable {
    private static final long serialVersionUID = 2032468700387603006L;
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String mobile;
    private int status;
    private int permission;

}
