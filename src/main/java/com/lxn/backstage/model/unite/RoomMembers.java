package com.lxn.backstage.model.unite;

import lombok.Data;
// 统计自习室的会员数
@Data
public class RoomMembers {
    private Long Id;
    private String Name;
    private Integer count;
}
