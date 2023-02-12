package com.lxn.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.model.dto.PasswordReqiest;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController

@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @PostMapping("/information")
    public BaseResponse<User> getInformation(@RequestBody User user) {
        String userAccount = user.getUserAccount();
        if (StringUtils.isAllBlank(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        User informationByUserAccount = userService.getInformationByUserAccount(userAccount);
        if (informationByUserAccount == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        return ResultUtils.success(informationByUserAccount, "");
    }

    /**
     * 更新基本信息
     * @param user
     * @return
     */
    @PostMapping("/updateBase")
    public BaseResponse<Boolean> updateBaseInformation(@RequestBody User user) {
        String userAccount = user.getUserAccount();
        String userName = user.getUserName();
        String userPhone = user.getUserPhone();
        String gender = user.getGender();

        if (StringUtils.isAllBlank(userAccount, userName, userPhone, gender)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        BaseResponse<User> information = getInformation(user);
        if (information == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        boolean update = userService.update(user, userQueryWrapper);
        return ResultUtils.success(update,"更新成功");

    }

    /**
     * 更新密码
     * @param passwordReqiest
     * @return
     */
    @PostMapping("/password")
    public BaseResponse<Integer> updatePassword(@RequestBody PasswordReqiest passwordReqiest) {
        String userAccount = passwordReqiest.getUserAccount();
        String oldPassword = passwordReqiest.getOldPassword();
        String newPassword = passwordReqiest.getNewPassword();
        String checkPassword = passwordReqiest.getCheckPassword();

        if (StringUtils.isAllBlank(userAccount, oldPassword, newPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        if (!newPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不同");
        }
        User informationByUserAccount = userService.getInformationByUserAccount(userAccount);
        if (informationByUserAccount == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        Integer integer = userService.updatePassword(userAccount, newPassword);
        return ResultUtils.success(integer,"修改成功");
    }


    @PostMapping("/isPresence")
    public BaseResponse isPresence(@RequestBody User user){
        String userAccount = user.getUserAccount();
        if(StringUtils.isAllBlank(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号不能为空");
        }
        Boolean presence = userService.isPresence(userAccount);
        if(presence){
            return ResultUtils.success(presence,"成功");
        }else{
            return ResultUtils.error(404,"用户不存在");
        }
    }

}
