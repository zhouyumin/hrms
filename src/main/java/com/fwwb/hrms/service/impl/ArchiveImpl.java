package com.fwwb.hrms.service.impl;

import com.fwwb.hrms.dao.ArchiveRepository;
import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import com.fwwb.hrms.service.ArchiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 15:32 2021/1/29
 */
@Service
public class ArchiveImpl implements ArchiveService {
    @Resource
    ArchiveRepository archiveRespository;

    @Override
    public List<Archive> getByEmployee(Employee employee) {
        return archiveRespository.getAllByEmployee(employee);
    }

    @Override
    public List<Archive> getByCompany(Company company) {
        return archiveRespository.getAllByCompany(company);
    }

    @Override
    public void saveArchive(Archive archive) {
        archiveRespository.save(archive);
    }

    @Override
    public Archive findByEmployeeAndCompany(Employee employee, Company company) {
        return archiveRespository.findByEmployeeAndCompany(employee, company);
    }
}
