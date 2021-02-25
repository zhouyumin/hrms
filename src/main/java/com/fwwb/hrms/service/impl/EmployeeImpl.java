package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.EmployeeRespository;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 周余民
 * @Date: Created in 15:35 2021/1/25
 */
@Service
public class EmployeeImpl implements EmployeeService {
    @Resource
    EmployeeRespository employeeRespository;
    @Override
    public Employee getById(String uid){
        return employeeRespository.findById(uid).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        employeeRespository.save(employee);
    }
}
