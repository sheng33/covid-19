package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfigImg implements Serializable {
    private static final long serialVersionUID = -4465731017950286590L;
    private Integer id;
    private String imgname;
    private String imgUrl;
    private Integer operator;
    private String create_time;
}
