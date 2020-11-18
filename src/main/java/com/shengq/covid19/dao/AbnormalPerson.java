package com.shengq.covid19.dao;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@ApiOperation("异常人员信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbnormalPerson implements Serializable {
    private static final long serialVersionUID = 4954794300097069460L;
    private Integer id;
    private Integer userid;
    private String mobile;
    private String address;
    private String nearestaddress;
    private int status;
    private String remark;
    private String createtime;
}
