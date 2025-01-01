package com.dil.glm;

import com.dil.glm.domain.weekly.TbWeeklyManage;
import com.dil.glm.mapper.TbWeeklyManageMapper;
import com.dil.glm.service.TbWeeklyManageService;
import com.dil.glm.service.ZhiPuAi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: "xx"
 * @Date: 2024/12/25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ServiceTest {

    @Resource
    private TbWeeklyManageService tbWeeklyManageService;

    @Resource
    private TbWeeklyManageMapper tbWeeklyManageMapper;

    @Resource
    private ZhiPuAi zhiPuAi;

    @Test
    public void test2() {
        TbWeeklyManage tbWeeklyManage = tbWeeklyManageMapper.selectTbWeeklyManageById(193L);
        log.info(tbWeeklyManage.toString());
    }

    @Test
    public void test() {
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek("2024-12-16~2024-12-22");
        tbWeeklyManage.setDeptId(101L);
        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        for (TbWeeklyManage tbWeeklyManage1 : tbWeeklyManages) {
            log.info(tbWeeklyManage1.toString());
        }
    }

    @Test
    public void summary() throws IOException {
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek("2024-12-16~2024-12-22");
        tbWeeklyManage.setDeptId(101L);
        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("将下列的周报进行总结汇总:");
        for (TbWeeklyManage tbWeeklyManage1 : tbWeeklyManages) {
            sb.append(tbWeeklyManage1.getContext());
        }
        log.info(sb.toString());
        String res = zhiPuAi.ai(sb.toString());
        log.info(tbWeeklyManage.getScopeWeek() + "周报汇总为 {}", res);
    }

}
