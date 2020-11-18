package com.shengq.covid19.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangerTransport implements Serializable {
    private static final long serialVersionUID = 2700271928727045684L;
    private Integer id;
    private String transprotation;
    private String serialNumber;
    private String findtime;
}
