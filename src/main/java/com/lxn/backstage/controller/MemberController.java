package com.lxn.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.mapper.RoomMapper;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.entity.Room;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.model.unite.MemberUser;
import com.lxn.backstage.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @Resource
    private RoomMapper roomMapper;
    @PostMapping("/list")
    public BaseResponse<List<MemberUser>> Information(@RequestBody User user){
        String userAccount = user.getUserAccount();
        if(StringUtils.isAllBlank(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        List<MemberUser> memberUsers = memberService.selectAllMemberUser(userAccount);
        return ResultUtils.success(memberUsers,"");
    }
    @GetMapping("/")
    public List<MemberUser> index() {
        return memberService.selectAllMemberUser(1);
    }

    @PostMapping("/information")
    public BaseResponse<Member> getInformation(@RequestBody Member member) {
        String userAccount = member.getUserAccount();
        Integer roomId = member.getRoomId();

        if (StringUtils.isAllBlank(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper
                .eq("userAccount", userAccount)
                .eq("roomId", roomId);
        Member one = memberService.getOne(memberQueryWrapper);
        if (one == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "会员不存在");
        }
        return ResultUtils.success(one, "");

    }

    @PostMapping("/count")
    public BaseResponse<Long> roomDetail(@RequestBody Member member) {
        Integer roomId = member.getRoomId();
        String userAccount = member.getUserAccount();
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper
                .eq("roomId", roomId)
                .eq("userAccount", userAccount);

        if (roomMapper.selectOne(roomQueryWrapper) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "自习室不存在");
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("roomId", roomId);
        long count = memberService.count(memberQueryWrapper);
        return ResultUtils.success(count, "");
    }

    @PostMapping("/addition")
    public BaseResponse<Integer> additionMember(@RequestBody Member member) {

        System.out.println(member);
        Integer roomId = member.getRoomId();
        String userAccount = member.getUserAccount();
        Date beginTime = member.getBeginTime();
        Date endTime = member.getEndTime();
        if (StringUtils.isAllBlank(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "会员名为空");
        }
        if (beginTime == null || endTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "选择时间错误");
        }
        if (roomId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "自习室错误");
        }
        Integer integer = memberService.additionMember(member);
        if (integer == 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "用户存在");
        }
        return ResultUtils.success(integer, "添加成功");
    }

    @PostMapping("/edit")
    public BaseResponse<Boolean> editMember(@RequestBody Member member) {
        String userAccount = member.getUserAccount();
        Integer roomId = member.getRoomId();
        Date beginTime = member.getBeginTime();
        Date endTime = member.getEndTime();
        Long memberId = member.getMemberId();
        if (StringUtils.isAllBlank(userAccount) || memberId < 0 || roomId < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("memberId", memberId);
        Member one = memberService.getOne(memberQueryWrapper);
        if (one == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "会员不存在");
        }
        Member member1 = new Member();
        member1.setBeginTime(beginTime);
        member1.setEndTime(endTime);
        member1.setRoomId(roomId);
        boolean update = memberService.update(member1, memberQueryWrapper);
        return ResultUtils.success(update, "更新成功");
    }
}
