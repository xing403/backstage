package com.lxn.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.mapper.MemberMapper;
import com.lxn.backstage.mapper.RoomMapper;
import com.lxn.backstage.mapper.UserMapper;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.entity.Room;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.model.unite.MemberUser;
import com.lxn.backstage.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

// 联表查询 MPJBaseServiceImpl填写主表
@Service
public class MemberServiceImpl extends MPJBaseServiceImpl<MemberMapper, Member> implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 根据自习室编号查询所有会员信息
     *
     * @param roomId
     * @return
     */
    @Override
    public List<MemberUser> selectAllMemberUser(Integer roomId) {

        MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<MemberUser>()
                .selectAll(Member.class)
                .selectAll(User.class)
                .selectAll(Room.class)// 链接三个表
                .leftJoin(User.class, User::getUserAccount, Member::getUserAccount)// 连接条件
                .leftJoin(Room.class, Room::getRoomId, Member::getRoomId)
                .eq(Room::getRoomId, roomId);

        return memberMapper.selectJoinList(MemberUser.class, mpjLambdaWrapper);
    }

    @Override
    public List<MemberUser> selectAllMemberUser(String userAccount) {
        MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<MemberUser>()
                .selectAll(Member.class).selectAll(User.class).selectAll(Room.class)
                .leftJoin(User.class, User::getUserAccount, Member::getUserAccount)
                .leftJoin(Room.class, Room::getRoomId, Member::getRoomId)
                .eq(Room::getUserAccount, userAccount);

        return memberMapper.selectJoinList(MemberUser.class, mpjLambdaWrapper);

    }

    @Override
    public Long statisticsMemberByRoomId(Integer roomId) {
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("roomId", roomId);
        if (roomMapper.selectCount(roomQueryWrapper) == 0) {
            return null;
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("roomId", roomId);
        return memberMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Long statisticsMemeberByUserAccount(String userAccount) {
        if (StringUtils.isAllBlank(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        if (userMapper.selectCount(userQueryWrapper) != 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }

        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("userAccount", userAccount);
        return memberMapper.selectCount(memberQueryWrapper);
    }

    @Override
    public Integer additionMember(Member member) {
        System.out.println(member);
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper
                .eq("userAccount", member.getUserAccount())
                .eq("roomId", member.getRoomId());
        if (memberMapper.selectOne(memberQueryWrapper) != null) {
            return 0;
        }
        return memberMapper.insert(member);
    }

}
