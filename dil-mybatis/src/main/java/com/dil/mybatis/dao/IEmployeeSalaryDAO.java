package com.dil.mybatis.dao;

import com.dil.mybatis.domain.po.EmployeeSalaryPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IEmployeeSalaryDAO {

    void insert(EmployeeSalaryPO employeeSalary);

    void insertList(List<EmployeeSalaryPO> list);

    void update(EmployeeSalaryPO employeeSalaryPO);

    EmployeeSalaryPO queryEmployeeSalaryByEmployNumber(String employNumber);

}
