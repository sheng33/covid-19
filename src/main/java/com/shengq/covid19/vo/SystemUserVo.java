package com.shengq.covid19.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserVo implements Serializable {
    private String name;
    private String password;
    private String mobile;
    private String Authority;
}
