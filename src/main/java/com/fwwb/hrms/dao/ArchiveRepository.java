package com.fwwb.hrms.dao;

import com.fwwb.hrms.po.Company;
import com.fwwb.hrms.po.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fwwb.hrms.po.Archive;

import java.util.List;

/**
 * @Author: 黄天赐
 * @Date: Created in 23:57 2021/1/28
 */
public interface ArchiveRepository extends JpaRepository<Archive, String>{
    List<Archive> getAllByEmployee(Employee employee);
    List<Archive> getAllByCompany(Company company);
    Archive findByUidAndCompany(Integer uid, Company company);
}
