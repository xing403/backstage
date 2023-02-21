package com.lxn.backstage.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "seat")
public class Seat implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userAccount;
    private Long roomId;
    private Integer seatId;
    private Integer seatStatus;// 1: 已预约，2: 有人
    private Date Time; // status 1 预约时间, 2 充值后结束时间
}
