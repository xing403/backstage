package com.lxn.backstage.model.unite;

import lombok.Data;

import java.io.Serializable;

@Data
public class Statistics implements Serializable {
    private Long Id;
    private String Name;
    private Integer count;
}
