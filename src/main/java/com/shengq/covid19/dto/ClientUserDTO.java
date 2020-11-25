package com.shengq.covid19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUserDTO implements Serializable {
    private static final long serialVersionUID = -2565028992218051744L;
    private String userid;
    private String username;
    private String mobile;
    private int istouch;
    private int isarea;
    private int istemperature;
}
