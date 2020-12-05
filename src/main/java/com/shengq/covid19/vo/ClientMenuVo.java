package com.shengq.covid19.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMenuVo implements Serializable {
    private static final long serialVersionUID = -8954579858358427128L;
    private Integer id;
    private String menu_name;
    private String menu_url;
    private Integer menu_imgId;
    private String ment_imgName;
    private String menu_imgUrl;
    private Integer status;
    private Integer auth;
}
