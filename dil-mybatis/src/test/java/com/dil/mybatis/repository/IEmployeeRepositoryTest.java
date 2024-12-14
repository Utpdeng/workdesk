package com.dil.mybatis.repository;

import com.alibaba.fastjson.JSON;
import com.dil.mybatis.domain.entity.EmployeeInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class IEmployeeRepositoryTest {

    @Resource
    private IEmployeeRepository employeeRepository;

    @Test
    public void testQuery() {
        EmployeeInfoEntity employeeInfoEntity = employeeRepository.queryEmployInfo("10000001");
        log.info("测试结果：{}", JSON.toJSONString(employeeInfoEntity));
    }

}
