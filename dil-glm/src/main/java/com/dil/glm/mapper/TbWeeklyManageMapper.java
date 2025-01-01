package com.dil.glm.mapper;

import com.dil.glm.domain.weekly.TbWeeklyManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbWeeklyManageMapper {

    public List<TbWeeklyManage> selectTbWeeklyManageList(TbWeeklyManage tbWeeklyManage);

    public TbWeeklyManage selectTbWeeklyManageById(Long id);
}
