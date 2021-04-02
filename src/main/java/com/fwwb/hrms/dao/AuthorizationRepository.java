package com.fwwb.hrms.dao;

import com.fwwb.hrms.po.Authorization;
import com.fwwb.hrms.po.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 17:01 2021/4/1
 * @description:
 */
public interface AuthorizationRepository extends JpaRepository<Authorization, String> {
    List<Authorization> findAllByEmployee(Employee employee);
    @Transactional
    void deleteByUid(String uid);
}
