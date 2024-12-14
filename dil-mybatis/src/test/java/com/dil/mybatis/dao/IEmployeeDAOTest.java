package com.dil.mybatis.dao;

import com.alibaba.fastjson2.JSON;
import com.dil.mybatis.domain.po.EmployeePO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class IEmployeeDAOTest {

    @Resource
    private IEmployeeDAO employeeDAO;

    @Test
    public void testQuery() {
        EmployeePO employeePO = employeeDAO.queryEmployeeByEmployNumber("10000003");
        log.info("测试结果：{}", JSON.toJSONString(employeePO));
    }

    @Test
    public void testInsert() {
        EmployeePO employee = new EmployeePO();
        employee.setEmployeeNumber("10000003");
        employee.setEmployeeName("e-002");
        employee.setEmployeeLevel("T2");
        employee.setEmployeeTitle("见习工程师");
        employeeDAO.insert(employee);
    }

    @Test
    public void testInsertList() {
        List<EmployeePO> list = new ArrayList<>();
        for (int i = 5; i < 8; i++) {
            EmployeePO employee = new EmployeePO();
            employee.setEmployeeNumber("1000001" + i);
            employee.setEmployeeName("花花");
            employee.setEmployeeLevel("T2");
            employee.setEmployeeTitle("见习工程师");
            list.add(employee);
        }
        employeeDAO.insertList(list);
    }

    @Test
    public void testInsertAndGetPrimaryKey() {
        EmployeePO employee = new EmployeePO();
        employee.setEmployeeNumber("10000004");
        employee.setEmployeeName("e-004");
        employee.setEmployeeLevel("T2");
        employee.setEmployeeTitle("见习工程师");
        employeeDAO.insertAndGetPrimaryKey(employee);
        log.info("测试结果：id: {}, {}", employee.getId(), JSON.toJSONString(employee));
    }

    @Test
    public void testBatchQuery() {
        List<EmployeePO> employees = employeeDAO.batchQuery(Arrays.asList("10000001", "10000003"));
        log.info("测试结果：{}", JSON.toJSONString(employees));
    }
}
