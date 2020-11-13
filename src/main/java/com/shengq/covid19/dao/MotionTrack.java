package com.shengq.covid19.dao;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@ApiOperation("运动轨迹表")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MotionTrack implements Serializable {
    private static final long serialVersionUID = -8313574761206070270L;
    private Integer userid;
    // 交通工具
    private String transportation;
    // 乘车时间
    private String traveltime;
    //车次
    private String serialNumber;

    private String startPoint;
    private String endPoint;
}
