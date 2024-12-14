package com.dil.mybatis.repository;

import com.dil.mybatis.domain.entity.EmployeeInfoEntity;

/**
 * 仓储
 */
public interface IEmployeeRepository {

    EmployeeInfoEntity queryEmployInfo(String employNumber);

    void insertEmployeeInfo(EmployeeInfoEntity employeeInfoEntity);

}
