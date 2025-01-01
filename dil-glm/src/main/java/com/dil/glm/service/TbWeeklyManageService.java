package com.dil.glm.service;

import com.dil.glm.domain.weekly.TbWeeklyManage;
import com.dil.glm.mapper.TbWeeklyManageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbWeeklyManageService {

    @Resource
    private TbWeeklyManageMapper tbWeeklyManageMapper;

    public List<TbWeeklyManage> selectTbWeeklyManageList(TbWeeklyManage tbWeeklyManage)
    {
        return tbWeeklyManageMapper.selectTbWeeklyManageList(tbWeeklyManage);
    }


}
