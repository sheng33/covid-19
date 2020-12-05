package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMenu implements Serializable {
    private static final long serialVersionUID = -7471781486952456967L;
    private Integer id;
    private String menu_name;
    private String menu_url;
    private Integer menu_imgId;
    private Integer status;
    private Integer auth;
}
