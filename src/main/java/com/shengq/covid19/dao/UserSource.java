package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSource implements Serializable {
    private static final long serialVersionUID = 1098578222471004189L;
    private Integer userid;
    private String platform;

}
