package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangerAddress implements Serializable {
    private static final long serialVersionUID = -3232881282981175783L;
    private Integer id;
    private String province;
    private String city;
    private String area;
    private int status;
    private String createtime;
}
