package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Authorization;
import com.fwwb.hrms.po.Employee;

import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 17:02 2021/4/1
 * @description:
 */
public interface AuthorizationService {
    void save(Authorization authorization);
    List<Authorization> getAuthorization(Employee employee);
    void deleteAuthorization(String uid);
}
