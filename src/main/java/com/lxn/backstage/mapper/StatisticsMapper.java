package com.lxn.backstage.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.unite.Statistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper  extends MPJBaseMapper<Member> {
    // 自习室有多少人
    List<Statistics> roomsHasMember(String userAccount);
}
