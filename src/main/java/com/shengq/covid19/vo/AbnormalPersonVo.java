package com.shengq.covid19.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbnormalPersonVo {
    private Integer id;
    private String userid;
    private String username;
    private String mobile;
    private String address;
    private String nearestaddress;
    private int status;
    private String remark;
    private String createtime;
    private int istouch;
    private int isarea;
    private int istemperature;
}
