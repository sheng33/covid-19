package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigImg implements Serializable {
    private static final long serialVersionUID = -4465731017950286590L;
    /**
     * Banner图类型
     */
    public static final Integer Type_Banner=0;
    /**
     * 用户菜单类型
     */
    public static final Integer Type_ClientMenu=1;
    /***
     * 系统菜单类型
     */
    public static final Integer Type_SysMenu=2;

    private Integer id;
    private String imgname;
    private String imgUrl;
    private Integer operator;
    private String create_time;
    private Integer type;
}
