package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.CompanyRepository;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 周余民
 * @Date: Created in 15:33 2021/1/25
 */
@Service
public class CompanyImpl implements CompanyService {
    @Resource
    CompanyRepository companyRepository;
    @Override
    public Company getById(String uid) {
        return companyRepository.findById(uid).orElse(null);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }
}
