package com.fwwb.hrms.service;

import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 15:31 2021/1/29
 */
public interface ArchiveService {
    List<Archive> getByEmployee(Employee employee);
    List<Archive> getByCompany(Company company);
    void saveArchive(Archive archive);
}
