package com.lxn.backstage.service;

import com.github.yulichang.base.MPJBaseService;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.unite.MemberUser;

import java.util.List;

public interface MemberService extends MPJBaseService<Member> {
    /**
     * 查询自习室的所有会员
     * @param roomId
     * @return
     */
    List<MemberUser> selectAllMemberUser(Integer roomId);

    List<MemberUser> selectAllMemberUser(String userAccount);

    /**
     * 自习室会员数量
     * @param roomId
     * @return
     */
    Long statisticsMemberByRoomId(Integer roomId);
    Long statisticsMemeberByUserAccount(String userAccount);


    Integer additionMember(Member member);
}
