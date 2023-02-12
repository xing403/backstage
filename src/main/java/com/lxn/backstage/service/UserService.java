package com.lxn.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxn.backstage.model.dto.PasswordReqiest;
import com.lxn.backstage.model.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userName 用户名
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @param userRole 用户角色
     * @return 新用户 id
     */
    long userRegister(String userName, String userAccount, String userPassword, String checkPassword, String userRole);

    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为用户
     * @param request
     * @return
     */
    boolean isUser(HttpServletRequest request);

    /**
     * 是否为商家
     * @param request
     * @return
     */
    boolean isMerchant(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取用户信息
     * @param userAccount
     * @return
     */
    User getInformationByUserAccount(String userAccount);

    /**
     * 更新密码
     * @param userAccount
     * @param userPassword
     * @return
     */
    Integer updatePassword(String userAccount,String userPassword);

    Boolean isPresence(String userAccount);
}
