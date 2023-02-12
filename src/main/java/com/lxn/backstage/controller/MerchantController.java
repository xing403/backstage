package com.lxn.backstage.controller;

import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.model.unite.MemberUser;
import com.lxn.backstage.service.MemberService;
import com.lxn.backstage.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private UserService userService;
    @Resource
    private MemberService memberService;

    @PostMapping("/")
    public BaseResponse<List<MemberUser>> Information(@RequestBody User user){
        String userAccount = user.getUserAccount();
        if(StringUtils.isAllBlank(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        List<MemberUser> memberUsers = memberService.selectAllMemberUser(userAccount);
        return ResultUtils.success(memberUsers,"查询成功");
    }
}
