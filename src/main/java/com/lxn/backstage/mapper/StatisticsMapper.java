package com.lxn.backstage.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.unite.RoomMembers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper  extends MPJBaseMapper<Member> {
    List<RoomMembers> roomsHasMember(String userAccount);
}
