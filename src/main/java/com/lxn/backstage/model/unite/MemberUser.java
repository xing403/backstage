package com.lxn.backstage.model.unite;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;

// 联合查询结果字段
@Data
public class MemberUser implements Serializable {
    private Long roomId;
    private String roomName;
    private String userName;
    private String userAccount;
    private String userAvatar;
    private String userPhone;
    private String gender;
    private Date beginTime;
    private Date endTime;
}
