package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Company;

/**
 * @Author: 周余民
 * @Date: Created in 15:32 2021/1/25
 */
public interface CompanyService {
    Company getById(String uid);
    void save(Company company);
}
