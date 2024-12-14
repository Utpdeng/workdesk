package com.dil.mybatis.dao;


import com.dil.mybatis.domain.po.EmployeePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IEmployeeDAO {

    EmployeePO queryEmployeeByEmployNumber(String employNumber);

    void insert(EmployeePO employee);

    void insertList(List<EmployeePO> list);

    void update(EmployeePO employeePO);

    void insertAndGetPrimaryKey(EmployeePO employee);

    List<EmployeePO> batchQuery(List<String> employNumbers);
}
