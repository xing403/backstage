package com.lxn.backstage.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class PasswordReqiest implements Serializable {
    private String userAccount;
    private String oldPassword;
    private String newPassword;
    private String checkPassword;
}
