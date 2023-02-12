package com.lxn.backstage.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.lxn.backstage.model.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends MPJBaseMapper<Member> {
}
