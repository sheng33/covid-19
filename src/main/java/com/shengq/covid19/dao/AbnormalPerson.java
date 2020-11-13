package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbnormalPerson implements Serializable {
    private static final long serialVersionUID = 4954794300097069460L;
    private Integer userid;
    private String mobile;
    private String address;
    private String nearestaddress;
    private int status;
    private String createtime;
}
