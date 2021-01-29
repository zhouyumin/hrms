package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Employee;

/**
 * @Author: 周余民
 * @Date: Created in 15:35 2021/1/25
 */
public interface EmployeeService {
    Employee getById(String uid);
    void save(Employee employee);
}
