package com.shengq.covid19.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author shengQ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserVo implements Serializable {
    private static final long serialVersionUID = 5924312159154672690L;
    private String name;
    private String username;
    private String mobile;
    private int status;
    private String authority;
}
