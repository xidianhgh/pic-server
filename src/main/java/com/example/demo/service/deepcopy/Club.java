package com.example.demo.service.deepcopy;

import lombok.Data;

import java.io.Serializable;

@Data
public class Club implements Serializable {
    private String club;
    private Integer rank;

}
