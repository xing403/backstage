package com.lxn.backstage.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;
    private String roomName;
    private String roomPhone;
    private String roomAddress;
    private Integer roomSeat;
    private String roomDesc;
    private String userAccount;
}
