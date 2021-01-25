package com.fwwb.hrms.dao;

import com.fwwb.hrms.po.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 周余民
 * @Date: Created in 15:35 2021/1/25
 */
public interface EmployeeRespository extends JpaRepository<Employee, String> {
}
