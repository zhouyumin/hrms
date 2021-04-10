package com.fwwb.hrms.dao;

import com.fwwb.hrms.dto.AuthorizationDto;
import com.fwwb.hrms.po.Archive;
import com.fwwb.hrms.po.Authorization;
import com.fwwb.hrms.po.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 周余民
 * @Date: Created in 17:01 2021/4/1
 * @description:
 */
public interface AuthorizationRepository extends JpaRepository<Authorization, String> {
    @Query("select new com.fwwb.hrms.dto.AuthorizationDto(p.uid, p.startDate, p.endDate) from Authorization  as p where " +
            "p.employee = ?1")
    List<AuthorizationDto> findAllByEmployee(Employee employee);
    @Transactional
    void deleteByUid(String uid);
}
