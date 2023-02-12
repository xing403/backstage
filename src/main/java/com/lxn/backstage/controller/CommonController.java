package com.lxn.backstage.controller;


import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;

import static com.lxn.backstage.constant.UserConstant.USER_LOGIN_STATE;

import com.lxn.backstage.model.dto.UserLoginRequest;
import com.lxn.backstage.model.dto.UserRegisterRequest;
import com.lxn.backstage.model.dto.PasswordReqiest;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CommonController {

    @Resource
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "hello world";
    }

    /**
     * 注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userName = userRegisterRequest.getUserName();
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String userRole = userRegisterRequest.getUserRole();

        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userRole)) {
            return null;
        }
        long result = userService.userRegister(userName, userAccount, userPassword, checkPassword, userRole);
        return ResultUtils.success(result, "注册成功");
    }

    /**
     * 登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);

        return ResultUtils.success(user, "登录成功");
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

}
