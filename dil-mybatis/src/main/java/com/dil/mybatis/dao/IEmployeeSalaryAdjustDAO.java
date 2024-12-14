package com.dil.mybatis.dao;

import com.dil.mybatis.domain.po.EmployeeSalaryAdjustPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IEmployeeSalaryAdjustDAO {

    void insert(EmployeeSalaryAdjustPO employeeSalaryAdjustPO);

}
