package com.shengq.covid19.dao;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@ApiOperation("日常登记表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyReport implements Serializable {
    private static final long serialVersionUID = 3701414900314642145L;
    private Integer id;
    private String userid;
    private String address;
    private String temperature;
    private String remark;
    private int status;
    private String createTime;
}
