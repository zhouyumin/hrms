package com.fwwb.hrms.dao;

import com.fwwb.hrms.po.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 周余民
 * @Date: Created in 15:33 2021/1/25
 */
public interface CompanyRepository extends JpaRepository<Company, String> {
}
