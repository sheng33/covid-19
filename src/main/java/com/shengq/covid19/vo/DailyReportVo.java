package com.shengq.covid19.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyReportVo implements Serializable {
    private static final long serialVersionUID = -7463585936636054665L;
    private String userid;
    private String address;
    private String temperature;
    private String remark;
    private boolean istouch;
}
